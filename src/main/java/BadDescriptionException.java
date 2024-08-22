public class BadDescriptionException extends Exception{
    public BadDescriptionException(TaskTypes type) {
        super(String.format("Provide details for the %s!", type.getDescription()));
    }
}
