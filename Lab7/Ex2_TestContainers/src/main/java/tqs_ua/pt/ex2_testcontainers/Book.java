package tqs_ua.pt.ex2_testcontainers;

import com.sun.istack.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String author;


    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book() { }


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Book title '" + this.title+ ", " + this.author;
    }
}