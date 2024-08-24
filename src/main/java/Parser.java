public class Parser {

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

        } else {
            throw new UnknownCommandException();
        }
    }

    private int parseIndex(String input) throws DiegoException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new NoIndexException();
        }
    }
}
