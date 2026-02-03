package io.github.danilotomassoni.libraryapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="books")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude="author")
public class Book {
    
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private String isbn;

    private String title;

    @Column(name="publication_date")
    private LocalDate publicationDate;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    private BigDecimal price;

    @ManyToOne //(fetch=FetchType.LAZY)
    @JoinColumn(name="id_author")
    private Author author;

}
