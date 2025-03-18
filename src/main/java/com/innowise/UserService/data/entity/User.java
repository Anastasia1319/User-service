package com.innowise.UserService.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;

        if (id == 0 && user.id == 0) {
            return email != null && email.equals(user.email);
        }

        return id == user.id;
    }

    @Override
    public int hashCode() {
        if (id == 0) {
            return email != null ? email.hashCode() : 0;
        }
        return Long.hashCode(id);
    }

}
