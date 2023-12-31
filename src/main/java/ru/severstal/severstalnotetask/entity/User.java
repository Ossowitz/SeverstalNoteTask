package ru.severstal.severstalnotetask.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users",
		uniqueConstraints = @UniqueConstraint(columnNames = {"email"},
				name = "notes_unique_email_idx"))
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class User extends NamedEntity {

	@Column(name = "email")
	public String email;

	@Column(name = "address")
	public String address;

	@Column(name = "password")
	public String password;

	@Column(name = "role")
	public String role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	public List<Note> notes;
}