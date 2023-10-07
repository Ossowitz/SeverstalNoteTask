package ru.severstal.severstalnotetask.service;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.severstal.severstalnotetask.entity.User;
import ru.severstal.severstalnotetask.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepo;

	private BCryptPasswordEncoder passwordEncoder;

	public User prepareSave(User user) {
		user.setRole("ROLE_USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	public boolean existEmailCheck(String email) {
		return userRepo.existsByEmail(email);
	}

	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes)
				(RequestContextHolder.getRequestAttributes()))
				.getRequest()
				.getSession();
		session.removeAttribute("msg");
	}
}