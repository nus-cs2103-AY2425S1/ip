package pixel;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateTimeParserTest {
    @Test
    public void toString_ddMMyyyy() throws Exception {
        DateTimeParser parser = new DateTimeParser("05-05-2022");
        assertEquals("05 May 2022", parser.toString());
    }

    @Test
    public void toString_yyyyMMdd() throws Exception {
        DateTimeParser parser = new DateTimeParser("2022-05-05");
        assertEquals("05 May 2022", parser.toString());
    }

    @Test
    public void toString_MMddyyyy() throws Exception {
        DateTimeParser parser = new DateTimeParser("05/05/2022");
        assertEquals("05 May 2022", parser.toString());
    }

    @Test
    public void toString_invalidDate() {
        assertThrows(PixelException.class, () -> {
            new DateTimeParser("invalid-date");
        });
    }

    @Test
    public void toString_emptyString() throws Exception {
        assertThrows(PixelException.class, () -> {
            new DateTimeParser("");
        });
    }
}
