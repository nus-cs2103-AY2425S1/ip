package pikappi.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import pikappi.exception.PikappiException;

public class EventCommandTest {
    @Test
    public void execute_validTask_success() throws PikappiException {
        assertEquals("2021-08-25 1800",
                new EventCommand("deadline test task /from 2021-08-25 1800 /to 2021-09-25 1900").from);
        assertEquals("2021-09-25 1900",
                new EventCommand("deadline test task /from 2021-08-25 1800 /to 2021-09-25 1900").to);
    }

    @Test
    public void execute_invalidDateTimeFormat_success() throws PikappiException {
        assertEquals("later",
                new EventCommand("event test task /from later /to tmr").from);
        assertEquals("tmr",
                new EventCommand("event test task /from later /to tmr").to);
    }

    @Test
    public void execute_extraSpaces_success() throws PikappiException {
        assertEquals("  2024-03-02 1800  ",
                new EventCommand("event      test     /from   2024-03-02 1800   /to 2025-03-02 1900").from);
        assertEquals("2025-03-02 1900",
                new EventCommand("event      test     /from   2024-03-02 1800   /to 2025-03-02 1900").to);
    }

    @Test
    public void execute_noStartAndEnd_exceptionThrown() {
        try {
            assertEquals("none",
                    new EventCommand("event test task").from);
            fail();
        } catch (PikappiException e) {
            assertEquals("Pika..? What is the event timings?", e.getMessage());
        }
    }

    @Test
    public void execute_missingEnd_exceptionThrown() {
        try {
            assertEquals("none",
                    new EventCommand("event test task /from 2025-09-09").from);
            fail();
        } catch (PikappiException e) {
            assertEquals("Pika..? What is the event timings?", e.getMessage());
        }
    }

    @Test
    public void execute_missingStart_exceptionThrown() {
        try {
            assertEquals("none",
                    new EventCommand("event test task /to 2025-09-09").from);
            fail();
        } catch (PikappiException e) {
            assertEquals("Pika..? What is the event timings?", e.getMessage());
        }
    }

    @Test
    public void execute_tooManyKeywords_exceptionThrown() {
        try {
            assertEquals("none",
                    new EventCommand("event test /from 2024-03-02 1800 /to tmr /to later").from);
            fail();
        } catch (PikappiException e) {
            assertEquals("Pika..? Too many '/to' keywords..", e.getMessage());
        }
    }
}
