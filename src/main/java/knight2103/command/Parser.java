package knight2103.command;

/**
 * Acts as interpreter of the user Commands to be executed.
 */
public class Parser {
    /**
     * Interprets user input and creates a relevant command object.
     * If the input is not interpretable, exceptions will be thrown and a command object will
     * not be created.
     *
     * @param fullCommand The input command given which must be of String type.
     * @return the relevant Command object that corresponds to the user input, if input is valid.
     * @throws InvalidCommandException If the first word of user input does not match
     * with expected command verbs. Or in general, if the command format is invalid.
     * @throws ArrayIndexOutOfBoundsException If the user input does not match
     * the expected basic format which for certain commands, requires the description,
     * apart from the first word (command verb). Such exceptions can be seen as a more specific kind of
     * InvalidCommandException.
     */
    public static Command parse(String fullCommand) throws InvalidCommandException {
        String[] commandArray = fullCommand.split(" ", 2);
        final String COMMAND_LIST_INPUT = "list";
        final String COMMAND_TODO_INPUT = "todo";
        final String COMMAND_DEADLINE_INPUT = "deadline";
        final String COMMAND_EVENT_INPUT = "event";
        final String COMMAND_MARK_INPUT = "mark";
        final String COMMAND_UNMARK_INPUT = "unmark";
        final String COMMAND_DELETE_INPUT = "delete";
        final String COMMAND_FIND_INPUT = "find";
        final String COMMAND_SORT_INPUT = "sort";
        final String COMMAND_BYE_INPUT = "bye";

        try {
            switch (commandArray[0]) {
            case COMMAND_LIST_INPUT: // must be final
                return new ListCommand();
            case COMMAND_TODO_INPUT:
                return new AddCommand(CommandVerb.TODO, commandArray[1]);
            case COMMAND_DEADLINE_INPUT:
                return new AddCommand(CommandVerb.DEADLINE, commandArray[1]);
            case COMMAND_EVENT_INPUT:
                return new AddCommand(CommandVerb.EVENT, commandArray[1]);
            case COMMAND_MARK_INPUT:
                return new ModifyCommand(CommandVerb.MARK, commandArray[1]);
            case COMMAND_UNMARK_INPUT:
                return new ModifyCommand(CommandVerb.UNMARK, commandArray[1]);
            case COMMAND_DELETE_INPUT:
                return new ModifyCommand(CommandVerb.DELETE, commandArray[1]);
            case COMMAND_FIND_INPUT:
                return new FindCommand(CommandVerb.FIND, commandArray[1]);
            case COMMAND_SORT_INPUT:
                return new SortCommand();
            case COMMAND_BYE_INPUT:
                return new ByeCommand();
            default:
                throw new InvalidCommandException("Invalid Commands. Only valid command verbs are "
                        + "list, todo, deadline, event, mark, unmark, delete, find, sort, bye");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("There's an issue in the instruction format."
                    + "\nPlease check that it is <CommandVerb> <description> format");
        }
    }
}
