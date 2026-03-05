package io.github.danilotomassoni.libraryapi.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Column(unique=true,length=20,nullable=false)
    private String login;

    @Column(length=300, nullable=false)
    private String password;

    @Column(name="roles", columnDefinition="varchar[]")
    private List<String> roles;
}
