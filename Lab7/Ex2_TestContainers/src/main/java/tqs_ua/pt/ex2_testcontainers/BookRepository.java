package tqs_ua.pt.ex2_testcontainers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

    public Book findByTitle(String title);
    public Book findAllByAuthor(String author);
    public List<Book> findAll();
}
