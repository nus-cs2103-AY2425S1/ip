public class BadDescriptionException extends Exception{
    public BadDescriptionException(String type) {
        super(String.format("Provide details for the %s!", type));
    }
}
