package fence.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parseInput_expectedInput_success() {
        assertEquals("bye", new Parser().parseInput("bye"));

        assertEquals("list", new Parser().parseInput("list"));

        assertEquals("task", new Parser().parseInput("todo some task"));

        assertEquals("task", new Parser().parseInput("deadline some task /by 2024-09-01"));

        assertEquals("task", new Parser().parseInput("event some task /from Monday 2pm /to 4pm"));

        assertEquals("mark", new Parser().parseInput("mark 1"));

        assertEquals("unmark", new Parser().parseInput("unmark 1"));

        assertEquals("delete", new Parser().parseInput("delete 1"));

        assertEquals("unknown command", new Parser().parseInput("unknown"));
    }

    @Test
    public void parseInput_additionalInput_success() {
        assertEquals("mark", new Parser().parseInput("mark 5 aaa"));

        assertEquals("unmark", new Parser().parseInput("unmark 5 aaa"));

        assertEquals("delete", new Parser().parseInput("delete 5 aaa"));
    }

    @Test
    public void parseInput_invalidNumberFormat_exceptionThrown() {
        try {
            assertEquals("", new Parser().parseInput("mark one"));
            fail();
        } catch (NumberFormatException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals("", new Parser().parseInput("unmark one"));
            fail();
        } catch (NumberFormatException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals("", new Parser().parseInput("delete one"));
            fail();
        } catch (NumberFormatException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseInput_todoMissingInput_exceptionThrown() {
        try {
            assertEquals("", new Parser().parseInput("todo"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseInput_deadlineMissingInput_exceptionThrown() {
        try {
            assertEquals("", new Parser().parseInput("deadline"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals("", new Parser().parseInput("deadline some task"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals("", new Parser().parseInput("deadline some task /by"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals("", new Parser().parseInput("deadline /by"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseInput_eventMissingInput_exceptionThrown() {
        try {
            assertEquals("", new Parser().parseInput("event"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals("", new Parser().parseInput("event some task"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals("", new Parser().parseInput("event some task /from"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals("", new Parser().parseInput("event some task /from Mon 2pm"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals("", new Parser().parseInput("event some task /from Mon 2pm /to"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals("", new Parser().parseInput("event some task /to"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals("", new Parser().parseInput("event some task /to 4pm"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals("", new Parser().parseInput("event /from"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals("", new Parser().parseInput("event /to"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals("", new Parser().parseInput("event some task /to 4pm /from Mon 2pm"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseInput_missingNumber_exceptionThrown() {
        try {
            assertEquals("", new Parser().parseInput("mark"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals("", new Parser().parseInput("unmark"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals("", new Parser().parseInput("delete"));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseInput_invalidDateFormat_exceptionThrown() {
        try {
            assertEquals("", new Parser().parseInput("deadline some task /by Monday"));
            fail();
        } catch (DateTimeParseException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseInput_emptyString_exceptionThrown() {
        try {
            assertEquals("", new Parser().parseInput(""));
            fail();
        } catch (NoSuchElementException e) {
            // Passes the test as this is the expected exception thrown
        } catch (Exception e) {
            fail();
        }
    }
}
