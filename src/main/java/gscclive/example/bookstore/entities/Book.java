package gscclive.example.bookstore.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book {

    @Id
    @Column(name = "book_isbn")
    private String isbn;

    @Column(name = "book_title", nullable = false)
    @NotBlank(message = "Title cannot be empty.")
    private String title;

    @ManyToMany
    @JoinColumn(name = "author_id", insertable = true, updatable = true)
    @NotNull(message = "Book need to have minimum of 1 author.")
    private Set<Author> authors;

    @Column(name = "book_year", nullable = false)
    @Min(1000)
    private int year;

    @Column(name = "book_price", nullable = false)
    @PositiveOrZero
    private double price;

    @Column(name = "book_genre", nullable = false)
    @NotBlank(message = "Book need to have a genre")
    private String genre;
}
