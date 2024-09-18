package justbot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import justbot.command.Command;
import justbot.command.DeadlineCommand;
import justbot.command.EventCommand;
import justbot.command.TodoCommand;
import justbot.exception.JustbotException;


public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void testParseCommand_validTodoCommand() throws JustbotException {
        String input = "todo play football";
        Command result = parser.parseCommand(input);
        assertTrue(result instanceof TodoCommand);
        assertEquals("play football", result.getTask().getTaskDescription());
    }

    @Test
    public void testParseCommand_validDeadlinecommand() throws JustbotException {
        String input = "deadline run /by 31/12/2024 00:00";
        Command result = parser.parseCommand(input);
        assertTrue(result instanceof DeadlineCommand);
        assertEquals("run", result.getTask().getTaskDescription());
    }

    @Test
    public void testParseCommand_validEventcommand() throws JustbotException {
        String input = "event work on iP /from  31/12/2024 00:00 /to 01/01/2025 00:00";
        Command result = parser.parseCommand(input);
        assertTrue(result instanceof EventCommand);
        assertEquals("work on iP", result.getTask().getTaskDescription());
    }

    @Test
    public void testParseCommand_invalidEventCommandEndDateTime() throws JustbotException {
        String input = "event work on iP /from  31/12/2050 00:00 /to 01/01/2025 00:00";
        JustbotException exception = assertThrows(JustbotException.class, () -> {
            parser.parseCommand(input);
        });
        assertEquals("Hey man, why is the end date and time before the start date and time?", exception.getMessage());
    }

    @Test
    public void testParseCommand_invalidEventCommandFormat() throws JustbotException {
        String input = "event /from 31/12/2050 00:00 /to 01/01/2060 00:00";
        JustbotException exception = assertThrows(JustbotException.class, () -> {
            parser.parseCommand(input);
        });
        assertEquals("Hey man, you can't leave the description blank!", exception.getMessage());
    }


    @Test
    public void testParseDateTime_validDateTime() throws JustbotException {
        String dateTimeStr = "31/12/2024 23:59";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 12, 31, 23, 59);
        LocalDateTime result = parser.parseDateTime(dateTimeStr);
        assertEquals(expectedDateTime, result);
    }

    @Test
    public void testParseDateTime_invalidDateTimeFormat() {
        String dateTimeStr = "2024-12-31 23:59";
        JustbotException exception = assertThrows(JustbotException.class, () -> {
            parser.parseDateTime(dateTimeStr);
        });
        assertEquals("Hey man you provided an invalid date and time format. Please use [dd/MM/yyyy HH:mm].",
                exception.getMessage());
    }

    @Test
    public void testParseDateTime_invalidDateTime() {
        String dateTimeStr = "2024-02-31 23:59";
        JustbotException exception = assertThrows(JustbotException.class, () -> {
            parser.parseDateTime(dateTimeStr);
        });
        assertEquals("Hey man you provided an invalid date and time format. Please use [dd/MM/yyyy HH:mm].",
                exception.getMessage());
    }

    @Test
    public void testParseDateTime_blankString() {
        String dateTimeStr = "   ";
        JustbotException exception = assertThrows(JustbotException.class, () -> {
            parser.parseDateTime(dateTimeStr);
        });
        assertEquals("Hey man you provided an invalid date and time format. Please use [dd/MM/yyyy HH:mm].",
                exception.getMessage());
    }
}
