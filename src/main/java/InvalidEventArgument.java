public class InvalidEventArgument extends FridayException {
    public InvalidEventArgument() {
        super("OOPS!!! The description of an event, the start date/time and the end date/time cannot be empty." + "\n"
                + "     Please enter a valid event command: event <description> /from <start date/time> /to <end date/time>");
    }
}
