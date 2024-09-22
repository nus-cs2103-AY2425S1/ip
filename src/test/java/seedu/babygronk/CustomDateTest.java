package seedu.babygronk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import babygronk.CustomDate;

public class CustomDateTest {

    @Test
    public void noDate() {
        assertEquals(new CustomDate(null).unparseDate(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HHmm")));
    }

    @Test
    public void invalidDate() {
        assertEquals(new CustomDate("212/213/213 21321").unparseDate(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HHmm")));
    }

    @Test
    public void testValidDateWithDashFormat() {
        CustomDate customDate = new CustomDate("31-12-2025 0000");
        assertEquals("31/12/2025 0000", customDate.unparseDate());
    }

    @Test
    public void testToStringFormat() {
        CustomDate customDate = new CustomDate("11/09/2000 1500");
        String expected = "11th of September 2000 at 03:00 PM";
        assertEquals(expected, customDate.toString());
    }

}
