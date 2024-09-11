package tudee.parser;

import tudee.TudeeException;
import tudee.command.AddTaskCommand;
import tudee.command.ByeCommand;
import tudee.command.Command;
import tudee.command.DateCommand;
import tudee.command.DeleteCommand;
import tudee.command.FindCommand;
import tudee.command.ListCommand;
import tudee.command.MarkCommand;
import tudee.command.UnknownCommand;
import tudee.command.UnmarkCommand;
import tudee.task.Deadline;
import tudee.task.Events;
import tudee.task.ToDo;

/**
 * Parses user input into respective Command objects.
 * This class interprets the user's input and returns the appropriate Command based on the input string.
 */
public class Parser {

    // Indices used for parsing input strings
    private static final int COMMAND_INDEX = 0;
    private static final int OLD_DESCRIPTION_INDEX = 1;
    private static final int NEW_DESCRIPTION_INDEX = 0;
    private static final int DEADLINE_INDEX = 1;
    private static final int START_INDEX = 1;
    private static final int END_INDEX = 2;

    /**
     * Parses the given input string to generate the respective Command.
     * The input string is expected to contain a command followed by optional arguments depending on the command called.
     * Each command is mapped to a specific Command subclass.
     *
     * @param input the user input string to be parsed.
     * @return the corresponding Command based on the input.
     * @throws TudeeException if the input is invalid or not recognized.
     */
    public static Command parse(String input) throws TudeeException {
        String[] inputs = input.split(" ", 2);
        String command = inputs[COMMAND_INDEX];
        CommandType commandType = CommandType.fromString(command);

        // Handle each command type
        switch (commandType) {
            case LIST:
                return new ListCommand();

            case BYE:
                return new ByeCommand();

            case TODO:
                return new AddTaskCommand(new ToDo(inputs[OLD_DESCRIPTION_INDEX]));

            case DEADLINE:
                String[] deadlineDetails = inputs[OLD_DESCRIPTION_INDEX].split("/by ");
                return new AddTaskCommand(new Deadline(deadlineDetails[NEW_DESCRIPTION_INDEX], deadlineDetails[DEADLINE_INDEX]));

            case EVENT:
                String[] eventDetails = inputs[OLD_DESCRIPTION_INDEX].split("/from | /to ");
                return new AddTaskCommand(new Events(eventDetails[NEW_DESCRIPTION_INDEX], eventDetails[START_INDEX], eventDetails[END_INDEX]));

            case MARK:
                return new MarkCommand(Integer.parseInt(inputs[OLD_DESCRIPTION_INDEX]));

            case UNMARK:
                return new UnmarkCommand(Integer.parseInt(inputs[OLD_DESCRIPTION_INDEX]));

            case DELETE:
                return new DeleteCommand(Integer.parseInt(inputs[OLD_DESCRIPTION_INDEX]));

            case DATE:
                return new DateCommand(inputs[OLD_DESCRIPTION_INDEX]);

            case FIND:
                return new FindCommand(inputs[OLD_DESCRIPTION_INDEX]);

            default:
                return new UnknownCommand();
        }
    }
}