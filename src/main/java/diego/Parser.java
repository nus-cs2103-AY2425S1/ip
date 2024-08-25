package diego;

/**
 * Parses user input commands and returns the appropriate Command object.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The user input string.
     * @return The appropriate Command object based on the user input.
     * @throws DiegoException If the input is invalid or unknown.
     */
    public Command parseCommand(String input) throws DiegoException {

        if (input.equals("bye")) {
            return new ExitCommand();

        } else if (input.equals("list")) {
            return new ListCommand();

        } else if (input.startsWith("mark")) {
            return new MarkCommand(parseIndex(input));

        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(parseIndex(input));

        } else if (input.startsWith("event")) {
            String[] data = input.split(" /from | /to ");
            if (data.length < 3) throw new NoDescriptionException();
            return new AddEventCommand(data[0].substring(6).trim(), data[1].trim(), data[2].trim());

        } else if (input.startsWith("todo")) {
            if (input.length() <= 5) throw new NoDescriptionException();
            return new AddTodoCommand(input.substring(5).trim());

        } else if (input.startsWith("deadline")) {
            String[] data = input.split(" /by");
            if (data.length < 2) throw new NoDescriptionException();
            return new AddDeadlineCommand(data[0].substring(9).trim(), data[1].trim());

        } else if (input.startsWith("delete")) {
            return new DeleteCommand(parseIndex(input));

        }else if (input.startsWith("find")) {
            return new FindCommand(input.substring(5).trim());

        } else {
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses the index from the user input string.
     *
     * @param input The user input string containing the index.
     * @return The parsed index as an integer.
     * @throws DiegoException If the index is not valid.
     */
    private int parseIndex(String input) throws DiegoException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new NoIndexException();
        }
    }
}
