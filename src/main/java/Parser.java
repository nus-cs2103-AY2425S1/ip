public class Parser {
    public Command parse(String input) throws DukeException {
        String[] inputWords = input.split(" ", 2);
        String commandType = inputWords[0];

        switch (commandType.toLowerCase()) {

        case "list":
            return new ListTaskCommand();

        case "mark", "unmark":
            return new UpdateTaskCommand(inputWords, commandType);

        case "deadline", "event", "todo":
            return new AddTaskCommand(inputWords, commandType);

        case "delete":
            return new DeleteTaskCommand(inputWords);

        case "bye":
            return new ExitCommand();

        default:
            throw new DukeException("Hmm, I'm sorry but I am unfamiliar with this command :(");
        }
    }
}
