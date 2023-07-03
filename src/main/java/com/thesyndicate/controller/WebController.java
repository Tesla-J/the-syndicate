package com.thesyndicate.controller;

import java.util.Objects;

import com.thesyndicate.entity.*;
import com.thesyndicate.util.CaptchaWrapperKt;

import com.thesyndicate.util.CaptchaWrapper;
import jakarta.servlet.http.HttpSession;
import kotlin.text.UStringsKt;
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
	private final String DEFAULT_PASSWORD = "123";
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

	// flags
	private final String ERROR_FLAG = "ERROR_FLAG";
	private final String SUCCESS_FLAG = "SUCCESS_FLAG";
	private final String SUCCESS_MESSAGE = "SUCCESS_MESSAGE";
	private final String ERROR_MESSAGE = "ERROR_MESSAGE";
	private Boolean errorFlag = false;
	private Boolean successFlag = false;
	private String errorMessage;
	private String successMessage;

	@Autowired
	private UserController userController;
	@Autowired
	private EmployeeController employeeController;
	@Autowired
	private RelatoryController relatoryController;
	@Autowired
	private CorpseController corpseController;
	@Autowired
	private WalletController walletController;
	@Autowired
	private TransactionController transactionController;
	private final CaptchaWrapper captchaWrapper; //maiusculate

	public WebController(){
		this.captchaWrapper = new CaptchaWrapper();
	}

	// flags manager

	/**
	 * reset the error and success flags and their respective messages
	 */
	private void resetFlags(){
		this.errorFlag = false;
		this.errorMessage = "";
		this.successFlag = false;
		this.successMessage = "";
	}

	/**
	 * sets the error flag to true and a custom error message
	 * @param errorMessage custom error message
	 */
	private void setErrorMessage(String errorMessage){
		this.errorFlag = true;
		this.errorMessage = errorMessage;
	}

	/**
	 * set's the success flag to true and a custom success message
	 * @param successMessage custom success message
	 */
	private void setSuccessMessage(String successMessage){
		this.successFlag = true;
		this.successMessage = successMessage;
	}

	/**
	 * define the flags to show the error and success messages
	 * @param model the model object where the flags will be set
	 */
	private void setFlags(Model model){
		model.addAttribute(ERROR_FLAG, this.errorFlag);
		model.addAttribute(ERROR_MESSAGE, this.errorMessage);
		model.addAttribute(SUCCESS_FLAG, this.successFlag);
		model.addAttribute(SUCCESS_MESSAGE, this.successMessage);
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
				suffix.append("?" + LOGIN_ERROR_PARAM + "=true");//model.addAttribute(LOGIN_ERROR, true);
			}
			else {
				suffix.append("?loginSuccess=true");
				model.addAttribute(LOGIN_SUCCESS, true);

				httpSession.setAttribute("user", user); //start user session
				if(user.isEmployee()){
					var employee = employeeController.findByUserId(user);
					httpSession.setAttribute("employee", employee);
				}
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
		var newWallet = new Wallet(null, WalletKt.generateHash(), 0.0D, newUser);
		walletController.save(newWallet);

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
	public String registerEmployee(Model model,
								   HttpSession httpSession){
		User user = (User) httpSession.getAttribute("user");
		if(user == null || !user.getUsername().equals("root"))
			return "redirect:/login";

		model.addAttribute("user", user);
		// set flags and messages
		setFlags(model);

		var newEmployee = new Employee();
		model.addAttribute("newEmployee", newEmployee);
		model.addAttribute(CAPTCHA_WRAPPER, this.captchaWrapper);

		// reset flags and messages
		resetFlags();

		return "register_employee";
	}

	@PostMapping(value = "/dashboard/register_employee")
	public String registerEmployee(Model model,
								   @ModelAttribute("newEmployee") Employee tmpEmployee,
								   @ModelAttribute(CAPTCHA_WRAPPER) CaptchaWrapper captchaWrapper,
								   @RequestParam String username){

		if(!CaptchaWrapperKt.verifyCaptcha(this.captchaWrapper.getCaptchaInstance(), captchaWrapper.getUserCaptchaAnswer())){
			setErrorMessage("Wrong captcha answer");
			return "redirect:/dashboard/register_employee";
		}
		else if(this.userController.exists(username)){
			setErrorMessage("Username already taken");
			return "redirect:/dashboard/register_employee";
		}
		else if(employeeController.exists(tmpEmployee.getName())){
			setErrorMessage("Employee name already registered");
			return "redirect:/dashboard/register_employee";
		}
		else if(employeeController.exists(tmpEmployee.getBi())){
			setErrorMessage("Employee BI already registered");
			return "redirect:/dashboard/register_employee";
		}
		else if(employeeController.exists(tmpEmployee.getPhoneNumber())){
			setErrorMessage("Employee phone number already registered");
			return "redirect:/dashboard/register_employee";
		}

		// registering employee
		var newUser = new User(null, username, UserKt.encryptPassword(DEFAULT_PASSWORD), true);
		userController.save(newUser);
		var newEmployee = new Employee(null,
				tmpEmployee.getName(),
				tmpEmployee.getBi(),
				tmpEmployee.getPhoneNumber(),
				tmpEmployee.getEmail(),
				tmpEmployee.getBirthDate(), newUser);
		employeeController.save(newEmployee);

		setSuccessMessage("Employee successfully registered");
		return "redirect:/dashboard/register_employee";
	}

	@GetMapping(value = "/dashboard/write_relatory")
	public String writeRelatory(Model model, HttpSession httpSession){
		User user = (User) httpSession.getAttribute("user");
		if(user == null || !user.isEmployee())
			return "redirect:/dashboard";

		setFlags(model);

		model.addAttribute("user", user);
		//add employee info if is an employee TODO
		model.addAttribute("newCorpse", new Corpses());
		model.addAttribute("newRelatory", new Relatories());
		model.addAttribute(CAPTCHA_WRAPPER, this.captchaWrapper);

		resetFlags();
		return "write_relatory";
	}

	@PostMapping(value = "/dashboard/write_relatory")
	public String writeRelatory(Model model,
								HttpSession httpSession,
								@ModelAttribute("newCorpse") Corpses tmpCorpse,
								@ModelAttribute("newRelatory") Relatories tmpRelatories,
								@ModelAttribute(CAPTCHA_WRAPPER) CaptchaWrapper captchaWrapper){
		if(!CaptchaWrapperKt.verifyCaptcha(this.captchaWrapper.getCaptchaInstance(), captchaWrapper.getUserCaptchaAnswer())){
			setErrorMessage("Wrong captcha answer");
			return "redirect:/dashboard/write_relatory";
		}

		var newRelatory = new Relatories(null,
										tmpRelatories.getTitle(),
										tmpRelatories.getContent(),
										(Employee) httpSession.getAttribute("employee"));
		relatoryController.save(newRelatory);
		var birthDate = tmpCorpse.getBirthDate().length() > 0 ? tmpCorpse.getBirthDate() : null;
		var newCorpse = new Corpses(null,
									tmpCorpse.getName(),
									tmpCorpse.getBi(),
									birthDate,
									tmpCorpse.getDeceaseDate(),
									newRelatory);
		corpseController.save(newCorpse);

		setSuccessMessage("Relatory successfully submitted");
		return "redirect:/dashboard/write_relatory";
	}

	@GetMapping(value = "/dashboard/wallet")
	public String wallet(Model model, HttpSession httpSession){

		var user = (User) httpSession.getAttribute("user");
		if(user == null || user.isEmployee())
			return "redirect:/dashboard";

		setFlags(model);
		resetFlags();

		model.addAttribute(CAPTCHA_WRAPPER, this.captchaWrapper);
		model.addAttribute("user", user);
		var wallet = walletController.findByOwner(user);
		model.addAttribute("wallet", wallet);

		return "wallet";
	}

	@PostMapping(value = "/dashboard/wallet")
	public String wallet(Model model,
						 HttpSession httpSession,
						 @ModelAttribute(CAPTCHA_WRAPPER) CaptchaWrapper captchaWrapper){

		return "redirect:/dashboard/wallet";
	}
}
