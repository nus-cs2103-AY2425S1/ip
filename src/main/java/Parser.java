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
     * - "todo": Creates an AddCommand which creates a new ToDo task with the information provided by the user.
     * - "deadline": Creates an AddCommand which creates a new Deadline task with the information provided by the user.
     * - "event": Creates an AddCommand which creates a new Event task with the information provided by the user.
     * - Any other input: Creates a Command which will terminate the program.
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
        case "todo":
            return new AddCommand(new ToDo(inputs[1]));
        case "deadline":
            String[] deadlineString = inputs[1].split("/by");
            return new AddCommand(new Deadline(deadlineString[0].trim(), deadlineString[1].trim()));
        case "event":
            String[] eventString = inputs[1].split("/from|/to");
            return new AddCommand(new Event(eventString[0].trim(),eventString[1].trim(),eventString[2].trim()));
        default:
            return new Command() {
                @Override
                public void execute(TaskList tasks, Ui ui) {
                    ui.displayGoodbye();
                }
            };
        }
    }
}
