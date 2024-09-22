package secondmind;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    void processInput_byeCommand_success() throws InvalidCommandFormatException {
        Parser parser = new Parser();
        String[] parsedInput = parser.processInput("bye");
        assertTrue(parsedInput[0].equals("bye"));
        assertTrue(parsedInput.length == 1);
    }

    @Test
    void processInput_validMarkCommand_success() throws InvalidCommandFormatException {
        Parser parser = new Parser();
        String[] parsedInput = parser.processInput("mark 1");
        assertTrue(parsedInput[0].equals("mark"));
        assertTrue(parsedInput[1].equals("1"));
    }

    @Test
    void processInput_invalidMarkCommand_success() throws InvalidCommandFormatException {
        Parser parser = new Parser();
        String[] parsedInput = parser.processInput("mark wrongFormat");
        assertTrue(parsedInput[0].equals("mark"));
        assertTrue(parsedInput[1].equals("wrongFormat"));
    }

    @Test
    void processInput_markCommandWithoutArguments_invalidCommandFormatExceptionThrown() {
        Parser parser = new Parser();
        assertThrows(InvalidCommandFormatException.class, () -> {
            parser.processInput("mark");
        });
    }

    @Test
    void processInput_validUnmarkCommand_success() throws InvalidCommandFormatException {
        Parser parser = new Parser();
        String[] parsedInput = parser.processInput("unmark 1");
        assertTrue(parsedInput[0].equals("unmark"));
        assertTrue(parsedInput[1].equals("1"));
    }

    @Test
    void processInput_invalidUnmarkCommand_InvalidCommandFormatExceptionThrown() throws InvalidCommandFormatException {
        Parser parser = new Parser();
        String[] parsedInput = parser.processInput("mark wrongFormat");
        assertTrue(parsedInput[0].equals("mark"));
        assertTrue(parsedInput[1].equals("wrongFormat"));
    }

    @Test
    void processInput_unmarkCommandWithoutArguments_invalidCommandFormatExceptionThrown() {
        Parser parser = new Parser();
        assertThrows(InvalidCommandFormatException.class, () -> {
            parser.processInput("unmark");
        });
    }

    @Test
    void processInput_validDeleteCommand_success() throws InvalidCommandFormatException {
        Parser parser = new Parser();
        String[] parsedInput = parser.processInput("delete 1");
        assertTrue(parsedInput[0].equals("delete"));
        assertTrue(parsedInput[1].equals("1"));
    }

    @Test
    void processInput_invalidDeleteCommand_success() throws InvalidCommandFormatException {
        Parser parser = new Parser();
        String[] parsedInput = parser.processInput("delete wrongFormat");
        assertTrue(parsedInput[0].equals("delete"));
        assertTrue(parsedInput[1].equals("wrongFormat"));
    }

    @Test
    void processInput_deleteCommandWithoutArguments_invalidCommandFormatExceptionThrown() {
        Parser parser = new Parser();
        assertThrows(InvalidCommandFormatException.class, () -> {
            parser.processInput("delete");
        });
    }

    @Test
    void processInput_listCommand_success() throws InvalidCommandFormatException {
        Parser parser = new Parser();
        String[] parsedInput = parser.processInput("list");
        assertTrue(parsedInput[0].equals("list"));
        assertTrue(parsedInput.length == 1);
    }

    @Test
    void processInput_taskCommand_success() throws InvalidCommandFormatException {
        Parser parser = new Parser();
        String[] parsedInput = parser.processInput("todo read book");
        assertTrue(parsedInput[0].equals("add"));
        assertTrue(parsedInput[1].equals("todo read book"));
        parsedInput = parser.processInput("deadline return book /by 2024-11-11T23:59:59");
        assertTrue(parsedInput[0].equals("add"));
        assertTrue(parsedInput[1].equals("deadline return book /by 2024-11-11T23:59:59"));
        parsedInput = parser.processInput("event project meeting /from 2024-11-11T16:00:00 /to 2024-11-11T19:00:00");
        assertTrue(parsedInput[0].equals("add"));
        assertTrue(parsedInput[1].equals("event project meeting /from 2024-11-11T16:00:00 /to 2024-11-11T19:00:00"));
    }

    @Test
    void processInput_validFindCommand_success() throws InvalidCommandFormatException {
        Parser parser = new Parser();
        String[] parsedInput = parser.processInput("find keyword");
        assertTrue(parsedInput[0].equals("find"));
        assertTrue(parsedInput[1].equals("keyword"));
    }

    // Test case for the "find" command with multiple keywords
    @Test
    void processInput_findCommandWithMultipleKeywords_success() throws InvalidCommandFormatException {
        Parser parser = new Parser();
        String[] parsedInput = parser.processInput("find keyword1 keyword2");
        assertTrue(parsedInput[0].equals("find"));
        assertTrue(parsedInput[1].equals("keyword1"));
        assertTrue(parsedInput[2].equals("keyword2"));
    }
}
