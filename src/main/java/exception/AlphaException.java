package exception;

public class AlphaException extends Exception {
    private final String description;
    
    public AlphaException(String description) {
        this.description = description;
    }
}
