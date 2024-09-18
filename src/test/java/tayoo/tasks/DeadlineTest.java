package tayoo.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toString_anyString_returnsCorrectString() {
        String DeadlineName = "Return Book";
        String dueDate = "today";
        Deadline testDeadline1 = new Deadline(DeadlineName, dueDate, false);
        String expected = "[D][ ] Return Book (by: today)";
        String actual = testDeadline1.toString();
        assertEquals(expected, actual);

        Deadline testDeadline2 = new Deadline(DeadlineName, dueDate, true);
        expected = "[D][X] Return Book (by: today)";
        actual = testDeadline2.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void toTxt_anyDeadline_returnsCorrectTxtRepresentation() {
        assertDoesNotThrow( () -> {
            String DeadlineName = "Return Book";
            String dueDate = "today";
            Deadline testDeadline1 = new Deadline(DeadlineName, dueDate, false);
            String expected = "Deadline | false | Return Book | today";
            String actual = testDeadline1.toTxt();
            assertEquals(expected, actual);

            Deadline testDeadline2 = new Deadline(DeadlineName, dueDate, true);
            expected = "Deadline | true | Return Book | today";
            actual = testDeadline2.toTxt();
            assertEquals(expected, actual);
        });
    }

    @Test
    public void toTxt_dateTimeParse_success() {
        assertDoesNotThrow(() -> {
            String DeadlineName = "Return Book";
            String dueDate = "01-01-2024";
            Deadline testDeadline1 = new Deadline(DeadlineName, dueDate, false);
            String expected = "Deadline | false | Return Book | 2024-01-01T00:00";
            String actual = testDeadline1.toTxt();
            assertEquals(expected, actual);

            /* Can handle time and date parsing correctly */
            dueDate = "01-01-2024 1835";
            Deadline testDeadline2 = new Deadline(DeadlineName, dueDate, true);
            expected = "Deadline | true | Return Book | 2024-01-01T18:35";
            actual = testDeadline2.toTxt();
            assertEquals(expected, actual);

            /* Can handle time only parsing correctly */
            dueDate = "1835";
            Deadline testDeadline3 = new Deadline(DeadlineName, dueDate, true);
            expected = "Deadline | true | Return Book | 18:35";
            actual = testDeadline3.toTxt();
            assertEquals(expected, actual);
        });
    }
}
