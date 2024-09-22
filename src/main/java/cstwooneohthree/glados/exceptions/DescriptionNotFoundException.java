package cstwooneohthree.glados.exceptions;

/**
 * DescriptionNotFoundException class when description does not exist.
 *
 * @author jayjay19630
 */
public class DescriptionNotFoundException extends GladosException {
    /**
     * Constructs DescriptionNotFoundException by calling the parent class.
     */
    public DescriptionNotFoundException() {
        super("Description for a task cannot be empty.");
    }
}
