public class InvalidSearchArgument extends FridayException {
    public InvalidSearchArgument() {
        super("OOPS!!! The search keyword cannot be empty." + "\n"
                + "     Please enter a valid search command: search <date>");
    }
}
