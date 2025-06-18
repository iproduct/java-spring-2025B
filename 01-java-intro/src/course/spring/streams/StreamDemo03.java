package course.spring.streams;

import course.spring.model.Book;

import java.util.Arrays;
import java.util.Comparator;

import static course.spring.model.MockBooks.MOCK_BOOKS;

public class StreamDemo03 {
    public static void main(String[] args) {

        var books = Arrays.stream(MOCK_BOOKS);
//        var compStringAlphabetic = Comparator.<String, String>comparing(String::toLowerCase);
        var totalJavaBookPrice = books
                .filter(book -> book.getTitle().toLowerCase().contains("java"))
                .mapToDouble(Book::getPrice)
                .average();
//                .reduce(0D, (acc, val) -> acc + val);

        System.out.printf("Total Java books price: %8.2f\n", totalJavaBookPrice.getAsDouble());
    }
}
