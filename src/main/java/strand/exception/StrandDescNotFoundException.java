package strand.exception;

/**
 * Exception thrown when a task description is not found.
 */
public class StrandDescNotFoundException extends StrandException {
    protected String part;

    public StrandDescNotFoundException(String part) {
        this.part = part;
    }

    @Override
    public String getMessage() {
        return this.part + " not found " + super.getMessage()
                + "\nPlease include a " + this.part + " for this task";
    }
}
