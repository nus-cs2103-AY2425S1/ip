package ekud.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ekud.exceptions.EkudException;

public class DeadlineTaskTest {
    @Test
    public void constructor_emptyDescription_exceptionThrown() {
        // CHECKSTYLE.OFF: SeparatorWrap
        EkudException emptyDescription = assertThrows(
                EkudException.class,
                () -> new DeadlineTask("", "1/1/2024 0000"));
        // CHECKSTYLE.ON: SeparatorWrap
        assertEquals(
                "I'm sorry, but, nothing does not have a DEADLINE.\nTry giving me an actual task.",
                emptyDescription.getMessage());
    }


    @Test
    public void constructor_emptyDeadline_exceptionThrown() {
        // CHECKSTYLE.OFF: SeparatorWrap
        EkudException emptyDeadline = assertThrows(
                EkudException.class,
                () -> new DeadlineTask("task name", ""));
        // CHECKSTYLE.ON: SeparatorWrap
        assertEquals(
                """
                        Whoopsies!! Looks like you forgot your deadline!
                        I'll say this once: next time mark your deadline with '/by'.""",
                emptyDeadline.getMessage());
    }

    @Test
    public void constructor_invalidDeadline_exceptionThrown() {
        String expected = """
                Whoopsies!! It looks like you tried to pass a deadline that I cannot read!
                I'd recommend that you follow the 'd/M/yyyy HHmm' format. Or else...""";

        // CHECKSTYLE.OFF: SeparatorWrap
        EkudException wrongDeadlineFormat = assertThrows(
                EkudException.class,
                () -> new DeadlineTask("task name", "1 Jan 2024 12 AM"));
        // CHECKSTYLE.ON: SeparatorWrap
        assertEquals(expected, wrongDeadlineFormat.getMessage());

        // CHECKSTYLE.OFF: SeparatorWrap
        EkudException correctFormatInvalidDate = assertThrows(
                EkudException.class,
                () -> new DeadlineTask("task name", "1/13/2024 2200"));
        // CHECKSTYLE.ON: SeparatorWrap
        assertEquals(expected, correctFormatInvalidDate.getMessage());
    }

    @Test
    public void getSaveStringFormat() {
        try {
            Task t = new DeadlineTask("task name", "1/1/2024 0000");
            // incomplete task
            assertEquals("D | 0 | LOW | task name | 1/1/2024 0000", t.getSaveTaskString());
            t.markAsDone();
            // complete task
            assertEquals("D | 1 | LOW | task name | 1/1/2024 0000", t.getSaveTaskString());
            t.setPriority(Task.Priority.HIGH);
            // set high priority
            assertEquals("D | 1 | HIGH | task name | 1/1/2024 0000", t.getSaveTaskString());
        } catch (EkudException e) {
            fail("Valid deadline task construction failed");
        }
    }

    @Test
    public void toStringTest() {
        try {
            Task t = new DeadlineTask("task name", "1/1/2024 0000");
            // incomplete task
            assertEquals("[D][ ] task name (by: Jan 01 2024, 12:00 AM)", t.toString());
            t.markAsDone();
            // complete task
            assertEquals("[D][X] task name (by: Jan 01 2024, 12:00 AM)", t.toString());
            t.setPriority(Task.Priority.HIGH);
            // complete task
            assertEquals("[D][X] task name *HIGH PRIORITY* (by: Jan 01 2024, 12:00 AM)",
                    t.toString());
        } catch (EkudException e) {
            fail("Valid todo task construction failed");
        }
    }
}
