package JayTest.task;

import jay.task.DeadlineTask;
import jay.task.Task;
import org.junit.jupiter.api.Test;
import jay.parser.InvalidDateException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTaskTest {
    @Test
    public void testToString() {
        try {
            DeadlineTask deadlineTask = new DeadlineTask("return book", false, Task.Priority.Low,
                    "21-08-2021");
            assertEquals("[D][ ] return book { Priority: Low } (by: 21 Aug 2021)", deadlineTask.toString());

            deadlineTask.markAsDone();
            assertEquals("[D][X] return book { Priority: Low } (by: 21 Aug 2021)", deadlineTask.toString());

            deadlineTask.markAsNotDone();
            assertEquals("[D][ ] return book { Priority: Low } (by: 21 Aug 2021)", deadlineTask.toString());
        } catch (InvalidDateException e) {
            fail();
        }
    }

    @Test
    public void testSimpleFormat() {
        try {
            DeadlineTask deadlineTask = new DeadlineTask("return book", false, Task.Priority.Low,
                    "21-08-2021");
            assertEquals("D | 0 | return book | Low | 21-08-2021", deadlineTask.getSimpleFormat());

            deadlineTask.markAsDone();
            assertEquals("D | 1 | return book | Low | 21-08-2021", deadlineTask.getSimpleFormat());

            deadlineTask.markAsNotDone();
            assertEquals("D | 0 | return book | Low | 21-08-2021", deadlineTask.getSimpleFormat());
        } catch (InvalidDateException e) {
            fail();
        }
    }
}
