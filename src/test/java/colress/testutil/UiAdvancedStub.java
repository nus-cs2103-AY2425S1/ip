package colress.testutil;

import java.time.format.DateTimeParseException;

import colress.Chatbot;
import colress.UiAdvanced;
import colress.exception.EmptyInputException;
import colress.exception.EndTimeException;
import colress.exception.UnknownTaskTypeException;
import colress.tasklist.TaskList;

/**
 * A stub class for the UiAdvanced class for testing purposes.
 */
public class UiAdvancedStub extends UiAdvanced {
    public UiAdvancedStub() {
        super(new ColressStub());
    }

    public UiAdvancedStub(Chatbot colress) {
        super(colress);
    }

    @Override
    public String processInput(String input, TaskList taskList) {
        return "This method should not be called";
    }

    @Override
    public void parseKeyword(String input) throws EmptyInputException {
    }

    @Override
    public void parseDate(String input) {
    }

    @Override
    public void parseTaskNumbers(String input, TaskList taskList) {
    }

    @Override
    public void parseTaskType(String input) throws UnknownTaskTypeException {
    }

    @Override
    public void parseDescription(String input) throws EmptyInputException {
    }

    @Override
    public void parseStartTime(String input) {
    }

    @Override
    public void parseEndTime(String input) throws EndTimeException, DateTimeParseException {
    }
}
