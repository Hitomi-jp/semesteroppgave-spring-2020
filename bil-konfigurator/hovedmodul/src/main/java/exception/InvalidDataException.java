package exception;

public class InvalidDataException extends IllegalArgumentException {
    public InvalidDataException(String msg) {
        super(msg);
    }
}

