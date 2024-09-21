package gallium.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

public class DeadlineTest {
    @Test
    public void deadlineTest() throws ParseException {
        Deadline test = new Deadline("deadline test /by 2000-09-09 1000");
        assertEquals("[D][ ] test (by: Sep 9 2000, 10:00 AM)", test.toString());
    }

    @Test
    public void setIsDoneTest() throws ParseException {
        Deadline test = new Deadline("deadline test /by 2000-09-09 1000");
        test.setIsDone(true);
        assertEquals("[D][X] test (by: Sep 9 2000, 10:00 AM)", test.toString());
    }

    @Test
    public void wrongDateInput() throws ParseException {
        try {
            Deadline test = new Deadline("deadline test /by adsakdh");
            assertEquals("Invalid date/time format! Please put in YYYY-MM-DD and 24 hours (HHMM) format!",
                    test.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void wrongDateFormat() throws ParseException {
        try {
            Deadline test = new Deadline("deadline test /by 2000-09- 0900");
            assertEquals("Invalid date/time format! Please put in YYYY-MM-DD and 24 hours (HHMM) format!",
                    test.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void wrongTimeFormat() throws ParseException {
        try {
            Deadline test = new Deadline("deadline test /by 2000-09-09 9");
            assertEquals("Invalid date/time format! Please put in YYYY-MM-DD and 24 hours (HHMM) format!",
                    test.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
