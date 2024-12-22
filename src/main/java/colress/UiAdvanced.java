package colress;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import colress.command.AddCommand;
import colress.command.Command;
import colress.exception.EmptyInputException;
import colress.exception.EndTimeException;
import colress.exception.InvalidCommandFormatException;
import colress.exception.UnknownCommandException;
import colress.exception.UnknownTaskTypeException;

/**
 * Represents the Advanced mode Ui of the Colress chatbot.
 */
public final class UiAdvanced extends Ui {

    private Command currCommand;

    public UiAdvanced(Colress colress) {
        super(colress);
    }

    /**
     * Checks current status of the Ui, which is an indication of what type of input is being expected from the user,
     * and calls the corresponding processing method to process the user input.
     *
     * @param taskList A TaskList object that is passed to the processing methods that require them to correctly
     *                 process user input.
     */
    @Override
    public String processInput(String input, TaskList taskList) {
        try {
            parseCommand(input);
            return currCommand.execute(this, taskList);
        } catch (UnknownCommandException | InvalidCommandFormatException e) {
            setCommandType("error");
            return e.getMessage();
        }
    }

    private void parseCommand(String input) throws UnknownCommandException {
        this.currCommand = getParser().parseCommand(input);
        setCommandType(currCommand.toString());
    }

    /**
     * Parses the user input using the Parser Object and stores the keyword given by the user in the command Object.
     *
     * @param input The user input.
     */
    public void parseKeyword(String input) throws EmptyInputException {
        String keyword = getParser().getString(input);
        currCommand.initialise(keyword);
    }

    /**
     * Parses the user input using the Parser Object and stores the date given by the user in the command object.
     *
     * @param input The user input.
     */
    public void parseDate(String input) throws DateTimeParseException {
        LocalDate date = getParser().readDate(input);
        currCommand.initialise(date);
    }

    /**
     * Parses the user input using the Parser Object, and check if the given task number is valid.
     * If so, store the task number in the command object, call the command object's execute method and returns
     * its value. Else, throw exception
     *
     * @param input The user input.
     * @param taskList The TaskList to check task number validity on.
     */
    public void parseTaskNumbers(String input, TaskList taskList)
            throws IndexOutOfBoundsException, NumberFormatException {
        int[] result = getParser().getTaskNumber(input);
        for (int i: result) {
            if (taskList.isOutOfBounds(i)) {
                throw new IndexOutOfBoundsException();
            }
        }
        Arrays.sort(result);
        currCommand.initialise(result);
    }

    /**
     * Parses the user input using the Parser Object, and stores the task type given by the user in the
     * AddCommand Object. The method catches an IllegalArgumentException if user input is not a recognisable task type
     * and throws an UnknownTaskTypeException.
     */
    public void parseTaskType(String input) throws UnknownTaskTypeException {
        try {
            TaskType result = getParser().getTaskType(input);
            currCommand.initialise(result);
        } catch (IllegalArgumentException e) {
            throw new UnknownTaskTypeException();
        }
    }

    /**
     * Parses the user input using the Parser Object, and stores the task description given by the user in the
     * AddCommand Object.
     *
     * @param input The user input.
     */
    public void parseDescription(String input) throws EmptyInputException {
        input = getParser().getString(input);
        currCommand.initialise(input);
    }

    /**
     * Parses the user input using the Parser Object, and stores the starting time given by the user in the
     * command object.
     *
     * @param input The user input.
     */
    public void parseStartTime(String input) throws DateTimeParseException {
        LocalTime result = getParser().readTime(input);
        currCommand.initialise(result);
    }

    /**
     * Parses the user input using the Parser Object, and stores the ending time given by the user in the
     * command object.
     *
     * @param input The user input.
     */
    public void parseEndTime(String input) throws EndTimeException, DateTimeParseException {
        LocalTime result = getParser().readTime(input);
        // A typecast is required here because not all command objects have the getTaskType method.
        // The only command that will lead to this method being called is the AddCommand command.
        // Therefore, this is a safe typecast.
        AddCommand c = (AddCommand) currCommand;
        if (c.isInvalidEndTime(result)) {
            throw new EndTimeException();
        }
        c.initialise(result);
    }
}
