package ekud.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ekud.exceptions.EkudException;

public class EventTaskTest {
    @Test
    public void constructor_emptyDescription_exceptionThrown() {
        // CHECKSTYLE.OFF: SeparatorWrap
        EkudException emptyDescription = assertThrows(
                EkudException.class,
                () -> new EventTask("", "1/1/2024 0000", "31/12/2024 1159"));
        // CHECKSTYLE.ON: SeparatorWrap
        assertEquals(
                "Did you forget your EVENT?\nBecause you tried to make an event of nothing!",
                emptyDescription.getMessage());
    }


    @Test
    public void constructor_emptyDate_exceptionThrown() {
        String fromExpected = """
                Woah Woah! Calm down buddy!
                Could you first tell when this event starts using '/from'?""";

        String toExpected = """
                Dude, stop being overzealous! Surely this event doesn't last forever?
                Think hard about it and you can tell me when it ends again with '/to'.""";

        // CHECKSTYLE.OFF: SeparatorWrap
        EkudException emptyFrom = assertThrows(
                EkudException.class,
                () -> new EventTask("task name", "", "31/12/2024 1159"));
        // CHECKSTYLE.ON: SeparatorWrap
        assertEquals(fromExpected, emptyFrom.getMessage());

        // CHECKSTYLE.OFF: SeparatorWrap
        EkudException emptyTo = assertThrows(
                EkudException.class,
                () -> new EventTask("task name", "1/1/2024 0000", ""));
        // CHECKSTYLE.ON: SeparatorWrap
        assertEquals(toExpected, emptyTo.getMessage());
    }

    @Test
    public void constructor_invalidDates_exceptionThrown() {
        String expected = """
                Man... What's wrong with you!!
                Why can't you just follow the correct 'd/M/yyyy HHmm' date format!""";

        // CHECKSTYLE.OFF: SeparatorWrap
        EkudException wrongFromFormat = assertThrows(
                EkudException.class,
                () -> new EventTask("task name", "1 Jan 2024 12:00 AM", "31/12/2024 1159"));
        // CHECKSTYLE.ON: SeparatorWrap
        assertEquals(expected, wrongFromFormat.getMessage());

        // CHECKSTYLE.OFF: SeparatorWrap
        EkudException correctFromFormatInvalidDate = assertThrows(
                EkudException.class,
                () -> new EventTask("task name", "1/1/2024 3000", "31/12/2024 1159"));
        // CHECKSTYLE.ON: SeparatorWrap
        assertEquals(expected, correctFromFormatInvalidDate.getMessage());

        // CHECKSTYLE.OFF: SeparatorWrap
        EkudException wrongToFormat = assertThrows(
                EkudException.class,
                () -> new EventTask("task name", "1/1/2024 0000", "31 December 2024 1159"));
        // CHECKSTYLE.ON: SeparatorWrap
        assertEquals(expected, wrongToFormat.getMessage());

        // CHECKSTYLE.OFF: SeparatorWrap
        EkudException correctToFormatInvalidDate = assertThrows(
                EkudException.class,
                () -> new EventTask("task name", "1/1/2024 0000", "32/12/2024 1159"));
        // CHECKSTYLE.ON: SeparatorWrap
        assertEquals(expected, correctToFormatInvalidDate.getMessage());
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
            // set high priority
            assertEquals("[D][X] task name *HIGH PRIORITY* (by: Jan 01 2024, 12:00 AM)",
                    t.toString());
        } catch (EkudException e) {
            fail("Valid todo task construction failed");
        }
    }
}
