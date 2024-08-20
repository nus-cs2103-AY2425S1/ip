public class UnknownTaskTypeException extends Exception {
    public UnknownTaskTypeException() {
        super("I do not recognise that task type."
                + "\nHere are the task types I recognise:"
                + "\ntodo: add a new task item to your list."
                + "\ndeadline: add a new task item with a deadline to your list."
                + "\nevent: add a new event to your list."
        );
    }
}
