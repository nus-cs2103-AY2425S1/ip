package matcha.parser;
import matcha.command.AddTaskCommand;
import matcha.command.Command;
import matcha.command.DeleteTaskCommand;
import matcha.command.ExitCommand;
import matcha.command.ListTaskCommand;
import matcha.command.UpdateTaskCommand;
import matcha.exception.MatchaException;

public class Parser {
    public Command parse(String input) throws MatchaException {
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
            throw new MatchaException("Hmm, I'm sorry but I am unfamiliar with this command :(");
        }
    }
}
