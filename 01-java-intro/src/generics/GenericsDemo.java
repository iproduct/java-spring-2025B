package generics;

import course.spring.model.Book;
import course.spring.model.MockBooks;
import course.spring.model.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericsDemo {
    public static double claculateTotal(Collection<? extends Product> products) {
        var sum = 0D;
//        var newp = new T(); // Type erasure
        for(Product p: products){
            sum += p.getPrice();
        }
        return sum;
    }
    public static void addToCollection(Collection<? super Product> collection, Product p) {
        collection.add(p);
    }


    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        List<? extends Product> booksOrProducts= products;
        List<? super Product> consumerCollection = new ArrayList<Object>();
        books.add(MockBooks.MOCK_BOOKS[0]);
        products.add(MockBooks.MOCK_BOOKS[0]);
        products.add(MockBooks.MOCK_BOOKS[1]);
        System.out.printf("Total products price: %8.2f\n", GenericsDemo.<Product>claculateTotal(products));
        System.out.printf("Total books price: %8.2f\n", GenericsDemo.claculateTotal(books));
        System.out.printf("Total books price: %8.2f\n", GenericsDemo.claculateTotal(booksOrProducts));
        consumerCollection.add(new Product(1));
        consumerCollection.add(MockBooks.MOCK_BOOKS[0]);
    }
}
