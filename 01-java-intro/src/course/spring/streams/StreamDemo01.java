package course.spring.streams;

import course.spring.model.Book;
import course.spring.model.MockBooks;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static course.spring.model.MockBooks.MOCK_BOOKS;

public class StreamDemo01 {
    public static void main(String[] args) {
        int[][] points = {{5, 6}, {3, 4}, {2, 1}, {9, 8}};
        Arrays.stream(points)
                .sorted(Comparator.comparing(p -> p[0]))
                .map(p -> Math.hypot(p[0], p[1]))
                .filter(d -> d <= 5)
//                .sorted()
                .forEach(d -> System.out.println(d));
        List<Book> books = Arrays.asList(MOCK_BOOKS);
//        Collections.sort(books, (b1, b2) -> (int)(b1.getPrice() - b2.getPrice()));
        Collections.sort(books, Comparator.comparing(b -> b.getAuthor().toLowerCase()));
//        books.sort(Comparator.comparing( Book::getAuthor));
        Comparator<Book> compareByYearTitlePrice = (b1, b2) -> {
            var compYear = b1.getYear() - b2.getYear();
            var compTitle = b1.getTitle().toLowerCase().compareTo(b2.getTitle().toLowerCase());
            var compPrice = (int)(b1.getPrice() - b2.getPrice());
            if(compYear != 0) {
                return compYear;
            }else if(compTitle != 0) {
                return  compTitle;
            } else {
                return compPrice;
            }
        };
        books.sort(compareByYearTitlePrice.reversed());
        books.forEach(System.out::println);
    }
}
