package strand.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import strand.exception.StrandException;

/**
 * The strand.task.DeadlineTest class is a JUnit test for the class strand.task.Deadline
 */
public class DeadlineTest {
    @Test
    public void testValidDeadlineToString() throws StrandException {
        Task newTask = new Deadline("return book", "2/12/2019 1800");
        assertEquals("[D][ ] return book (by: Dec 02 2019, 18:00)", newTask.toString());

        newTask.markAsDone();
        assertEquals("[D][X] return book (by: Dec 02 2019, 18:00)", newTask.toString());

        newTask.markAsNotDone();
        assertEquals("[D][ ] return book (by: Dec 02 2019, 18:00)", newTask.toString());

        assertEquals("[D][ ] read (by: Dec 21 1212, 00:00)",
                new Deadline("read", "21/12/1212").toString());
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

    @Test
    public void testIncorrectDateFormat() {
        try {
            new Deadline("description", "tomorrow");
        } catch (StrandException e) {
            assertEquals("tomorrow is not a valid date (×_×;）\n"
                    + "Please include a date of the correct format (e.g. 2/12/2019 1800)", e.getMessage());
        }
        try {
            new Deadline("description2", "2/12/2019");
        } catch (StrandException e) {
            assertEquals("tomorrow is not a valid date (×_×;）\n"
                    + "Please include a date of the correct format (e.g. 2/12/2019 1800)", e.getMessage());
        }
    }

}
