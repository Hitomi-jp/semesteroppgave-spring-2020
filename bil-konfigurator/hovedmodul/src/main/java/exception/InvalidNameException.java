package exception;

public class InvalidNameException extends IllegalArgumentException {
    public InvalidNameException() {
        super("Name must start with capital letter");
    }
}
