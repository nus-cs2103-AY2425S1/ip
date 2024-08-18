public class DescriptionNotFoundException extends GladosException {
    public DescriptionNotFoundException() {
        super("GLaDOS: Description for a task cannot be empty.");
    }
}