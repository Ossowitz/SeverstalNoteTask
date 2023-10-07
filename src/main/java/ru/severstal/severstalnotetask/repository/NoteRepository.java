package ru.severstal.severstalnotetask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.severstal.severstalnotetask.entity.Note;
import ru.severstal.severstalnotetask.entity.User;

import java.util.List;

@Transactional(readOnly = true)
@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByUser(User user);
}