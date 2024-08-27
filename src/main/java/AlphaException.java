public class AlphaException extends Exception {
    private final String description;
    
    AlphaException(String description) {
        this.description = description;
    }
}
