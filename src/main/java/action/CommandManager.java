package action;

import java.util.HashMap;

import action.commands.Command;
import action.commands.DeadlineCommand;
import action.commands.DeleteCommand;
import action.commands.EndCommand;
import action.commands.EventCommand;
import action.commands.ListCommand;
import action.commands.MarkCommand;
import action.commands.TodoCommand;
import data.exception.BarneyException;
import data.exception.InvalidCommandException;

public class CommandManager {

    public static enum CommandType {
        LIST("list", "^list\\s*$", ListCommand.class), MARK("mark", "^mark\\s+\\d+\\s*$", MarkCommand.class, "index"),
        UNMARK("unmark", "^unmark\\s+\\d+\\s*", MarkCommand.class, "index"),
        TODO("todo", "^todo\\s+.+\\s*", TodoCommand.class, "description"),
        DEADLINE("deadline", "^deadline\\s+.+\\s+/by\\s+.+\\s*$", DeadlineCommand.class, "description", "by"),
        EVENT("event", "^event\\s+.+\\s+/from\\s+.+\\s+/to\\s+.+\\s*$", EventCommand.class, "description", "from",
                "to"),
        DELETE("delete", "^delete\\s+\\d+\\s*$", DeleteCommand.class, "index"),
        BYE("bye", "^bye\\s*$", EndCommand.class);

        public final String commandStr;
        public final String commandRegex;
        public final String[] commandArgs;
        public final Class<? extends Command> commandClass;

        CommandType(String description, String rgx, Class<? extends Command> command, String... args) {
            this.commandStr = description;
            this.commandRegex = rgx;
            this.commandClass = command;
            this.commandArgs = args;
        }

        @Override
        public String toString() {
            return this.commandStr;
        }

        public static CommandType fromString(String value) {
            for (CommandType cmd : CommandType.values()) {
                if (cmd.commandStr.equals(value)) {
                    return cmd;
                }
            }
            throw new IllegalArgumentException("Invalid command type");
        }
    }

    public CommandManager() {
    }

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
        case BYE:
            break;
        }
        return commandMap;
    }

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
