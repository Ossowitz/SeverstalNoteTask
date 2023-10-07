package ru.severstal.severstalnotetask.controller;

import java.security.Principal;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.severstal.severstalnotetask.entity.Note;
import ru.severstal.severstalnotetask.entity.User;
import ru.severstal.severstalnotetask.service.NoteService;
import ru.severstal.severstalnotetask.service.UserService;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class UserController {

	private UserService userService;

	private NoteService noteService;

	@ModelAttribute
	public User getUser(Principal p, Model m) {
		String email = p.getName();
		User user = userService.findByEmail(email);
		log.info("get user {}", user);
		m.addAttribute("user", user);
		return user;
	}

	@GetMapping("/addNotes")
	public String addNotes() {
		log.info("add note");
		return "add_notes";
	}

	@GetMapping("/viewNotes")
	public String viewNotes(Model model, Principal principal) {
		User user = getUser(principal, model);
		List<Note> notes = noteService.getNotesByUser(user);
		log.info("view notes: {}", notes);
		model.addAttribute("notesList", notes);
		return "view_notes";
	}

	@GetMapping("/editNotes/{id}")
	public String editNotes(@PathVariable("id") int id, Model model) {
		Note editNote = noteService.getNotesById(id);
		log.info("edit note: {}", editNote);
		model.addAttribute("n", editNote);
		return "edit_notes";
	}

	@PostMapping("/saveNotes")
	public String saveNotes(@ModelAttribute Note note, HttpSession session,
							Principal principal, Model model) {
		note.setUser(getUser(principal, model));

		Note newNote = noteService.saveNotes(note);

		log.info("create note: {}", newNote);

		if (!newNote.isNew()) {
			session.setAttribute("msg", "Notes Save success");
		} else {
			session.setAttribute("msg", "Something wrong on server");
			log.info("something wrong on server");
		}
		return "redirect:/user/addNotes";
	}

	@PostMapping("/updateNotes")
	public String updateNotes(@ModelAttribute Note note, HttpSession session,
							  Principal principal, Model model) {
		log.info("before update note: {}", note);
		note.setUser(getUser(principal, model));

		Note saveNote = noteService.saveNotes(note);
		if (!saveNote.isNew()) {
			session.setAttribute("msg", "Notes update success");
			log.info("after update note: {}", note);
		} else {
			session.setAttribute("msg", "Something wrong on server");
			log.info("something wrong on server");
		}
		return "redirect:/user/viewNotes";
	}

	@GetMapping("/deleteNotes/{id}")
	public String deleteNotes(@PathVariable int id, HttpSession session) {
		boolean deleteNotes = noteService.deleteNotes(id);
		if (deleteNotes) {
			session.setAttribute("msg", "Delete success");
			log.info("delete success");
		} else {
			session.setAttribute("msg", "Something wrong on server");
			log.info("something wrong on server");
		}
		return "redirect:/user/viewNotes";
	}
}