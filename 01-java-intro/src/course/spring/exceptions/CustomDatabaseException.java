package course.spring.exceptions;

public class CustomDatabaseException extends Exception {
    public CustomDatabaseException() {
    }

    public CustomDatabaseException(String message) {
        super(message);
    }

    public CustomDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomDatabaseException(Throwable cause) {
        super(cause);
    }
}
