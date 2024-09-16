package secondmind;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    void processInput_byeCommand_success() {
        Parser parser = new Parser();
        String[] parsedInput = parser.processInput("bye");
        assertTrue(parsedInput[0].equals("bye"));
        assertTrue(parsedInput.length == 1);
    }

    @Test
    void processInput_validMarkCommand_success() {
        Parser parser = new Parser();
        String[] parsedInput = parser.processInput("mark 1");
        assertTrue(parsedInput[0].equals("mark"));
        assertTrue(parsedInput[1].equals("1"));
    }

    @Test
    void processInput_invalidMarkCommand_numberFormatExceptionThrown() {
        Parser parser = new Parser();
        assertThrows(NumberFormatException.class, () -> {
            parser.processInput("mark wrongFormat");
        });
    }

    @Test
    void processInput_validUnmarkCommand_success() {
        Parser parser = new Parser();
        String[] parsedInput = parser.processInput("unmark 1");
        assertTrue(parsedInput[0].equals("unmark"));
        assertTrue(parsedInput[1].equals("1"));
    }

    @Test
    void processInput_invalidUnmarkCommand_numberFormatExceptionThrown() {
        Parser parser = new Parser();
        assertThrows(NumberFormatException.class, () -> {
            parser.processInput("unmark wrongFormat");
        });
    }

    @Test
    void processInput_validDeleteCommand_success() {
        Parser parser = new Parser();
        String[] parsedInput = parser.processInput("delete 1");
        assertTrue(parsedInput[0].equals("delete"));
        assertTrue(parsedInput[1].equals("1"));
    }

    @Test
    void processInput_invalidDeleteCommand_numberFormatExceptionThrown() {
        Parser parser = new Parser();
        assertThrows(NumberFormatException.class, () -> {
            parser.processInput("delete wrongFormat");
        });
    }

    @Test
    void processInput_listCommand_success() {
        Parser parser = new Parser();
        String[] parsedInput = parser.processInput("list");
        assertTrue(parsedInput[0].equals("list"));
        assertTrue(parsedInput.length == 1);
    }

    @Test
    void processInput_taskCommand_success() {
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
}
