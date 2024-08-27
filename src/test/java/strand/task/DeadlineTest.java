package strand.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import strand.exception.StrandException;

import java.util.Date;

public class DeadlineTest {
    @Test
    public void testValidDeadlineToString() throws StrandException {
        Task newTask = new Deadline("return book", "2/12/2019 1800");
        assertEquals("[D][ ] return book (by: Dec 02 2019, 18:00)", newTask.toString());

        newTask.markAsDone();
        assertEquals("[D][X] return book (by: Dec 02 2019, 18:00)", newTask.toString());

        newTask.markAsNotDone();
        assertEquals("[D][ ] return book (by: Dec 02 2019, 18:00)", newTask.toString());

        assertEquals("[D][ ] read (by: today)", new Deadline("read", "today").toString());
        assertEquals(String.format("[D][ ] read (by: %s)", new Date(0)),
                new Deadline("read", new Date(0).toString()).toString());
    }

    @Test
    public void testMissingFields() {
        try {
            new Deadline("", "tomorrow");
        } catch (StrandException e) {
            assertEquals("Description not found (×_×;）\n"
                    + "Please include a Description for this task", e.getMessage());
        }
    }

}
