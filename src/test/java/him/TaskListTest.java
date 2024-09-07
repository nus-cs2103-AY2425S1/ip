package him;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exceptions.AlreadyCompletedException;
import exceptions.TaskDoesNotExistException;
import task.TaskList;
import task.ToDo;


public class TaskListTest {

    @Test
    public void complete_validIndex_success() throws AlreadyCompletedException, TaskDoesNotExistException {
        TaskList list = new TaskList();
        ToDo todo = new ToDo("todo");
        list.add(todo);
        list.complete(0);
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void complete_invalidIndex_throwsException() {
        TaskList list = new TaskList();
        try {
            list.complete(0);
            fail();
        } catch (Exception e) {
            assertEquals(TaskDoesNotExistException.class, e.getClass());
        }
    }

    @Test
    public void complete_completedTask_throwsException() {
        try {
            TaskList list = new TaskList();
            ToDo todo = new ToDo("todo");
            list.add(todo);
            list.complete(0);
            list.complete(0);
            fail();
        } catch (Exception e) {
            assertEquals(AlreadyCompletedException.class, e.getClass());
        }
    }
}
