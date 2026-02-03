package io.github.danilotomassoni.libraryapi.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="authors")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude="books")
public class Author {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(name="date_birth")
    private LocalDate dateBirth;
    
    private String nationality;

    @OneToMany(mappedBy="author",fetch=FetchType.LAZY)
    private List<Book> books;
    
}
