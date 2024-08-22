/**
 * The Parser class is used to interpret user input and create the appropriate
 * Command objects.
 */
public class Parser {
    /**
     * Parses the user input and returns the appropriate Command object.
     * This method interprets the user input and creates the appropriate
     * Command object. It can identify the following:
     * - "bye": Creates an ExitCommand which terminates the program.
     * - "list": Creates an ListCommand to display all tasks in the task list.
     * - Any other input: Creates an AddCommand which will create a new task with
     * the user input as its information.
     *
     * @param userInput The string input by the user.
     * @return A Command object with respect to the user's input.
     */
    public static Command parse(String userInput) {

        switch (userInput) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        default:
            return new AddCommand(new Task(userInput));
        }
    }
}
