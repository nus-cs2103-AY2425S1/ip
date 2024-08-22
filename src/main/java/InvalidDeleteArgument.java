public class InvalidDeleteArgument extends FridayException {
    public InvalidDeleteArgument() {
        super("OOPS!!! Please key in a valid task number to be deleted." + "\n"
                + "     Please enter a valid delete command: delete <task number>" + "\n"
                + "     To view the list of tasks, type 'list'");
    }
}
