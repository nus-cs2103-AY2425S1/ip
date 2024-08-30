package nah.storage;

import nah.data.Task;
import nah.exceptions.NahException;
import nah.parser.Parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class DecoderTest {
    @Test
    public void decodingTest1() {
        try {
            assertEquals("[T] [ ] sleep", Decoder.decode("T | 0 | sleep").toString());
        } catch (NahException e) {
            fail("No NahException expected");
        }
    }

    @Test
    public void decodingTest2() {
        try {
            assertEquals("[E] [X] dinner (from: Oct 10 2025, 6:00 PM to: Oct 10 2025, 8:00 PM)",
                    Decoder.decode("E | 1 | dinner | Oct 10 2025, 6:00 PM | Oct 10 2025, 8:00 PM").toString());
        } catch (NahException e) {
            fail("No NahException expected");
        }
    }

    @Test
    public void exceptionTest1() {
        try {
            Decoder.decode("D | 2 | homework | Dec 12 2025, 8:00 PM");
            fail("NahException expected");
        } catch (NahException e) {
            assertEquals(" File contain invalid value\n", e.getMessage());
        }
    }

    @Test
    public void exceptionTest2() {
        try {
            Decoder.decode("D | 0 | homework | Dec-12-2025, 8:00 PM");
            fail("NahException expected");
        } catch (NahException e) {
            assertEquals(" File contain invalid value\n", e.getMessage());
        }
    }
}
