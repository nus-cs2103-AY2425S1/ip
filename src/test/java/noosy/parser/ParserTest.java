package noosy.parser;

import noosy.commands.AddCommand;
import noosy.exception.NoosyDateTimeException;
import noosy.exception.NoosyException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parseEvent_validInput_success() throws NoosyException {
        String input = "attend seminar /from 2024-09-25 1500 /to 2024-09-25 1700";
        assertTrue(Parser.parseEvent(input) instanceof AddCommand);
    }

    @Test
    public void parseEvent_missingDuration_throwsNoosyException() {
        String input = "attend seminar /from 2024-09-25 1500"; // Missing "/to" part
        assertThrows(NoosyException.class, () -> {
            Parser.parseEvent(input);
        });
    }

    @Test
    public void parseEvent_invalidDateFormat_throwsNoosyDateTimeException() {
        String input = "attend seminar /from 2024-09-25 1500 /to 2024-09-25 99:99"; // Invalid time
        assertThrows(NoosyDateTimeException.class, () -> {
            Parser.parseEvent(input);
        });
    }
}

