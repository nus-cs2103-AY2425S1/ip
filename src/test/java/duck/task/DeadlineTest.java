package duck.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    public void testStringConversion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate date = LocalDate.parse("2/12/2019", formatter);
        assertEquals("[D][ ] desc (by: Feb 12 2019)",
                new Deadline("desc", date).toString());
    }
}
