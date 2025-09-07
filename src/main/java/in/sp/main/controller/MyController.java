package in.sp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.sp.main.entity.User;
import in.sp.main.services.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/regPage")
	public String openRegPage(Model model) 
	{
		model.addAttribute("users", new User());
		return "register";
	}
	
	@PostMapping("/submitReg")
	public String submitRegForm(@ModelAttribute("users") User user, Model model)
	{
		User user2 = userService.regsiterUser(user);
		if(user2 != null) {
			model.addAttribute("successMsg", "User Reguister Successfully");
			return "redirect:/loginPage";
		}
		else {
			model.addAttribute("errorMsg", "User Not Reguister!");
			return "register";
		}
	}
	
	@GetMapping("/loginPage")
	public String openLoginPage(Model model) 
	{
		model.addAttribute("users", new User());
		return "login";
	}
	
	@PostMapping("/submitLogin")
	public String submitLoginForm(@ModelAttribute("users") User user, HttpSession session, Model model)
	{
		User validUser = userService.loginUser(user.getEmail(), user.getPassword());
		if(validUser != null) {
			session.setAttribute("sessionUser", validUser);
			return "redirect:/taskPage";
		}
		else {
			model.addAttribute("errorMsg", "User Not Login");
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) 
	{
		session.invalidate();
		return "redirect:/loginPage";
	}
	
}
