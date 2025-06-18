package course.spring.streams;

import course.spring.model.Book;

import java.util.Arrays;

import static course.spring.model.MockBooks.MOCK_BOOKS;

public class StreamDemo04 {
    public static void main(String[] args) {

        var books = Arrays.stream(MOCK_BOOKS);
//        var compStringAlphabetic = Comparator.<String, String>comparing(String::toLowerCase);
        books
                .filter(book -> book.getTitle().toLowerCase().contains("java"))

                .forEach(System.out::println);

    }
}
