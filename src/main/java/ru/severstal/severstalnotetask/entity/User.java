package ru.severstal.severstalnotetask.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity
@Table(name = "users",
		uniqueConstraints = @UniqueConstraint(columnNames = {"email"},
				name = "notes_unique_email_idx"))
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class User extends NamedEntity {

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Note> notes;
}