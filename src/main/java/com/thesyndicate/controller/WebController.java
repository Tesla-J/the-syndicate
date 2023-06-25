package com.thesyndicate.controller;

import java.util.Objects;

import cn.apiclub.captcha.Captcha;

import com.thesyndicate.entity.UserKt;
import com.thesyndicate.util.CaptchaManagerKt;

import com.thesyndicate.util.CaptchaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WebController {
	private final String LOGIN_ERROR = "LOGIN_ERROR";
	private final String CAPTCHA_ERROR = "CAPTCHA_ERROR";
	private final String LOGIN_SUCCESS = "LOGIN_SUCCESS";

	@Autowired
	private UserController userController;
	private CaptchaWrapper captchaWrapper;

	@GetMapping(value = {"/", "index", "home"})
	public String index() {
		return "index";
	}

	/**
	 * Login page
	 * @param model model
	 * @return
	 */
	@GetMapping(value = "/login")
	public String login(
			Model model,
			@RequestParam(value = "loginError", required = false) boolean loginError,
			@RequestParam(value = "captchaError", required = false) boolean captchaError,
			@RequestParam(value = "loginSuccess", required = false) boolean loginSuccess
			) {

		model.addAttribute(LOGIN_ERROR, loginError);
		model.addAttribute(CAPTCHA_ERROR, captchaError);

		this.captchaWrapper = new CaptchaWrapper();
		model.addAttribute("captchaWrapper", this.captchaWrapper);

		//log
		System.out.println("loginSuccess="+loginSuccess);

		if(loginSuccess) return "redirect:/home";
		return "login";
	}

	/**
	 * Verify authentication data and captcha
	 * @param username username
	 * @param password password
	 * @param captchaWrapper captcha object
	 * @param model model
	 * @return RedirectView object that redirects to the login page (GET request)
	 */
	@PostMapping(value = "/login")
	public RedirectView login(
			String username,
			String password,
			@ModelAttribute("captchaWrapper") CaptchaWrapper captchaWrapper,
			Model model) {

		StringBuilder suffix = new StringBuilder();

		// Verification
		if(CaptchaManagerKt.verifyCaptcha(this.captchaWrapper.getCaptchaInstance(), captchaWrapper.getUserCaptchaAnswer())){
			var user = userController.authenticate(username, password);
			//System.err.println(UserKt.encryptPassword("root"));
			if (Objects.isNull(user)) {
				System.out.println("Login failed");
				suffix.append("?loginError=true");//model.addAttribute(LOGIN_ERROR, true);
			}
			else {
				System.out.println("Welcome");
				suffix.append("?loginSuccess=true");
				model.addAttribute(LOGIN_SUCCESS, true);
			}
		}else{
			suffix.append(suffix.toString().length() > 0 ? "&captchaError=true": "?captchaError=true");//model.addAttribute(CAPTCHA_ERROR, true);
		}
		return new RedirectView("/login" + suffix.toString());
	}
}
