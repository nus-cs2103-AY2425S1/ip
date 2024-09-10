package joe.command;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import joe.Commands;
import joe.JoeException;
import joe.Parser;

/**
 * The {@code CommandFactory} class is responsible for registering all available commands and
 * providing a mechanism to retrieve the specific command class associated with a given command code.
 * The factory uses a {@link Map} to store the associations between {@link Commands} and
 * their respective command creation logic, implemented as {@link Function} instances.
 */
public class CommandFactory {
    // holds all registered commands
    private static final Map<Commands, Function<Parser, Command>> commands = new HashMap<>();

    static {
        // registers the commands
        commands.put(Commands.BYE, (args) -> new ExitCommand());
        commands.put(Commands.LIST, (args) -> new ListCommand());
        commands.put(Commands.TODO, (args) -> new TodoCommand(args.message));
        commands.put(Commands.DELETE, (args) -> new DeleteCommand(args.message));
        commands.put(Commands.MARK, (args) -> new MarkCommand(args.message));
        commands.put(Commands.UNMARK, (args) -> new UnmarkCommand(args.message));
        commands.put(Commands.FIND, (args) -> new FindCommand(args.message));
        commands.put(Commands.DEADLINE, (args) -> new DeadlineCommand(
                args.message, args.getParsedArgs(Parser.CommandArgs.BY)));
        commands.put(Commands.EVENT, (args) -> new EventCommand(
                args.message,
                args.getParsedArgs(Parser.CommandArgs.FROM),
                args.getParsedArgs(Parser.CommandArgs.TO)));
    }

    /**
     * Retrieves the {@link Command} object corresponding to the given command code.
     * The command code must be registered in the factory; otherwise, an exception is thrown.
     *
     * @param commandCode The {@link Commands} code registered with the factory.
     * @param parser The {@link Parser} object containing parsed input data needed to construct the command.
     * @return The specific {@link Command} object associated with the provided {@code commandCode}.
     * @throws JoeException if the command code is not registered or unknown.
     */
    public static Command getCommand(Commands commandCode, Parser parser) {
        Function<Parser, Command> commandFunction = commands.get(commandCode);
        if (commandFunction == null) {
            throw new JoeException("Unknown command");
        }
        return commandFunction.apply(parser);
    }
}
