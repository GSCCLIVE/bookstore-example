package gscclive.example.bookstore.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    private String title;

    @ManyToMany
    @JoinColumn(name = "author_id", insertable = true, updatable = true)
    private Set<Author> authors;

    @Column(name = "book_year", nullable = false)
    private int year;

    @Column(name = "book_price", nullable = false)
    private double price;

    @Column(name = "book_genre", nullable = false)
    private String genre;
}
