package sentinel.parser;

import sentinel.SentinelException;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseStringToTask_validTask_success() {
        assertEquals("[D][ ] hey (by: Feb 12 2009)", Parser.parseStringToTask("1. [D][ ] hey (by: Feb 12 2009)").toString());
    }

    @Test
    public void parseStringToTask_corruptedTask_nullOutput() {
        assertNull(Parser.parseStringToTask("1. [D][ hey (by: Feb 12 webrr)"));
    }

    @Test
    public void parseStringToDate_validString_success() {
        try {
            assertEquals("Dec 12 2009", Parser.parseStringToDate("12/12/2009")
                                                       .format(DateTimeFormatter.ofPattern(Parser.DATE_OUTPUT_PATTERN)));
        } catch (SentinelException e) {
            fail();
        }
    }

    @Test
    public void parseStringToDate_invalidString_exceptionThrown() {
        try {
            assertEquals("Dec 12 2009", Parser.parseStringToDate("12/13/2009")
                    .format(DateTimeFormatter.ofPattern(Parser.DATE_OUTPUT_PATTERN)));
            fail();
        } catch (SentinelException e) {
            assertEquals("Invalid date time format, I can only read formats in dd/M/yyyy pattern!", e.getMessage());
        }
    }
}

