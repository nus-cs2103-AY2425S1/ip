package deez;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.util.Pair;

public class DeezTest {

    private Deez deez;

    @BeforeEach
    public void setup() {
        deez = new Deez(true);
    }

    // Test that the addTodo method correctly adds a todo task to the task list
    @Test
    public void testAddTodo() throws DeezException {
        Properties props = new Properties();
        props.setProperty("name", "Buy milk");
        props.setProperty("tags", "chore");
        deez.handleCommand(new Pair<>(Command.TODO, props));
        assertEquals(1, deez.taskList.size());
    }

    // Test that the addDeadline method correctly adds a deadline task to the task list
    @Test
    public void testAddDeadline() throws DeezException {
        Properties props = new Properties();
        props.setProperty("name", "Finish project");
        props.setProperty("by", "2025-01-01 1200");
        props.setProperty("tags", "school");
        deez.handleCommand(new Pair<>(Command.DEADLINE, props));
        assertEquals(1, deez.taskList.size());
    }

    // Test that the addEvent method correctly adds an event task to the task list
    @Test
    public void testAddEvent() throws DeezException {
        Properties props = new Properties();
        props.setProperty("name", "Meeting");
        props.setProperty("from", "2025-01-01 1000");
        props.setProperty("to", "2025-01-01 1200");
        props.setProperty("tags", "work");
        deez.handleCommand(new Pair<>(Command.EVENT, props));
        assertEquals(1, deez.taskList.size());
    }

    // Test that the handleMarkUnmarkDone method correctly marks or unmarks a task as done
    @Test
    public void testHandleMarkUnmarkDone() throws DeezException {
        Properties props = new Properties();
        props.setProperty("index", "1");
        props.setProperty("name", "example");
        props.setProperty("tags", "work");
        deez.handleCommand(new Pair<>(Command.TODO, props));
        deez.handleCommand(new Pair<>(Command.MARK, props));
        assertEquals(true, deez.taskList.get(0).isDone());
        deez.handleCommand(new Pair<>(Command.UNMARK, props));
        assertEquals(false, deez.taskList.get(0).isDone());
    }

    // Test that the deleteTask method correctly deletes a task from the task list
    @Test
    public void testDeleteTask() throws DeezException {
        Properties props = new Properties();
        props.setProperty("index", "1");
        props.setProperty("name", "example");
        props.setProperty("tags", "work");
        deez.handleCommand(new Pair<>(Command.TODO, props));
        deez.handleCommand(new Pair<>(Command.DELETE, props));
        assertEquals(0, deez.taskList.size());
    }

    // Test that it does not allow events with conflicting start and end dates
    @Test
    public void testInvalidEventDates() throws DeezException {
        Properties props = new Properties();
        props.setProperty("name", "Meeting");
        props.setProperty("from", "2025-01-01 1200");
        props.setProperty("to", "2025-01-01 1000");
        props.setProperty("tags", "work");
        assertThrows(DeezException.class, () -> deez.handleCommand(new Pair<>(Command.EVENT, props)));
    }
}
