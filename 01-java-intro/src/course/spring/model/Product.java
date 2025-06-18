package course.spring.model;


import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Product implements Identifiable<Long> {
    private static Random random;
    static {
        random = new Random();
        random.setSeed(System.nanoTime());
    }
    public static String getNextId() {
        return UUID.randomUUID().toString();
    }
    private Long id;
    private String title;
    private double price;
    private String description;
    private Set<String> tags = Collections.emptySet();

    // Overloaded constructors
    // No args constructor
    public Product() {
    }

    public Product(long id) {
        this.id = id;
    }

    // Required args constructor
    public Product(String title, double price) {
        this.title = title;
        this.price = price;

    }

    public Product(String title, double price,
                   String description, Set<String> tags) {
        this(title, price);
        this.description = description;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        if(description == null){
            description = "id=" + id +
                    ", title='" + title + '\'' +
                    ", price=" + price;
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append(", tags=").append(tags);
        sb.append('}');
        return sb.toString();
    }

    public String format() {
        return String.format("| %4d | %-20.20s | %-20.20s | %4d | %6.2f | %-20.20s |",
                id, title, price, tags.stream().collect(Collectors.joining(", ")));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product book = (Product) o;

        return getId() != null ? getId().equals(book.getId()) : book.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

}
