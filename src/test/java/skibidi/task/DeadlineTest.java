package skibidi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import skibidi.exceptions.SkibidiException;
import skibidi.utils.MilitaryTime;


public class DeadlineTest {

    @Test
    public void testWriteToFile() throws SkibidiException {
        Deadline deadline = new Deadline("read book", "2021-08-24", new MilitaryTime("1800"));
        assertEquals("D | 0 | read book | 2021-08-24 | 6:00 PM", deadline.writeToFile());
    }

    @Test
    public void testToString() throws SkibidiException {
        Deadline deadline = new Deadline("read book", "2021-08-24", new MilitaryTime("1800"));
        assertEquals(" [D][ ] ReAd bOoK (by: 2021-08-24 6:00 PM)", deadline.toString());
    }
}
