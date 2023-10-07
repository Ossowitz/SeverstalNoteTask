package ru.severstal.severstalnotetask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.severstal.severstalnotetask.entity.User;

@Transactional(readOnly = true)
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	boolean existsByEmail(String email);

	User findByEmail(String email);
}