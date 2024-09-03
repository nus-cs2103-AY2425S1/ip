package casper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import exception.CasperBotOutOfBoundsException;
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
