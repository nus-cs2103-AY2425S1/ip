public class EmptyDescriptionException extends Exception {
    
    final private String message;

    public EmptyDescriptionException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
