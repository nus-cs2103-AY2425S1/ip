public class InvalidMarkArgument extends FridayException {
    public InvalidMarkArgument() {
        super("OOPS!!! Please key in a valid task number to mark as done." + "\n"
                + "     Please enter a valid mark command: mark <task number>" + "\n"
                + "     To view the list of tasks, type 'list'");
    }
}
