package oyster.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void empty() {
        TaskList taskList = new TaskList();

        assertEquals("", taskList.toString());

        assertEquals(true, taskList.isEmpty());
    }

    @Test
    public void add() {
        TaskList taskList = new TaskList();

        taskList.insert(new ToDoTask("Test"));
        taskList.insert(new DeadlineTask("Test", LocalDateTime.parse("2000-10-10T00:00:00")));
        taskList.insert(new EventTask("Test",
                LocalDateTime.parse("2000-10-10T00:00:00"),
                LocalDateTime.parse("2000-10-11T00:00:00")));

        taskList.mark(1);
        taskList.unmark(1);

        assertEquals("""
                1. [T][ ] Test
                2. [D][ ] Test (by: 10 OCT 2000)
                3. [E][ ] Test (from: 10 OCT 2000 to: 11 OCT 2000)
                """.trim(), taskList.toString().trim());

        assertEquals(false, taskList.isEmpty());
    }

    @Test
    public void remove() {
        TaskList taskList = new TaskList();

        taskList.insert(new ToDoTask("Test"));
        taskList.insert(new DeadlineTask("Test",
                LocalDateTime.parse("2000-10-10T00:00:00")));
        taskList.insert(new EventTask("Test",
                LocalDateTime.parse("2000-10-10T00:00:00"), LocalDateTime.parse("2000-10-11T00:00:00")));

        taskList.pop(0);
        taskList.pop(0);

        assertEquals("""
                1. [E][ ] Test (from: 10 OCT 2000 to: 11 OCT 2000)
                """.trim(), taskList.toString().trim());

        assertEquals(false, taskList.isEmpty());

        taskList.pop(0);

        assertEquals(true, taskList.isEmpty());
    }
}
