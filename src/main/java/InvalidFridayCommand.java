public class InvalidFridayCommand extends FridayException {
    public InvalidFridayCommand(String command) {
        super("OOPS!!! I'm sorry, but this command is invalid: " + command + "\n"
                + "     Please enter a valid command: todo, deadline, event, list, mark, unmark, delete, bye");
    }
}
