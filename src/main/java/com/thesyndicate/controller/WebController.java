package com.thesyndicate.controller;

import java.util.Objects;

import com.thesyndicate.entity.User;
import com.thesyndicate.entity.UserKt;
import com.thesyndicate.util.CaptchaWrapperKt;

import com.thesyndicate.util.CaptchaWrapper;
import jakarta.servlet.http.HttpSession;
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
	private final String LOGIN_ERROR_PARAM = "login_error";
	private final String CAPTCHA_ERROR = "CAPTCHA_ERROR";
	private final String CAPTCHA_ERROR_PARAM = "captcha_error";
	private final String LOGIN_SUCCESS = "LOGIN_SUCCESS";
	private final String LOGIN_SUCCESS_PARAM = "login_success";
	private final String SIGNUP_ERROR = "SIGNUP_ERROR";
	private final String SIGNUP_ERROR_PARAM = "signup_error";
	private final String CAPTCHA_WRAPPER = "captchaWrapper";
	private final String SIGNUP_SUCCESS = "SIGNUP_SUCCESS";
	private final String SIGNUP_SUCCESS_PARAM = "signup_success";
	private final String PASSWORD_MISMATCH_ERROR = "PASSWORD_MISMATCH_ERROR";
	private final String PASSWORD_MISMATCH_ERROR_PARAM = "password_mismach";

	@Autowired
	private UserController userController;
	private final CaptchaWrapper captchaWrapper;

	public WebController(){
		this.captchaWrapper = new CaptchaWrapper();
	}

	/**
	 * homepate
	 * @return return the homepage
	 */
	@GetMapping(value = {"/", "index", "home"})
	public String index(Model model, HttpSession httpSession) {
		model.addAttribute("user", httpSession.getAttribute("user"));
		return "index";
	}

	/**
	 * Login page
	 * @param model model
	 * @return
	 */
	@GetMapping(value = "/login")
	public String login(Model model,
						HttpSession httpSession,
						@RequestParam(value = LOGIN_ERROR_PARAM, required = false) boolean loginError,
						@RequestParam(value = CAPTCHA_ERROR_PARAM, required = false) boolean captchaError,
						@RequestParam(value = LOGIN_SUCCESS_PARAM, required = false) boolean loginSuccess,
						@RequestParam(value = SIGNUP_SUCCESS_PARAM, required = false) boolean signUpSuccess) {

		// redirect if is already logged in
		if(httpSession.getAttribute("user") != null)
			return "redirect:/dashboard";

		model.addAttribute(LOGIN_ERROR, loginError);
		model.addAttribute(CAPTCHA_ERROR, captchaError);
		model.addAttribute(SIGNUP_SUCCESS, signUpSuccess);

		model.addAttribute(CAPTCHA_WRAPPER, this.captchaWrapper);

		//log
		System.out.println(LOGIN_SUCCESS_PARAM + " : " + loginSuccess);

		if(loginSuccess) {
			return "redirect:/dashboard";
		}
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
	public RedirectView login(String username,
							  String password,
							  @ModelAttribute(CAPTCHA_WRAPPER) CaptchaWrapper captchaWrapper,
							  Model model,
							  HttpSession httpSession) {

		if(httpSession.getAttribute("user") != null) new RedirectView("/dashboard");

		StringBuilder suffix = new StringBuilder();

		// Verification
		if(CaptchaWrapperKt.verifyCaptcha(this.captchaWrapper.getCaptchaInstance(), captchaWrapper.getUserCaptchaAnswer())){
			var user = userController.authenticate(username, password);
			//System.err.println(UserKt.encryptPassword("root"));
			if (Objects.isNull(user)) {
				System.out.println("Login failed");
				suffix.append("?" + LOGIN_ERROR_PARAM + "=true");//model.addAttribute(LOGIN_ERROR, true);
			}
			else {
				System.out.println("Welcome"); //log
				suffix.append("?loginSuccess=true");
				model.addAttribute(LOGIN_SUCCESS, true);

				httpSession.setAttribute("user", user); //start user session
			}
		}else{
			suffix.append(suffix.toString().length() > 0 ? "&" + CAPTCHA_ERROR_PARAM + "=true": "?" + CAPTCHA_ERROR_PARAM + "=true");//model.addAttribute(CAPTCHA_ERROR, true);
		}
		return new RedirectView("/login" + suffix.toString());
	}

	/**
	 * sign up for black market users
	 * @param signUpError a flag to verify if the registration process returned a error
	 * @param model
	 * @return redirects to the defined template
	 */
	@GetMapping(value = "/register_user")
	public String registerUser(@RequestParam(value = SIGNUP_ERROR_PARAM, required = false) boolean signUpError,
							   @RequestParam(value = CAPTCHA_ERROR_PARAM, required = false) boolean captchaError,
							   @RequestParam(value = PASSWORD_MISMATCH_ERROR_PARAM, required = false) boolean passwordMismatchError,
							   Model model,
							   HttpSession httpSession){
		//if is logged in
		if(httpSession.getAttribute("user") != null) return "redirect:/dashboard";

		if(captchaError) model.addAttribute(CAPTCHA_ERROR, captchaError);
		else if(signUpError) model.addAttribute(SIGNUP_ERROR, signUpError);
		else if(passwordMismatchError) model.addAttribute(PASSWORD_MISMATCH_ERROR, passwordMismatchError);

		model.addAttribute(CAPTCHA_WRAPPER, this.captchaWrapper);

		return "register_user";
	}

	@PostMapping(value = "/register_user")
	public String registerUser(Model model,
							   @RequestParam String username,
							   @RequestParam String pass,
							   @RequestParam String cpass,
							   @ModelAttribute(CAPTCHA_WRAPPER) CaptchaWrapper captchaWrapper){

		if(!CaptchaWrapperKt.verifyCaptcha(this.captchaWrapper.getCaptchaInstance(), captchaWrapper.getUserCaptchaAnswer()))
			return "redirect:/register_user?" + CAPTCHA_ERROR_PARAM + "=true";
		else if(userController.exists(username))
			return "redirect:/register_user?" + SIGNUP_ERROR_PARAM + "=true";
		else if(!pass.equals(cpass))
			return "redirect:/register_user?" + PASSWORD_MISMATCH_ERROR_PARAM + "=true";

		var newUser = new User(null, username, UserKt.encryptPassword(pass), false);
		userController.save(newUser);

		return "redirect:/login?" + SIGNUP_SUCCESS_PARAM + "=true";
	}

	@GetMapping(value = "/dashboard")
	public String dashboard(Model model, HttpSession httpSession){
		if(httpSession.getAttribute("user") == null) return "redirect:/login";

		model.addAttribute("user", httpSession.getAttribute("user"));

		return "/dashboard";
	}

	@GetMapping(value = "/dashboard/logout")
	public String logout(Model model, HttpSession httpSession){
		httpSession.invalidate();
		return "redirect:/home";
	}

	@GetMapping(value = "/dashboard/register_employee")
	public String registerEmployee(Model model, HttpSession httpSession){
		User user = (User) httpSession.getAttribute("user");
		if(user == null || !user.getUsername().equals("root"))
			return "redirect:/login";

		model.addAttribute("users", userController.getAll());
		model.addAttribute(CAPTCHA_WRAPPER, this.captchaWrapper);

		return "register_employee";
	}

	@PostMapping(value = "/dashboard/register_employee")
	public String registerEmployee(Model model){
		return "redirect:/dashboard/register_employee";
	}
}
