package prince.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.tasks.Deadline;

public class DeadlineTest {
    @Test
    public void constructor_validInputWithoutFormattedDate_success() {
        Deadline deadline = new Deadline("test method", "9 aug 6pm");
        assertEquals("test method", deadline.getDescription());
        assertEquals("9 aug 6pm", deadline.toFileFormat().split(" .. ")[3]);
    }

    @Test
    public void toString_validInputWithFormattedDate_success() {
        Deadline deadline = new Deadline("test method", "2024-08-31");
        String expected = "[D][ ]  test method (by: 31 Aug 2024)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void toString_validInputWithFormattedDateTime_success() {
        Deadline deadline = new Deadline("test method", "2024-08-31 1832");
        String expected = "[D][ ]  test method (by: 31 Aug 2024 6:32pm)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void toString_validInputWithoutFormattedDateTime_success() {
        Deadline deadline = new Deadline("test method", "sunday 5am");
        String expected = "[D][ ]  test method (by: sunday 5am)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void toFileFormat_validInput_success() {
        Deadline deadline = new Deadline("test method", "sunday 5am");
        String expected = "D .. 0 .. test method .. sunday 5am";
        assertEquals(expected, deadline.toFileFormat());
    }

    // test toString with no by

}
