package gscclive.example.bookstore.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "author")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    @JsonIgnore
    private int id;

    @Column(name = "author_name", nullable = false, unique = true)
    @NotBlank(message = "Author need to have a name")
    private String name; 

    @Column(name = "author_birthday", nullable = false)
    @NotNull(message = "Author need to have a birthday")
    private LocalDate birthday;

    // @ManyToMany
    // @JoinColumn(name="book_isbn")
    // @JsonIgnore
    // private Set<Book> book;
}
