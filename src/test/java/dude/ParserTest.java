package dude;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dude.exception.DudeDateTimeFormatException;
import dude.exception.DudeException;
import dude.exception.DudeInvalidCommandException;
import dude.exception.DudeInvalidDefineException;
import dude.exception.DudeNullCommandException;

public class ParserTest {
    private Parser parser;

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

        assertEquals("Oops!! Are you typing in a new invisible font?", exception.getMessage());
    }

    @Test
    public void testGetCommand_invalidCommand_throwsDudeInvalidCommandException() {
        DudeInvalidCommandException exception = assertThrows(DudeInvalidCommandException.class, () -> {
            parser.getCommand("hello");
        });

        assertEquals("Oops!! I don't know what that means. What are you trying to do?",
                exception.getMessage());
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

        assertEquals("Oops!! Are you typing in a new invisible font?", exception.getMessage());
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

        assertEquals("Oops!! What is that? Input the date and time with the following format: "
                + "\"yyyy-MM-dd HH:mm\", and with valid value.", exception.getMessage());
    }

    @Test
    public void testStringToDateTime_invalidValue_throwsDudeDateTimeFormatException() {
        DudeDateTimeFormatException exception = assertThrows(DudeDateTimeFormatException.class, () -> {
            Parser.stringToDateTime("2024-08-30 99:99");
        });

        assertEquals("Oops!! What is that? Input the date and time with the following format: "
                + "\"yyyy-MM-dd HH:mm\", and with valid value.", exception.getMessage());
    }

    @Test
    public void testDefineShortcut_validInput() throws DudeInvalidDefineException {
        assertEquals(CommandType.LIST, parser.defineShortcut("l", "list"));
        assertEquals(CommandType.FIND, parser.defineShortcut("cari", "find"));
        assertEquals(CommandType.DEFINE, parser.defineShortcut("def", "DEfiNE"));

        HashMap<String, CommandType> shortcutMap = parser.getShortcutMap();

        assertEquals(3, shortcutMap.size());
        assertTrue(shortcutMap.containsKey("cari"));
        assertFalse(shortcutMap.containsKey("lst"));
    }

    @Test
    public void testDefineShortcut_invalidShortcut_throwsDudeInvalidDefineException() {
        DudeInvalidDefineException exception = assertThrows(DudeInvalidDefineException.class, () -> {
            parser.defineShortcut("find", "define");
        });

        assertEquals("Oops!! You can't define a command as shortcut!", exception.getMessage());
    }

    @Test
    public void testDefineShortcut_invalidCommand_throwsDudeInvalidDefineException() {
        DudeInvalidDefineException exception = assertThrows(DudeInvalidDefineException.class, () -> {
            parser.defineShortcut("gg", "help");
        });

        assertEquals("Oops!! \"help\" is not a valid command.", exception.getMessage());
    }

    @Test
    public void testDeleteShortcut_validInput() throws DudeException {
        parser.defineShortcut("td", "todo");
        parser.defineShortcut("mk", "mark");
        parser.defineShortcut("dl", "deadline");

        HashMap<String, CommandType> shortcutMap = parser.getShortcutMap();

        assertEquals(3, shortcutMap.size());

        parser.deleteShortcut("mk");

        assertEquals(2, shortcutMap.size());
        assertTrue(shortcutMap.containsKey("td"));
        assertFalse(shortcutMap.containsKey("mk"));
    }
}
