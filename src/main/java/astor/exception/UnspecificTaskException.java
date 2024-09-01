package astor.exception;

public class UnspecificTaskException extends AstorException {
    public UnspecificTaskException() {
        super("Please be clear on what to do!\n" +
                "Type \"list\" to see the list of astor.task,\n\n" +
                "\"mark\" or \"unmark\" to mark tasks, " +
                "\"todo\" (task info) to create todo astor.task,\n" +
                "\"deadline\" (task info) /by (deadline) to create deadline tasks,\n" +
                "\"event\" (task info) /from (start time) /to (end time) to create event tasks");
    }
}
