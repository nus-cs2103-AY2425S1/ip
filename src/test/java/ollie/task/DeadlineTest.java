package ollie.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion(){
        assertEquals("[D][ ] Science Homework (by: 1 Aug 24)", new Deadline("Science Homework", "1 Aug 24").toString());
    }

    @Test
    public void testFormattedStringConversion(){
        assertEquals("D | 0 | Buy Grocery | tomorrow", new Deadline("Buy Grocery", "tomorrow").getFormattedString());
    }
}