package exception;

public class InvalidDataTypeException extends IllegalArgumentException {
    public InvalidDataTypeException() {
        super("Price must be numbers.");
    }
}

