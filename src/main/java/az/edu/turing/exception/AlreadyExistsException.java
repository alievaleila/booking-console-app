package az.edu.turing.exception;

public class AlreadyExistsException extends RuntimeException {

    public String message;

    public AlreadyExistsException(String message) {
        this.message = message;
    }
}
