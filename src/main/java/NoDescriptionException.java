public class NoDescriptionException extends DiegoException {
    public NoDescriptionException() {
        super(
                "I think you might have pressed Enter on accident.\n" +
                "Please use one of the following formats:\n" +
                " - todo sleep\n" +
                " - event project meeting /from Mon 2pm /to 4pm\n" +
                " - deadline return book /by Sunday\n" +
                "____________________________________________________________");
    }
}
