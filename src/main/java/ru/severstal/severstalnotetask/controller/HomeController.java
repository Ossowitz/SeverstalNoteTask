package ru.severstal.severstalnotetask.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.severstal.severstalnotetask.entity.User;
import ru.severstal.severstalnotetask.service.UserService;

@Controller
@AllArgsConstructor
@Slf4j
public class HomeController {

	private UserService userService;

	@GetMapping("/")
	public String index() {
		log.info("main page output");
		return "index";
	}

	@GetMapping("/register")
	public String register() {
		log.info("registration page");
		return "register";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session) {

		log.info("create user {}", user);
		boolean existEmailCheck = userService.existEmailCheck(user.getEmail());

		if (existEmailCheck) {
			session.setAttribute("msg", "Email already exist");
			log.info("email already exist");
		} else {
			User saveUser = userService.prepareSave(user);
			if (!saveUser.isNew()) {
				session.setAttribute("msg", "Register success");
				log.info("{} register success", user);
			} else {
				session.setAttribute("msg", "Something wrong on server");
				log.info("something wrong on server");
			}
		}
		return "redirect:/register";
	}

	@GetMapping("/signin")
	public String login() {
		log.info("login page");
		return "login";
	}
}