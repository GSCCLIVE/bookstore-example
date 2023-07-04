package gscclive.example.bookstore.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Book entity.
 * 
 * @author GCLIVE
 */
@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Book {

    @Id
    @Column(name = "isbn")
    @NotNull(message = "ISBN is missing.")
    @NotBlank(message = "ISBN cannot be empty.")
    private String isbn;

    @NotBlank(message = "Title cannot be empty.")
    private String title;

    @OneToMany(mappedBy = "book", cascade=CascadeType.ALL, orphanRemoval = true)
    @NotNull(message = "Book need to have an author.")
    @Size(min = 1, message = "Book need to have minimum of 1 author.")
    private Set<Author> authors;

    @Column(name = "publication", nullable = false)
    @NotNull(message = "Book need a publication year.")
    @Min(1000)
    @Max(9999)
    private int year;

    @PositiveOrZero
    @NotNull(message = "Book need to have a price.")
    private double price;

    @NotBlank(message = "Book need to have a genre.")
    private String genre;

}
