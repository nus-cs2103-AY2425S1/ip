package exceptions;

public class DescriptionNotFoundException extends GladosException {
    /**
     * Constructs DescriptionNotFoundException by calling the parent class.
     */
    public DescriptionNotFoundException() {
        super("Description for a task cannot be empty.");
    }
}