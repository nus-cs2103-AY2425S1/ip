/**
 * Interprets and processes user commands.
 * Converts user input into actionable commands for the application to execute.
 *
 * @author Aaron
 */
public class Parser {
    /**
     * This method creates a new scanner object to process user input.
     */
    public static Command parse(String userInput) throws ElsaException {
            if (userInput.contains("bye")) {
                return new ByeCommand();
            } else if (userInput.contains("list")) {
                return new ListCommand();
            } else if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                return new MarkCommand(index);
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                return new UnmarkCommand(index);
            } else if (userInput.startsWith("todo")) {
                // Ensure that the user input is long enough to contain a description
                String description = userInput.length() > 5 ? userInput.substring(5).trim() : "";
                if (description.isEmpty()) {
                    String message = ElsaException.addSeparatorLines("Oh, it appears that the description of your " +
                            "ToDo item is empty...");
                    throw new ElsaException(message);
                }
                return new TodoCommand(description);
            } else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.split(" /by ", 2);
                // Check if there are two parts and if 'by' is in the correct format
                if (parts.length < 2 || parts[1].trim().length() != 16 ||
                        !parts[1].trim().matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
                    String message = ElsaException.addSeparatorLines("Oops, the format for the Deadline task is " +
                            "a bit off.\nPlease follow this format: deadline [activity] /by yyyy-mm-dd hh:mm");
                    throw new ElsaException(message);
                }
                String description = parts[0].substring(9).trim();
                String dueBy = parts[1].trim();
                // Check if the description is empty
                if (description.isEmpty()) {
                    String message = ElsaException.addSeparatorLines("Oh, it appears that the description of your " +
                            "Deadline task is empty...");
                    throw new ElsaException(message);
                }
                return new DeadlineCommand(description, dueBy);
            } else if (userInput.startsWith("event")) {
                String[] parts = userInput.split(" /from | /to ");
                return new EventCommand(parts[0].substring(6).trim(), parts[1].trim(), parts[2].trim());
            } else if (userInput.startsWith("delete")) {
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                return new DeleteCommand(index);
            } else {
                // Elsa will ask for clarification upon encountering any unrecognised input
                String message = ElsaException.addSeparatorLines("Sorry, I'm unable to perform this action: " +
                        userInput);
                throw new ElsaException(message);
            }
    }
}
