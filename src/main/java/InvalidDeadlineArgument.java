public class InvalidDeadlineArgument extends FridayException {
    public InvalidDeadlineArgument() {
        super("OOPS!!! The description of a deadline and date/time to be done by cannot be empty. " + "\n"
                + "     Please enter a valid deadline command: deadline <description> /by <date>");
    }
}
