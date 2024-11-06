package ipman.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseCommand_emptyMessage_exceptionThrown() {
        // Inspired by https://stackoverflow.com/a/2935935/4428725
        assertThrows(KeywordNotRecognisedException.class, () -> {
            Parser.parseCommand("");
        });
    }

    @Test
    public void parseCommand_unknownMessage_exceptionThrown() {
        assertThrows(KeywordNotRecognisedException.class, () -> {
            Parser.parseCommand("gibberish gibberish");
        });
    }

    @Test
    public void parseCommand_markNoArgs_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () -> {
            Parser.parseCommand("mark");
        });
    }

    @Test
    public void parseCommand_unmarkNoArgs_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () -> {
            Parser.parseCommand("unmark");
        });
    }

    @Test
    public void parseCommand_todoNoArgs_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () -> {
            Parser.parseCommand("todo");
        });
    }

    // Deadline
    @Test
    public void parseCommand_deadlinePartialArgs_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () -> {
            Parser.parseCommand("deadline homework");
        });
    }

    @Test
    public void parseCommand_deadlineWrongArgName_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () -> {
            Parser.parseCommand("deadline homework /to 2024-08-01");
        });
    }

    @Test
    public void parseCommand_deadlineInvalidDateFormat_exceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> {
            Parser.parseCommand("deadline homework /by 01-08-2024");
        });

        assertThrows(DateTimeParseException.class, () -> {
            Parser.parseCommand("deadline homework /by 01/08/2024");
        });
    }

    @Test
    public void parseCommand_deadline_noExceptionThrown() {
        assertDoesNotThrow(() -> {
            Parser.parseCommand("deadline homework /by 2024-08-01");
        });
    }

    // Event
    @Test
    public void parseCommand_eventPartialArgs_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () -> {
            Parser.parseCommand("event school /from");
        });
    }

    @Test
    public void parseCommand_eventWrongArgName_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () -> {
            Parser.parseCommand("event school /start 2024-08-01 /to 2024-08-01");
        });
    }

    @Test
    public void parseCommand_event_noExceptionThrown() {
        assertDoesNotThrow(() -> {
            Parser.parseCommand("event school /from 2024-08-01 /to 2024-08-02");
        });
    }

    // Delete
    @Test
    public void parseCommand_deleteNoArgs_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () -> {
            Parser.parseCommand("delete");
        });
    }

    @Test
    public void parseCommand_deleteArgNotNumber_exceptionThrown() {
        assertThrows(NumberFormatException.class, () -> {
            Parser.parseCommand("delete abc");
        });
    }

    @Test
    public void parseCommand_delete_noExceptionThrown() {
        assertDoesNotThrow(() -> {
            Parser.parseCommand("delete 3");
        });
    }

    @Test
    public void parseCommand_findNoArgs_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () -> {
            Parser.parseCommand("find");
        });
    }
}
