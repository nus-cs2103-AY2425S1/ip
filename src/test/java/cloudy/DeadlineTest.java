package cloudy;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testPrintTaskOnList_Marked() {
        Deadline deadline = new Deadline("go grocery shopping", LocalDate.parse("2024-10-10"), true);

        String result = deadline.printTaskOnList();

        assertEquals("[D][X] go grocery shopping (by: 10 October 2024)", result);
    }

    @Test
    public void testPrintTaskOnList_Unmarked() {
        Deadline deadline = new Deadline("go grocery shopping", LocalDate.parse("2024-10-10"), false);

        String result = deadline.printTaskOnList();

        assertEquals("[D][ ] go grocery shopping (by: 10 October 2024)", result);
    }

    @Test
    public void testToFileFormat_Marked() {
        Deadline deadline = new Deadline("go grocery shopping", LocalDate.parse("2024-10-10"), true);

        String result = deadline.toFileFormat();

        assertEquals("D | 1 | go grocery shopping | 2024-10-10", result);
    }

    @Test
    public void testToFileFormat_Unmarked() {
        Deadline deadline = new Deadline("go grocery shopping", LocalDate.parse("2024-10-10"), false);

        String result = deadline.toFileFormat();

        assertEquals("D | 0 | go grocery shopping | 2024-10-10", result);
    }
}
