package stelle.exception;

/**
 * Exception thrown when a Deadline or Event doesn't have a tag for its date.
 * @author Lee Ze Hao (A0276123J)
 */

public class NoDateTagException extends TaskException {
    public NoDateTagException() {
        super("""
                        You didn't add a tag for the date while creating this.\n
                        Deadline needs /by before date,
                        Event needs /from and /to before its dates! \n
                        """);
    }
}
