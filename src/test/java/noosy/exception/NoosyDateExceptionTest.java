package noosy.exception;

import noosy.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NoosyDateExceptionTest {

    @Test
    public void parseDeadline_invalidDateFormat_throwsNoosyDateException() {
        String input = "submit assignment /by 2022-30-01";
        assertThrows(NoosyDateException.class, () -> {
            Parser.parseDeadline(input);
        });
    }
}

