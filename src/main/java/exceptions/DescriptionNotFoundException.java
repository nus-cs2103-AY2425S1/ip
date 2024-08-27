package exceptions;

public class DescriptionNotFoundException extends GladosException {
    public DescriptionNotFoundException() {
        super("Description for a task cannot be empty.");
    }
}