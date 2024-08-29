package casper;

import exception.CasperBotOutOfBoundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void testIsEmpty() {
        TaskList taskList = new TaskList();
        assertTrue(taskList.isEmpty());
        taskList.addTask(new ToDo("test", true));
        assertFalse(taskList.isEmpty());
    }
    @Test
    public void testGetTask() throws CasperBotOutOfBoundsException {
        assertThrows(CasperBotOutOfBoundsException.class, () -> {
            new TaskList().getTask(0);
        });
        TaskList taskList = new TaskList();
        ToDo toDo = new ToDo("test", true);
        taskList.addTask(toDo);
        assertEquals(toDo, taskList.getTask(0));
    }
}