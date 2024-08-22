public class TaskFieldException extends OysterException {
    public final String field;

    public TaskFieldException(String field) {
        super(String.format("[%s] %s", "Invalid field", field));
        this.field = field;
    }
}
