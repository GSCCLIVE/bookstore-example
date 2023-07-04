package gscclive.example.bookstore.config;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import gscclive.example.bookstore.entities.Author;
import gscclive.example.bookstore.entities.Book;
import gscclive.example.bookstore.service.BookService;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableTransactionManagement
public class ContextConfiguration {

	private static final String LOG_CREATE_BOOK = "Creating new book: {}";

	@Bean
	public CommandLineRunner demo(BookService service) {
		return args -> {
			Author clive = Author.builder()
					.name("Clive")
					.birthday(LocalDate.now().minusYears(5))
					.build();
			Author bauer = Author.builder()
					.name("Bauer")
					.birthday(LocalDate.now().minusYears(10).minusMonths(10))
					.build();
			Author kelvin = Author.builder()
					.name("Kelvin")
					.birthday(LocalDate.now().minusYears(10))
					.build();

			Book helloWorld = Book.builder()
					.isbn("100-1")
					.title("Hello World")
					.authors(Set.of(clive))
					.year(2023)
					.price(20.5)
					.genre("Software")
					.build();
			Book sportWorld = Book.builder()
					.isbn("200-1")
					.title("Sport World")
					.authors(Set.of(bauer))
					.year(2023)
					.price(20.5)
					.genre("World")
					.build();
			Book divineWorld = Book.builder()
					.isbn("300-1")
					.title("Divine World")
					.authors(Set.of(clive, kelvin))
					.year(2023)
					.price(20.5)
					.genre("World")
					.build();

			log.info(LOG_CREATE_BOOK, helloWorld.getTitle());
			service.create(helloWorld);
			log.info(LOG_CREATE_BOOK, sportWorld.getTitle());
			service.create(sportWorld);
			log.info(LOG_CREATE_BOOK, divineWorld.getTitle());
			service.create(divineWorld);
		};
	}
}
