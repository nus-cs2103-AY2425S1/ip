package ScoobyDoo.Parser;
import static ScoobyDoo.Parser.DeadlineParser.getDeadlineDate;
import static ScoobyDoo.Parser.EventParser.getEventFromAndToDate;

import ScoobyDoo.Command.ByeCommand;
import ScoobyDoo.Command.DeadlineCommand;
import ScoobyDoo.Command.DeleteCommand;
import ScoobyDoo.Command.EventCommand;
import ScoobyDoo.Command.FindCommand;
import ScoobyDoo.Command.MarkCommand;
import ScoobyDoo.Command.RedoCommand;
import ScoobyDoo.Command.TodoCommand;
import ScoobyDoo.Command.UnMarkCommand;
import ScoobyDoo.Command.UndoCommand;
import ScoobyDoo.exception.InputFormatException;
import ScoobyDoo.Command.Command;
import ScoobyDoo.Command.ListCommand;
import ScoobyDoo.exception.NoMatchingInputException;

/**
 * The Parser class provides utility methods for parsing different types of task inputs.
 * It can extract descriptions and dates for events, deadlines, and todo tasks.
 */
public class Parser {
    /**
     * Given a command, return it's command type
     * @param input command
     * @return Command Type in String Format
     */
    private static String getCommandType(String input) {
        int index = input.indexOf(" ");
        String command = input;
        if (index != -1) {
            command = input.substring(0, index);
        }
        return command;
    }

    /**
     * Checks if the input string matches the pattern for a task.
     *
     * @param input The input string to be checked.
     * @return The number following the keyword.
     * @throws InputFormatException If the input format is incorrect.
     */
    private static int getIndexArgument(String input) throws InputFormatException{
        String[] inputArr = input.split(" ");
        if (inputArr.length < 2) {
            throw new InputFormatException("Please specify a number");
        }
        try {
            return Integer.parseInt(inputArr[1]);
        } catch (NumberFormatException e){
            throw new InputFormatException("Please input a number");
        }
    }

    /**
     * Extracts the description of n task from the input string.
     *
     * @param input The input string user typed.
     * @return The description of the task.
     * @throws InputFormatException If the input format is invalid.
     */
    private static String getDescription(String input) throws InputFormatException{
        String[] splitTask = input.split(" ", 2);
        if (splitTask.length != 2) {
            throw new InputFormatException("Oops! I need a description to save your task");
        }

        String[] splitDescription = splitTask[1].split("/",2);
        return splitDescription[0].trim();
    }

    public static Command parse(String input) throws InputFormatException{
        return switch (getCommandType(input)) {
                case "list" -> new ListCommand();
                case "delete" -> new DeleteCommand(getIndexArgument(input));
                case "mark" -> new MarkCommand(getIndexArgument(input));
                case "unmark" -> new UnMarkCommand(getIndexArgument(input));
                case "todo" -> new TodoCommand(getDescription(input));
                case "deadline" -> new DeadlineCommand(getDescription(input), getDeadlineDate(input));
                case "event" -> new EventCommand(getDescription(input), getEventFromAndToDate(input)[0],
                        getEventFromAndToDate(input)[1]);
                case "bye" -> new ByeCommand();
                case "find" -> new FindCommand(getDescription(input));
                case "undo" -> new UndoCommand();
                case "redo" -> new RedoCommand();
                default -> throw new NoMatchingInputException();
        };
    }
}
