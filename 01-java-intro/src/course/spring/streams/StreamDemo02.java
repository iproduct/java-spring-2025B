package course.spring.streams;

import course.spring.model.Book;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static course.spring.model.MockBooks.MOCK_BOOKS;

public class StreamDemo02 {
    public static void main(String[] args) {

        var books = Arrays.stream(MOCK_BOOKS);
//        var compStringAlphabetic = Comparator.<String, String>comparing(String::toLowerCase);
        books
                .filter(book -> book.getTitle().toLowerCase().contains("java"))
                .map(Book::getTitle)
                .sorted(Comparator.<String, String>comparing(String::toLowerCase).reversed())
                .forEach(System.out::println);
    }
}
