package ekud.task;

import ekud.exceptions.EkudException;
import jdk.jfr.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTaskTest {
    @Test
    public void constructor_emptyDescription_exceptionThrown() {
        EkudException emptyDescription = assertThrows(
                EkudException.class,
                () -> new EventTask("", "1/1/2024 0000", "31/12/2024 1159")
        );
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

        EkudException emptyFrom = assertThrows(
                EkudException.class,
                () -> new EventTask("task name", "", "31/12/2024 1159")
        );
        assertEquals(fromExpected, emptyFrom.getMessage());

        EkudException emptyTo = assertThrows(
                EkudException.class,
                () -> new EventTask("task name", "1/1/2024 0000", "")
        );
        assertEquals(toExpected, emptyTo.getMessage());
    }

    @Test
    public void constructor_invalidDates_exceptionThrown() {
        String expected = """
                Man... What's wrong with you!!
                Why can't you just follow the correct 'd/M/yyyy HHmm' date format!""";

        EkudException wrongFromFormat = assertThrows(
                EkudException.class,
                () -> new EventTask("task name", "1 Jan 2024 12:00 AM", "31/12/2024 1159")
        );
        assertEquals(expected, wrongFromFormat.getMessage());

        EkudException correctFromFormatInvalidDate = assertThrows(
                EkudException.class,
                () -> new EventTask("task name", "1/1/2024 3000", "31/12/2024 1159")
        );
        assertEquals(expected, correctFromFormatInvalidDate.getMessage());

        EkudException wrongToFormat = assertThrows(
                EkudException.class,
                () -> new EventTask("task name", "1/1/2024 0000", "31 December 2024 1159")
        );
        assertEquals(expected, wrongToFormat.getMessage());

        EkudException correctToFormatInvalidDate = assertThrows(
                EkudException.class,
                () -> new EventTask("task name", "1/1/2024 0000", "32/12/2024 1159")
        );
        assertEquals(expected, correctToFormatInvalidDate.getMessage());
    }

    @Test
    public void getSaveStringFormat() {
        try {
            Task t = new DeadlineTask("task name", "1/1/2024 0000");
            // incomplete task
            assertEquals("D | 0 | task name | 1/1/2024 0000", t.getSaveTaskString());
            t.markAsDone();
            // complete task
            assertEquals("D | 1 | task name | 1/1/2024 0000", t.getSaveTaskString());
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
        } catch (EkudException e) {
            fail("Valid todo task construction failed");
        }
    }
}
