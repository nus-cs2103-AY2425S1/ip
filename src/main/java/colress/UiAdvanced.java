package colress;

import java.time.format.DateTimeParseException;

import colress.exception.EmptyInputException;
import colress.exception.EndTimeException;
import colress.exception.UnknownTaskTypeException;
import colress.tasklist.TaskList;

/**
 * An abstract class for the Advanced mode Ui of the Colress chatbot.
 * The Advanced mode Ui uses single-line command format with no prompts.
 */
public abstract class UiAdvanced extends Ui {
    public UiAdvanced(Colress colress) {
        super(colress);
    }

    public abstract String processInput(String input, TaskList taskList);
    public abstract void parseKeyword(String input) throws EmptyInputException;
    public abstract void parseDate(String input);
    public abstract void parseTaskNumbers(String input, TaskList taskList);
    public abstract void parseTaskType(String input) throws UnknownTaskTypeException;
    public abstract void parseDescription(String input) throws EmptyInputException;
    public abstract void parseStartTime(String input);
    public abstract void parseEndTime(String input) throws EndTimeException, DateTimeParseException;
}
