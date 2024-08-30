package bob;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void testParseCommand_validCommand() {
        try {
            assertEquals(Bob.Command.BYE, Parser.parseCommand("bye"));
            assertEquals(Bob.Command.LIST, Parser.parseCommand("list"));
            assertEquals(Bob.Command.MARK, Parser.parseCommand("mark 1"));
            assertEquals(Bob.Command.UNMARK, Parser.parseCommand("unmark 1"));
            assertEquals(Bob.Command.TODO, Parser.parseCommand("todo read book"));
            assertEquals(Bob.Command.DEADLINE,
                    Parser.parseCommand("deadline Submit assignment /by 2024-12-12 2359"));
            assertEquals(Bob.Command.EVENT,
                    Parser.parseCommand("event Christmas party /from 2024-12-25 1800 /to 2024-12-35 2200"));
            assertEquals(Bob.Command.RELEVANT, Parser.parseCommand("relevant 2024-12-12"));
        } catch (BobException e) {
            fail("Exception should not be thrown for valid commands.");
        }
    }

    @Test
    public void testParseCommand_unknownCommand() throws BobException {
        assertEquals(Bob.Command.UNKNOWN, Parser.parseCommand("invalidcommand"));
    }

    @Test
    public void parseCommand_emptyInput_exceptionThrown() {
        BobException e = assertThrows(BobException.class, () -> Parser.parseCommand(""));
        assertEquals("You did not key in anything...", e.getMessage());
    }

    @Test
    public void testParseCommand_blankInput_exceptionThrown() {
        BobException e = assertThrows(BobException.class, () -> Parser.parseCommand(" "));
        assertEquals("You did not key in anything...", e.getMessage());
    }

    @Test
    public void testParseDateTime_validFormat() {
        String validFormatStr = "2024-12-12 1212";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 12, 12, 12, 12);
        try {
            assertEquals(expectedDateTime, Parser.parseDateTime(validFormatStr));
        } catch (BobException e) {
            fail("BobException should not be thrown for a valid date time format.");
        }
    }


    @Test
    public void parseDateTime_invalidTime_exceptionThrown() {
        String invalidTimeStr = "2024-12-12 99:99";
        BobException e = assertThrows(BobException.class, () -> Parser.parseDateTime(invalidTimeStr));
        assertEquals("Please provide the correct date and 24-hour time format: "
                + "yyyy-mm-dd HHmm"
                + "\nEg. 2024-08-27 1530 for Aug 27 2024 03.30pm", e.getMessage());
    }

    @Test
    public void parseDateTime_invalidDate_exceptionThrown() {
        String invalidDateStr = "2024-31-00 1600";
        BobException e = assertThrows(BobException.class, () -> Parser.parseDateTime(invalidDateStr));
        assertEquals("Please provide the correct date and 24-hour time format: "
                + "yyyy-mm-dd HHmm"
                + "\nEg. 2024-08-27 1530 for Aug 27 2024 03.30pm", e.getMessage());
    }

    @Test
    public void parseDateTime_invalidFormat_exceptionThrown() {
        String invalidFormatStr = "Dec 12 2024 4pm";
        BobException e = assertThrows(BobException.class, () -> Parser.parseDateTime(invalidFormatStr));
        assertEquals("Please provide the correct date and 24-hour time format: "
                + "yyyy-mm-dd HHmm"
                + "\nEg. 2024-08-27 1530 for Aug 27 2024 03.30pm", e.getMessage());
    }
}
