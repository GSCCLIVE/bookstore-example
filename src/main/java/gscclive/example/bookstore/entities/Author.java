package gscclive.example.bookstore.entities;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    private String name; 

    @Column(name = "author_birthday", nullable = false)
    private LocalDate birthday;

    // @ManyToMany
    // @JoinColumn(name="book_isbn")
    // @JsonIgnore
    // private Set<Book> book;
}
