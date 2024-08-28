package Exception;

public class AlphaException extends Exception {
    private final String description;
    
    public AlphaException(String description) {
        this.description = description;
    }
}
