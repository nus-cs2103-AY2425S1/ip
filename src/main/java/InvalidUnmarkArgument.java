public class InvalidUnmarkArgument extends FridayException {
    public InvalidUnmarkArgument() {
        super("OOPS!!! Please key in a valid task number to unmark as done." + "\n"
                + "     Please enter a valid unmark command: unmark <task number>" + "\n"
                + "     To view the list of tasks, type 'list'");
    }
}
