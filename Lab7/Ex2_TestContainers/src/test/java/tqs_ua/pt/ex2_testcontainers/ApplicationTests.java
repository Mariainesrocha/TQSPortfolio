package tqs_ua.pt.ex2_testcontainers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import static org.hamcrest.Matchers.is;

@Testcontainers
@SpringBootTest
class ApplicationTests {
    //docker run --name postgresql-container -p 5432:5432 -e POSTGRES_USER=ines -e POSTGRES_PASSWORD=pass -d postgres

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest")
            .withUsername("ines")
            .withPassword("pass")
            .withDatabaseName("ines");

    @Autowired
    private BookRepository bookRepository;

    // requires Spring Boot >= 2.2.6
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Test
    @Order(1)
    void insertOneBook() {
        Book book = new Book();
        book.setTitle("Testcontainers");
        book.setAuthor("Maria");

        bookRepository.save(book);
    }

    @Test
    @Order(2)
    void listBooks() {
        List<Book> books = bookRepository.findAll();
        MatcherAssert.assertThat(books.size(), is(2));
    }
}