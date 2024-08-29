package task;

import org.junit.jupiter.api.Test;
import parser.InvalidDateException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTaskTest {
    @Test
    public void testToString() {
        try {
            DeadlineTask deadlineTask = new DeadlineTask("return book", false, "21-08-2021");
            assertEquals("[D][ ] return book (by: 21 Aug 2021)", deadlineTask.toString());

            deadlineTask.markAsDone();
            assertEquals("[D][X] return book (by: 21 Aug 2021)", deadlineTask.toString());

            deadlineTask.markAsNotDone();
            assertEquals("[D][ ] return book (by: 21 Aug 2021)", deadlineTask.toString());
        } catch (InvalidDateException e) {
            fail();
        }
    }

    @Test
    public void testSimpleFormat() {
        try {
            DeadlineTask deadlineTask = new DeadlineTask("return book", false, "21-08-2021");
            assertEquals("D | 0 | return book | 21-08-2021", deadlineTask.simpleFormat());

            deadlineTask.markAsDone();
            assertEquals("D | 1 | return book | 21-08-2021", deadlineTask.simpleFormat());

            deadlineTask.markAsNotDone();
            assertEquals("D | 0 | return book | 21-08-2021", deadlineTask.simpleFormat());
        } catch (InvalidDateException e) {
            fail();
        }
    }
}
