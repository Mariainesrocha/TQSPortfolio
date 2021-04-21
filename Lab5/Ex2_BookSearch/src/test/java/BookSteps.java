import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookSteps {
    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})") //exemplo de como criar um tipo de dados novo a ser usado nas condicoes,data com yyyy (4) - mm (2) - dd (2)
    public Date iso8601Date(String year, String month, String day){
        LocalDateTime a = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0);
        return java.sql.Timestamp.valueOf(a);
    }

    @Given("(a|another) book with the title {string}, written by {string}, published in {iso8601Date}")
    public void book_with_the_title_written_by_published_in(String title, String author, Date published) {
        Book book = new Book(title, author, published);
        library.addBook(book);
    }

    @When("the customer searches for books published between {int} and {int}")
    public void the_customer_searches_for_books_published_between_and(final int yearFrom,final int yearTo) {
        result = library.findBooks("date",Arrays.asList(iso8601Date(String.valueOf(yearFrom),"01","01"), iso8601Date(String.valueOf(yearTo),"12","31")));
    }

    @Then("{int} books should have been found")
    public void  books_should_have_been_found(final int booksFound) {
        assertThat(result.size(), equalTo(booksFound));
    }

    @When("the customer searches for books whose title contains {string}")
    public void setSearchParametersT(String title) {
        result = library.findBooks("title", Arrays.asList(title));
    }

    @Then("Book {int} should have the title {string}")
    public void book_should_have_the_title(int position, String title) {
        assertThat(result.get(position - 1).getTitle(), equalTo(title));
    }

    @When("the customer searches for books whose author contains {string}")
    public void setSearchParametersA(String author) {
        result = library.findBooks("author", Arrays.asList(author));
    }

    @Then("Book {int} author should be {string}")
    public void verifyBookAtPosition2(final int position, final String au) {
        assertThat(result.get(position - 1).getAuthor(), equalTo(au));
    }

/*


    */
}