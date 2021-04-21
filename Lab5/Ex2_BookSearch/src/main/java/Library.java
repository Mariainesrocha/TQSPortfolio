import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private final List<Book> store = new ArrayList<>();

    public void addBook(final Book book) {
        store.add(book);
    }

    public List<Book> findBooks(String atribute, List<Object> values) {
        if (atribute.equals("date")){
            Date from = (Date) values.get(0);
            Date end2 = (Date) values.get(1);

            return store.stream().filter(book -> { return  from.before(book.getPublished()) && end2.after(book.getPublished());}).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
        } else if (atribute.equals("title"))
            return store.stream().filter(book -> {
                return book.getTitle().contains((String)values.get(0));}).collect(Collectors.toList());
        else if (atribute.equals("author"))
            return store.stream().filter(book -> { return book.getAuthor().equalsIgnoreCase((String)values.get(0));}).collect(Collectors.toList());
        else
            System.out.println("Error with search atributes");
            return new ArrayList<>();
    }
}