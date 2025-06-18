package course.spring.model;


import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Book extends Product {
    private String author;
    private int year;
    private String publisher;
    private String description;

    // Overloaded constructors
    // No args constructor
    public Book() {
    }

    public Book(long id) {
        super(id);
    }

    // Required args constructor
    public Book(String title, String author, int year, String publisher, double price) {
        super(title, price);
        this.author = author;
        this.year = year;
        this.publisher = publisher;
    }

    public Book(String title, String author, int year, String publisher, double price,
                String description, Set<String> tags) {
        super(title, price, description, tags);
        this.author = author;
        this.publisher = publisher;
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String getDescription() {
        if(description == null){
            description = super.getDescription() +
                    ", author='" + author + '\'' +
                    ", publishingDate=" + year +
                    ", publisher='" + publisher + '\'';
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(getId());
        sb.append(", title='").append(getTitle()).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", year=").append(year);
        sb.append(", publisher='").append(publisher).append('\'');
        sb.append(", price=").append(getPrice());
        sb.append(", description='").append(description).append('\'');
        sb.append(", tags=").append(getTags());
        sb.append('}');
        return sb.toString();
    }

    public String format() {
        return String.format("| %4d | %-20.20s | %-20.20s | %4d | %6.2f | %-20.20s |",
                getId(), getTitle(), author, year, getPrice(), getTags().stream().collect(Collectors.joining(", ")));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        return getId() != null ? getId().equals(book.getId()) : book.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

}
