package ru.severstal.severstalnotetask.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notes")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Note extends NamedEntity {

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OrderColumn
    private User user;

    @Override
    public String toString() {
        return "Note{" +
               "description='" + description + '\'' +
               ", name='" + name + '\'' +
               ", id=" + id +
               '}';
    }
}