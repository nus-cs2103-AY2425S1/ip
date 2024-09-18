package stobberi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stobberi.stobberiexception.StobberiException;

public class TaskTest {

    private Task todoTask;
    private Task deadlineTask;
    private Task eventTask;

    @BeforeEach
    public void setUp() throws StobberiException {
        // Initialize instances of Todo, Deadline, and Event tasks
        todoTask = new Todo("Read book", "0");
        deadlineTask = new Deadline("Submit report", "19-09-2024 0900hrs", "0");
        eventTask = new Event("Conference", "21-09-2024 0900hrs", "21-09-2024 1700hrs", "0");
    }

    // ================== Tests for Todo ==================

    @Test
    public void testTodoInitialStatus() {
        assertFalse(todoTask.isDone(), "Todo should not be done initially.");
        assertEquals(" ", todoTask.getStatusIcon(), "Status icon should be an empty space initially.");
    }

    @Test
    public void testTodoSetDone() {
        todoTask.setDone();
        assertTrue(todoTask.isDone(), "Todo should be marked as done.");
        assertEquals("X", todoTask.getStatusIcon(), "Status icon should be 'X' when Todo is done.");
    }

    @Test
    public void testTodoSetNotDone() {
        todoTask.setDone();
        todoTask.setNotDone();
        assertFalse(todoTask.isDone(), "Todo should be marked as not done.");
        assertEquals(" ", todoTask.getStatusIcon(), "Status icon should be an empty space when Todo is not done.");
    }

    @Test
    public void testTodoToString() {
        assertEquals("[T] [ ] Read book", todoTask.toString(), "toString should return '[ ] Read book' for Todo.");
        todoTask.setDone();
        assertEquals("[T] [X] Read book", todoTask.toString(), "toString should return '[X] Read book' when Todo is done.");
    }

    // ================== Tests for Deadline ==================

    @Test
    public void testDeadlineInitialStatus() {
        assertFalse(deadlineTask.isDone(), "Deadline should not be done initially.");
        assertEquals(" ", deadlineTask.getStatusIcon(), "Status icon should be an empty space initially.");
    }

    @Test
    public void testDeadlineSetDone() {
        deadlineTask.setDone();
        assertTrue(deadlineTask.isDone(), "Deadline should be marked as done.");
        assertEquals("X", deadlineTask.getStatusIcon(), "Status icon should be 'X' when Deadline is done.");
    }

    @Test
    public void testDeadlineGetDescription() {
        assertEquals("Submit report", deadlineTask.getDescription(), "Description should be 'Submit report'.");
    }

    @Test
    public void testDeadlineToString() {
        assertEquals("[D] [ ] Submit report (by: 19 September 2024 9am)", deadlineTask.toString(),
                "Deadline toString isn't correct when task not done");
        deadlineTask.setDone();
        assertEquals("[D] [X] Submit report (by: 19 September 2024 9am)", deadlineTask.toString(),
                "Deadline toString isn't correct when task is done");
    }

    // ================== Tests for Event ==================

    @Test
    public void testEventInitialStatus() {
        assertFalse(eventTask.isDone(), "Event should not be done initially.");
        assertEquals(" ", eventTask.getStatusIcon(), "Status icon should be an empty space initially.");
    }

    @Test
    public void testEventSetDone() {
        eventTask.setDone();
        assertTrue(eventTask.isDone(), "Event should be marked as done.");
        assertEquals("X", eventTask.getStatusIcon(), "Status icon should be 'X' when Event is done.");
    }

    @Test
    public void testEventGetDescription() {
        assertEquals("Conference", eventTask.getDescription(), "Description should be 'Conference'.");
    }

    @Test
    public void testEventToString() {
        assertEquals("[E] [ ] Conference (from: 21 September 2024 9am to: 21 September 2024 5pm)", eventTask.toString(),
                "Event toString() isn't correct");
        eventTask.setDone();
        assertEquals("[E] [X] Conference (from: 21 September 2024 9am to: 21 September 2024 5pm)", eventTask.toString(),
                "Event toString() isn't correct");
    }
}
