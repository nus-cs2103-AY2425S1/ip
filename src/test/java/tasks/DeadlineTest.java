package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duck.tasks.DateAndTime;
import duck.tasks.Deadline;

public class DeadlineTest {
    @Test
    public void createDeadline_unmarked_success() throws Exception {
        // toString
        Deadline testDeadline1 = new Deadline("test description", new DateAndTime("2024-08-30"));
        assertEquals("[D][ ] test description (by: 30 AUGUST 2024)", testDeadline1.toString());

        // save string
        Deadline testDeadline2 = new Deadline("test description", new DateAndTime("2024-08-30"));
        assertEquals("D,false,test description,2024-08-30", testDeadline2.getSaveString());
    }

    @Test
    public void createDeadline_marked_success() throws Exception {
        // toString
        Deadline testDeadline1 = new Deadline("test description", new DateAndTime("2024-08-30"));
        testDeadline1.markAsDone();
        assertEquals("[D][X] test description (by: 30 AUGUST 2024)", testDeadline1.toString());

        // save string
        Deadline testDeadline2 = new Deadline("test description", new DateAndTime("2024-08-30"));
        testDeadline2.markAsDone();
        assertEquals("D,true,test description,2024-08-30", testDeadline2.getSaveString());
    }
}
