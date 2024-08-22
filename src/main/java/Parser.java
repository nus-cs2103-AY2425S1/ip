/**
 * The Parser class is used to interpret user input and create the appropriate
 * Command objects.
 */
public class Parser {
    /**
     * Parses the user input and returns the appropriate Command object.
     * This method interprets the user input, splitting the input to determine the command
     * and respective index and creates the appropriate
     * Command object. It can identify the following:
     * - "bye": Creates an ExitCommand which terminates the program.
     * - "list": Creates an ListCommand to display all tasks in the task list.
     * - "mark": Creates an MarkCommand flagged as mark to mark the task as done.
     * - "unmark": Creates an MarkCommand flagged as unmark to mark the task as not done.
     * - Any other input: Creates an AddCommand which will create a new task with
     * the user input as its information.
     *
     * @param userInput The string input by the user.
     * @return A Command object with respect to the user's input.
     */
    public static Command parse(String userInput) {
        String[] inputs = userInput.split(" ", 2);
        String commandPhrase = inputs[0].toLowerCase();

        switch (commandPhrase) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            int markIndex = Integer.parseInt(inputs[1]) - 1;
            return new MarkCommand(markIndex, true);
        case "unmark":
            int unmarkIndex = Integer.parseInt(inputs[1]) - 1;
            return new MarkCommand(unmarkIndex, false);
        default:
            return new AddCommand(new Task(userInput));
        }
    }
}
