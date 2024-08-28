package stan;
import stan.commands.*;

public class Parser {

    public static Command parse(String fullCommand) throws StanException {
        String[] words = fullCommand.split(" ", 2);
        CommandType commandType = getCommandType(words[0]);

        try {
            switch (commandType) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkCommand(words);
            case UNMARK:
                return new UnmarkCommand(words);
            case TODO:
                return new TodoCommand(words);
            case DEADLINE:
                return new DeadlineCommand(words);
            case EVENT:
                return new EventCommand(words);
            case DELETE:
                return new DeleteCommand(words);
            default:
                throw new StanInvalidCommandException();
            }
        } catch (StanMissingArgumentException | StanInvalidArgumentException e) {
            throw new StanException("Invalid input: " + e.getMessage());
        }
    }

    private static CommandType getCommandType(String command) throws StanInvalidCommandException {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new StanInvalidCommandException();
        }
    }
}


