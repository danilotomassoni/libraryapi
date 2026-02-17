package io.github.danilotomassoni.libraryapi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@EntityListeners(AuditingEntityListener.class)
public class Author {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Column(nullable=false, length=100)
    private String name;

    @Column(name="date_birth",nullable=false)
    private LocalDate dateBirth;
    
    @Column(nullable=false)
    private String nationality;

    @OneToMany(mappedBy="author",fetch=FetchType.LAZY)
    private List<Book> books;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    
}
