package barney.action;

import java.util.HashMap;

import barney.action.commands.Command;
import barney.action.commands.DeadlineCommand;
import barney.action.commands.DeleteCommand;
import barney.action.commands.EndCommand;
import barney.action.commands.EventCommand;
import barney.action.commands.ListCommand;
import barney.action.commands.MarkCommand;
import barney.action.commands.TodoCommand;
import barney.action.commands.FindCommand;
import barney.data.exception.BarneyException;
import barney.data.exception.InvalidCommandException;

/**
 * The CommandManager class is responsible for managing the execution of
 * commands in the Barney application. It provides functionality to parse
 * commands and create the corresponding command objects.
 */
public class CommandManager {

    /**
     * Represents the different types of commands that can be executed by the
     * CommandManager. Each command type has a command string, a regular expression
     * pattern, a command class, and optional command arguments.
     *
     * The command string represents the description of the command. The regular
     * expression pattern is used to validate the command input. The command class
     * is the class that implements the command logic. The command arguments are
     * optional and represent the required arguments for the command.
     *
     * This enum provides methods to convert a command string to its corresponding
     * CommandType and vice versa.
     *
     * @see CommandManager
     * @see Command
     */
    public static enum CommandType {
        LIST("list", "^list\\s*$", ListCommand.class), MARK("mark", "^mark\\s+\\d+\\s*$", MarkCommand.class, "index"),
        UNMARK("unmark", "^unmark\\s+\\d+\\s*", MarkCommand.class, "index"),
        TODO("todo", "^todo\\s+.+\\s*", TodoCommand.class, "description"),
        DEADLINE("deadline", "^deadline\\s+.+\\s+/by\\s+.+\\s*$", DeadlineCommand.class, "description", "by"),
        EVENT("event", "^event\\s+.+\\s+/from\\s+.+\\s+/to\\s+.+\\s*$", EventCommand.class, "description", "from",
                "to"),
        DELETE("delete", "^delete\\s+\\d+\\s*$", DeleteCommand.class, "index"),
        FIND("find", "^find\\s+.+\\s*$", FindCommand.class, "keyword"), BYE("bye", "^bye\\s*$", EndCommand.class);

        public final String commandStr;
        public final String commandRegex;
        public final String[] commandArgs;
        public final Class<? extends Command> commandClass;

        /**
         * Represents a type of command.
         *
         * @param description a brief description of the command type
         * @param rgx         a regular expression used to match the command type
         * @param command     the class representing the command type
         * @param args        the arguments required for the command type
         */
        CommandType(String description, String rgx, Class<? extends Command> command, String... args) {
            this.commandStr = description;
            this.commandRegex = rgx;
            this.commandClass = command;
            this.commandArgs = args;
        }

        /**
         * Returns a string representation of the CommandManager object.
         *
         * @return The command string associated with the CommandManager object.
         */
        @Override
        public String toString() {
            return this.commandStr;
        }

        /**
         * Converts a string value to the corresponding CommandType enum value.
         *
         * @param value the string value to be converted
         * @return the CommandType enum value corresponding to the given string value
         * @throws IllegalArgumentException if the given string value does not match any
         *                                  CommandType enum value
         */
        public static CommandType fromString(String value) {
            for (CommandType cmd : CommandType.values()) {
                if (cmd.commandStr.equals(value)) {
                    return cmd;
                }
            }
            throw new IllegalArgumentException("Invalid command type");
        }
    }

    /**
     * Manages the execution of commands.
     */
    public CommandManager() {
    }

    /**
     * Parses the given command type and line to create a command map.
     *
     * @param commandType The type of command to be parsed.
     * @param line        The input line to be parsed.
     * @return A HashMap containing the parsed command and its corresponding
     *         arguments.
     */
    private HashMap<String, String> parseArgument(CommandType commandType, String line) {
        HashMap<String, String> commandMap = new HashMap<String, String>();
        commandMap.put("command", commandType.toString());
        switch (commandType) {
        case LIST:
            break;
        case MARK:
            commandMap.put(commandType.commandArgs[0], line);
            break;
        case UNMARK:
            commandMap.put(commandType.commandArgs[0], line);
            break;
        case DELETE:
            commandMap.put(commandType.commandArgs[0], line);
            break;
        case TODO:
            commandMap.put(commandType.commandArgs[0], line);
            break;
        case DEADLINE:
            String[] deadlineArgs = line.split("/by");
            commandMap.put(commandType.commandArgs[0], deadlineArgs[0].trim());
            commandMap.put(commandType.commandArgs[1], deadlineArgs[1].trim());
            break;
        case EVENT:
            String[] eventArgs = line.split("/from");
            String[] timeArgs = eventArgs[1].split("/to");
            commandMap.put(commandType.commandArgs[0], eventArgs[0].trim());
            commandMap.put(commandType.commandArgs[1], timeArgs[0].trim());
            commandMap.put(commandType.commandArgs[2], timeArgs[1].trim());
            break;
        case FIND:
            commandMap.put(commandType.commandArgs[0], line);
            break;
        case BYE:
            break;
        }
        return commandMap;
    }

    /**
     * Retrieves a command based on the given input line.
     *
     * @param line The input line containing the command and its arguments.
     * @return The command object corresponding to the input line.
     * @throws BarneyException If the input line is invalid or there is an error
     *                         creating the command object.
     */
    public Command getCommand(String line) throws BarneyException {
        String command = line.split(" ")[0];
        CommandType commandType = CommandType.fromString(command);
        if (!line.matches(commandType.commandRegex)) {
            throw new InvalidCommandException("Invalid command, please try again!");
        }
        String argumentString = line.substring(command.length()).trim();
        HashMap<String, String> commandMap = parseArgument(commandType, argumentString);

        try {
            return commandType.commandClass.getConstructor(HashMap.class).newInstance(commandMap);
        } catch (Exception e) {
            throw new BarneyException("Error with class creation");
        }
    }
}
