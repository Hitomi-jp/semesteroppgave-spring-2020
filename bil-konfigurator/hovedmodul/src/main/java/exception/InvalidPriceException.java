package exception;

public class InvalidPriceException extends IllegalArgumentException {
    public InvalidPriceException() {
        super("Price must be 0 or over");
}
    public InvalidPriceException(String price) {
        super("Not a valid number: " + price );
    }
}
