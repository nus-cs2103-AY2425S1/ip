package parser;

import exception.TakoException;
import org.junit.jupiter.api.Test;
import task.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseEvent_emptyTask_exceptionThrown() {
        assertEquals("Wrong format! Event command should have the form"
                     + " 'event x /from YYYY-MM-DD /to YYYY-MM-DD', where x is not empty",
                     Parser.parseEvent("event"));
    }

    @Test
    public void parseEvent_noFrom_exceptionThrown() {
        assertEquals("Wrong format! Event command should have the form"
                        + " 'event x /from YYYY-MM-DD /to YYYY-MM-DD', where x is not empty",
                Parser.parseEvent("event test"));
    }

    @Test
    public void parseEvent_noTo_exceptionThrown() {
        assertEquals("Wrong format! Event command should have the form"
                        + " 'event x /from YYYY-MM-DD /to YYYY-MM-DD', where x is not empty",
                Parser.parseEvent("event test /from 2022-10-10"));
    }

    @Test
    public void parseEvent_emptyTaskWithFromTo_exceptionThrown() {
        assertEquals("Wrong format! Event command should have the form"
                        + " 'event x /from YYYY-MM-DD /to YYYY-MM-DD', where x is not empty",
                Parser.parseEvent("event  /from 2022-10-10 /to 2022-10-10"));
    }

    @Test
    public void parseEvent_invalidFromDate_exceptionThrown() {
        assertEquals("Wrong format! Event command should have the form"
                        + " 'event x /from YYYY-MM-DD /to YYYY-MM-DD', where x is not empty",
                Parser.parseEvent("event test /from 2022-40-40 /to 2022-10-10"));
    }

    @Test
    public void parseEvent_invalidToDate_exceptionThrown() {
        assertEquals("Wrong format! Event command should have the form"
                        + " 'event x /from YYYY-MM-DD /to YYYY-MM-DD', where x is not empty",
                Parser.parseEvent("event test /from 2022-10-10 /to 2022-02-31"));
    }

    @Test
    public void parseEvent_emptyFrom_exceptionThrown() {
        assertEquals("Wrong format! Event command should have the form"
                        + " 'event x /from YYYY-MM-DD /to YYYY-MM-DD', where x is not empty",
                Parser.parseEvent("event test /from /to 2022-10-10"));
    }

    @Test
    public void parseEvent_emptyTo_exceptionThrown() {
        assertEquals("Wrong format! Event command should have the form"
                        + " 'event x /from YYYY-MM-DD /to YYYY-MM-DD', where x is not empty",
                Parser.parseEvent("event test /from 2022-10-10 /to "));
    }

    @Test
    public void parseEvent_invalidInputFrom_exceptionThrown() {
        assertEquals("Wrong format! Event command should have the form"
                        + " 'event x /from YYYY-MM-DD /to YYYY-MM-DD', where x is not empty",
                Parser.parseEvent("event test /from apple /to 2022-10-10"));
    }

    @Test
    public void parseEvent_invalidInputTo_exceptionThrown() {
        assertEquals("Wrong format! Event command should have the form"
                        + " 'event x /from YYYY-MM-DD /to YYYY-MM-DD', where x is not empty",
                Parser.parseEvent("event test /from 2022-10-10 /to banana"));
    }

    @Test
    public void parseEvent_success1() {
        assertEquals("Task Received! I've added this task:\n" +
                        "[E][ ][L] test (from: OCTOBER 10 2022 to: OCTOBER 10 2022)" + "\n" +
                        "Now, you have 1 tasks in your list!",
                Parser.parseEvent("event test /from 2022-10-10 /to 2022-10-10"));
    }

    @Test
    public void parseEvent_success2() {
        assertEquals("Task Received! I've added this task:\n" +
                        "[E][ ][L] eat supper (from: APRIL 10 2021 to: SEPTEMBER 10 2022)" + "\n" +
                        "Now, you have 2 tasks in your list!",
                Parser.parseEvent("event eat supper /from 2021-04-10 /to 2022-09-10"));
    }
}
