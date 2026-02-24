package io.github.danilotomassoni.libraryapi.repositories.specs;

import org.springframework.data.jpa.domain.Specification;

import io.github.danilotomassoni.libraryapi.model.Book;
import io.github.danilotomassoni.libraryapi.model.GenderType;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class BookSpec {

    public static Specification<Book> isbnEqual(String isbn) {
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    public static Specification<Book> titleLike(String title) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("title")), "%" + title.toUpperCase() + "%");
    }

    public static Specification<Book> genderEqual(GenderType gender) {
        return (root, query, cb) -> cb.equal(root.get("gender"), gender);
    }

    public static Specification<Book> publicationDateEqual(Integer publicationDate) {
        return (root, query, cb) -> cb.equal(
            cb.function("to_char", String.class, root.get("publicationDate"),cb.literal("YYYY")), 
            publicationDate.toString());
    }


    public static Specification<Book> nameAuthorLike(String name) {
        return (root, query, cb) -> {
            Join<Object,Object> joinAuthor = root.join("author",JoinType.INNER);

            return cb.like(cb.upper(joinAuthor.get("name")), "%" + name.toUpperCase() + "%");
            //return cb.like(cb.upper(root.get("author").get("name")), "%"+name.toUpperCase()+"%");
        };
    }


}
