package gallium.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

public class EventTest {
    @Test
    public void eventTest() throws ParseException {
        Event test = new Event("event test /from 2030-02-02 2000 /to 2030-02-02 2200");
        assertEquals("[E][ ] test (from: Feb 2 2030, 08:00 PM to: Feb 2 2030, 10:00 PM)", test.toString());
    }

    @Test
    public void setIsDoneTest() throws ParseException {
        Event test = new Event("event test /from 2030-02-02 2000 /to 2030-02-02 2200");
        test.setIsDone(true);
        assertEquals("[E][X] test (from: Feb 2 2030, 08:00 PM to: Feb 2 2030, 10:00 PM)", test.toString());
    }

    @Test
    public void wrongDateInput() throws ParseException {
        try {
            Event test = new Event("event test /from adsakdh /to sdfg");
            assertEquals("Invalid date/time format! Please put in YYYY-MM-DD and 24 hours (HHMM) format!",
                    test.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void wrongDateFormat() throws ParseException {
        try {
            Event test = new Event("event test /from 2000-09- 0900 /to 2000-09 0900");
            assertEquals("Invalid date/time format! Please put in YYYY-MM-DD and 24 hours (HHMM) format!",
                    test.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void wrongTimeFormat() throws ParseException {
        try {
            Event test = new Event("event test /from 2000-09-09 9 /to 2000-09-09 0");
            assertEquals("Invalid date/time format! Please put in YYYY-MM-DD and 24 hours (HHMM) format!",
                    test.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
