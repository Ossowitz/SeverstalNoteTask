package ru.severstal.severstalnotetask.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.severstal.severstalnotetask.entity.Note;
import ru.severstal.severstalnotetask.entity.User;
import ru.severstal.severstalnotetask.repository.NoteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteService {

    private NoteRepository noteRepository;

    public Note saveNotes(Note note) {
        return noteRepository.save(note);
    }

    public Note getNotesById(int id) {
        return noteRepository.findById(id).get();
    }

    public List<Note> getNotesByUser(User user) {
        return noteRepository.findByUser(user);
    }

    public Note updateNotes(Note note) {
        return noteRepository.save(note);
    }

    public boolean deleteNotes(int id) {
        Note note = noteRepository.findById(id).get();
        noteRepository.delete(note);
        return true;
    }
}