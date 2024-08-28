package ollie.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion(){
        assertEquals("[D][ ] Science Homework (by: Aug 1 2024)", new Deadline("Science Homework", LocalDate.parse("2024-08-01")).toString());
    }

    @Test
    public void testFormattedStringConversion(){
        assertEquals("D | 0 | Buy Grocery | 2022-05-20", new Deadline("Buy Grocery", LocalDate.parse("2022-05-20")).getFormattedString());
    }
}