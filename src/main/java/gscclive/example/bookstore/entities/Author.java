package gscclive.example.bookstore.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "author")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Author {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "author_id")
    // @JsonIgnore
    // private int id;

    @Id
    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(message = "Author need to have a name")
    private String name;

    @Column(name = "birthday", nullable = false)
    @NotNull(message = "Author need to have a birthday")
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name="book_isbn", nullable = false)
    @JsonIgnore
    private Book book;

}
