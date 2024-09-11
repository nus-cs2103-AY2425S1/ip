package dude;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dude.exception.DudeDateTimeFormatException;
import dude.exception.DudeException;
import dude.exception.DudeInvalidCommandException;
import dude.exception.DudeNullCommandException;

public class ParserTest {
    Parser parser;

    @BeforeEach
    public void setup() {
        parser = new Parser(new HashMap<>());
    }

    @Test
    public void testGetCommand_validCommand() throws DudeException {
        assertEquals(CommandType.TODO, parser.getCommand("todo read book"));
        assertEquals(CommandType.DEADLINE, parser.getCommand("deadline submit report"));
        assertEquals(CommandType.EVENT, parser.getCommand("event"));
    }

    @Test
    public void testGetCommand_emptyInput_throwsDudeNullCommandException() {
        DudeNullCommandException exception = assertThrows(DudeNullCommandException.class, () -> {
            parser.getCommand("");
        });

        assertEquals("Oops!! You did not enter anything.\n", exception.getMessage());
    }

    @Test
    public void testGetCommand_invalidCommand_throwsDudeInvalidCommandException() {
        DudeInvalidCommandException exception = assertThrows(DudeInvalidCommandException.class, () -> {
            parser.getCommand("hello");
        });

        assertEquals("Oops!! I don't know what does that mean.\n", exception.getMessage());
    }

    @Test
    public void testGetDescription_withDescription() throws DudeException {
        assertEquals("read book", Parser.getDescription("todo read book"));
        assertEquals("submit report /by lol",
                Parser.getDescription("deadline submit report /by lol"));
    }

    @Test
    public void testGetDescription_withoutDescription() throws DudeException {
        assertEquals("", Parser.getDescription("todo"));
    }

    @Test
    public void testGetDescription_emptyInput_throwsDudeNullCommandException() {
        DudeException exception = assertThrows(DudeNullCommandException.class, () -> {
            Parser.getDescription("");
        });

        assertEquals("Oops!! You did not enter anything.\n", exception.getMessage());
    }

    @Test
    public void testStringToDateTime_validFormat() throws DudeDateTimeFormatException {
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 8, 30, 1, 12);

        assertEquals(expectedDateTime, Parser.stringToDateTime("2024-08-30 01:12"));
    }

    @Test
    public void testStringToDateTime_invalidFormat_throwsDudeDateTimeFormatException() {
        DudeDateTimeFormatException exception = assertThrows(DudeDateTimeFormatException.class, () -> {
            Parser.stringToDateTime("2024/08/30 01:12");
        });

        assertEquals("Oops!! Please input the date and time with the following format: "
                + "\"yyyy-MM-dd HH:mm\", and with valid value.\n", exception.getMessage());
    }

    @Test
    public void testStringToDateTime_invalidValue_throwsDudeDateTimeFormatException() {
        DudeDateTimeFormatException exception = assertThrows(DudeDateTimeFormatException.class, () -> {
            Parser.stringToDateTime("2024-08-30 99:99");
        });

        assertEquals("Oops!! Please input the date and time with the following format: "
                + "\"yyyy-MM-dd HH:mm\", and with valid value.\n", exception.getMessage());
    }
}
