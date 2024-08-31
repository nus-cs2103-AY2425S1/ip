package commands;

import exceptions.MizzException;
import util.Storage;
import util.TaskList;
import util.Ui;

/**
 * Abstract class encapsulating a possible command.
 */
public abstract class Command {
    protected CommandTypes command;

    /**
     * Enum represnting all valid commands. <br>
     * Contains useful methods to interact with valid types.
     */
    public static enum CommandTypes {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE, FIND;

        /**
         * Method to convert a string to a commandType.
         *
         * @param type The type in string represetation.
         * @return The corresponding commandtype of the input.
         * @throws MizzException if an invalid type is used.
         */
        public static CommandTypes toCommandType(String type) throws MizzException {
            switch (type.strip().toLowerCase()) {
            case "todo":
                return TODO;
            case "deadline":
                return DEADLINE;
            case "event":
                return EVENT;
            case "list":
                return LIST;
            case "mark":
                return MARK;
            case "unmark":
                return UNMARK;
            case "delete":
                return DELETE;
            case "bye":
                return BYE;
            case "find":
                return FIND;
            default:
                throw new MizzException("Unknown command type: " + type);
            }
        }

        /**
         * Method to convert the command type to a command.
         *
         * @param type The command type to be converted.
         * @return A command to the input type.
         * @throws MizzException if invalid type is used.
         */
        public static Command toCommand(CommandTypes type) throws MizzException {
            switch (type) {
            case TODO:
                return new ToDoCommand(type);
            case DEADLINE:
                return new DeadlineCommand(type);
            case EVENT:
                return new EventCommand(type);
            case LIST:
                return new ListCommand(type);
            case MARK:
                // fall through
            case UNMARK:
                return new MarkCommand(type);
            case DELETE:
                return new DeleteCommand(type);
            case BYE:
                return new ByeCommand(type);
            case FIND:
                return new FindCommand(type);
            default:
                throw new MizzException("Unknown command type: " + type.toString());
            }
        }
    }

    /**
     * Factory method to create a Command from a string.
     *
     * @param type String representing a command.
     * @return The Command represented by the type.
     * @throws MizzException if the type is an invalid command.
     */
    public static Command of(String type) throws MizzException {
        CommandTypes cmdType = CommandTypes.toCommandType(type);
        return CommandTypes.toCommand(cmdType);
    }

    /**
     * Method to excute the command.
     *
     * @param tl The tasklist object.
     * @param ui The Ui object.
     * @param storage The storage object.
     * @param details An array of the details.
     * @throws MizzException if the excute operation fails.
     */
    public abstract void execute(TaskList tl, Ui ui, Storage storage, String... details)
            throws MizzException;

    @Override
    public String toString() {
        return "Command";
    }
}
