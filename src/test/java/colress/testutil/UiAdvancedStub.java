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
    private boolean throwsFirstException = false;
    private boolean throwsSecondException = false;

    public UiAdvancedStub() {
        super(new ColressStub());
    }

    /**
     * Constructs a stub class that will or will not throw exceptions depending on the arguments passed.
     */
    public UiAdvancedStub(boolean throwsFirstException, boolean throwsSecondException) {
        super(new ColressStub());
        this.throwsFirstException = throwsFirstException;
        this.throwsSecondException = throwsSecondException;
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
        if (throwsFirstException) {
            throw new EmptyInputException();
        }
    }

    @Override
    public void parseDate(String input) throws DateTimeParseException {
    }

    @Override
    public void parseTaskNumbers(String input, TaskList taskList)
            throws IndexOutOfBoundsException, NumberFormatException {
        if (throwsFirstException) {
            throw new IndexOutOfBoundsException();
        } else if (throwsSecondException) {
            throw new NumberFormatException();
        }
    }

    @Override
    public void parseTaskType(String input) throws UnknownTaskTypeException {
        if (throwsFirstException) {
            throw new UnknownTaskTypeException();
        }
    }

    @Override
    public void parseDescription(String input) throws EmptyInputException {
        if (throwsFirstException) {
            throw new EmptyInputException();
        }
    }

    @Override
    public void parseStartTime(String input) throws DateTimeParseException {
    }

    @Override
    public void parseEndTime(String input) throws EndTimeException, DateTimeParseException {
        if (throwsFirstException) {
            throw new EndTimeException();
        }
    }
}
