package gale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import gale.exception.GaleException;
import gale.parser.Parser;

public class ParserTest {

    @Test
    public void userInput_wrongCommandFormat_exceptionThrown() {
        try {
            Parser.parseTask("hello");
            fail();
        } catch (GaleException e) {
            assertEquals("Whoosh! The wind blew away your command. Please use 'todo', 'deadline' or 'event'.",
                    e.getMessage());
        }
    }

    @Test
    public void todoCommand_missingDescription_exceptionThrown() {
        try {
            Parser.parseToDo("todo");
            fail();
        } catch (GaleException e) {
            assertEquals("Oops! The wind blew away your to-do description. "
                    + "Please use: 'todo [priority] [description]'.",
                    e.getMessage());
        }
    }

    @Test
    public void deadlineCommand_missingBy_exceptionThrown() {
        try {
            Parser.parseDeadline("deadline go to the bar");
            fail();
        } catch (GaleException e) {
            assertEquals("Oops! The wind blew away your deadline description. "
                    + "Please use 'deadline [priority] [description] /by [date and time]'.",
                    e.getMessage());
        }
    }

    @Test
    public void eventCommand_missingFromAndTo_exceptionThrown() {
        try {
            Parser.parseEvent("event go to the bar");
        } catch (GaleException e) {
            assertEquals("Oops! The wind blew away your event description. "
                    + "Please use 'event [priority] [description] /from [start date] /to [end date]'.",
                    e.getMessage());
        }
    }

    @Test
    public void eventCommand_missingToOnly_exceptionThrown() {
        try {
            Parser.parseEvent("event go to the beach /from 1/1/2024 18:00");
        } catch (GaleException e) {
            assertEquals("Oops! The wind blew away your event description. "
                    + "Please use 'event [priority] [description] /from [start date] /to [end date]'.",
                e.getMessage());
        }
    }

    @Test
    public void todoCommand_spellingError_correctExceptionMessageDisplayed() {
        try {
            Parser.parseToDo("tod go to the bar");
        } catch (GaleException e) {
            assertEquals("Whoosh! The wind blew away your command. Please use 'todo', 'deadline' or 'event'.",
                e.getMessage());
        }
    }

    @Test
    public void parseDateTime_invalidDateFormat_exceptionThrown() {
        String invalidDate = "2023-12/31 18:00";
        assertThrows(DateTimeParseException.class, () -> Parser.parseDateTime(invalidDate));
    }

    @Test
    public void parseDate_invalidTimeFormat_exceptionThrown() {
        String invalidDate = "1/1/2024 1800";
        assertThrows(DateTimeParseException.class, () -> Parser.parseDateTime(invalidDate));
    }
}
