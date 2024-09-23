package knight2103.command;

/**
 * Acts as an interpreter of the user inputs to execute bot's Commands.
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
     * with expected command verbs, and if the command format, in general, is invalid.
     * @throws ArrayIndexOutOfBoundsException If the user input does not match
     * the expected basic format which for certain commands, they require the description,
     * apart from the first word (command verb). Such exception thrown can be seen as a more specific kind of
     * InvalidCommandException.
     */
    public static Command parse(String fullCommand) throws InvalidCommandException {
        final int NUMBER_OF_COMMAND_PARTS = 2;
        String[] commandArray = fullCommand.split(" ", NUMBER_OF_COMMAND_PARTS);

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

        final int COMMAND_INDEX = 0;
        final int DESCRIPTION_INDEX = 1;

        try {
            switch (commandArray[COMMAND_INDEX]) {
            case COMMAND_LIST_INPUT:
                return new ListCommand();
            case COMMAND_TODO_INPUT:
                return new AddCommand(CommandVerb.TODO, commandArray[DESCRIPTION_INDEX]);
            case COMMAND_DEADLINE_INPUT:
                return new AddCommand(CommandVerb.DEADLINE, commandArray[DESCRIPTION_INDEX]);
            case COMMAND_EVENT_INPUT:
                return new AddCommand(CommandVerb.EVENT, commandArray[DESCRIPTION_INDEX]);
            case COMMAND_MARK_INPUT:
                return new ModifyCommand(CommandVerb.MARK, commandArray[DESCRIPTION_INDEX]);
            case COMMAND_UNMARK_INPUT:
                return new ModifyCommand(CommandVerb.UNMARK, commandArray[DESCRIPTION_INDEX]);
            case COMMAND_DELETE_INPUT:
                return new ModifyCommand(CommandVerb.DELETE, commandArray[DESCRIPTION_INDEX]);
            case COMMAND_FIND_INPUT:
                return new FindCommand(CommandVerb.FIND, commandArray[DESCRIPTION_INDEX]);
            case COMMAND_SORT_INPUT:
                return new SortCommand();
            case COMMAND_BYE_INPUT:
                return new ByeCommand();
            default:
                throw new InvalidCommandException("Invalid Command. Only valid command verbs are "
                        + "list, todo, deadline, event, mark, unmark, delete, find, sort, bye");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("There's an issue in the command format."
                    + "\nPlease check that it is <CommandVerb> <description> format");
        }
    }
}
