public class SamException extends Exception {
    public SamException(String message) {
        super(message); 
    }

    public SamException() {
        super("Huh? I'm not quite sure what you mean by that.");
    }
}