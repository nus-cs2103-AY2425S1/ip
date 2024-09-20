package tasks;

import exceptions.InvalidTaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListTest {

    @Test
    public void getTask_emptyList_throwsInvalidTaskException() {
        List list = new List();
        InvalidTaskException exception = assertThrows(InvalidTaskException.class, () -> list.getTask(0));
        String errorMessage = exception.getMessage();
        assertEquals("OOPS!!! Task 1 does not exist.\n", errorMessage);
    }

    @Test
    public void getTask_taskExists_correctTask() throws InvalidTaskException {
        List list = new List();
        Task taskA = new ToDo("A");
        Task taskB = new ToDo("B");
        list.addTaskToList(taskA);
        list.addTaskToList(taskB);
        Task task = list.getTask(1);
        assertEquals(taskB, task);
    }

    @Test
    public void getTask_wrongIndex_throwsInvalidTaskException() {
        List list = new List();
        Task taskA = new ToDo("A");
        list.addTaskToList(taskA);
        InvalidTaskException exception = assertThrows(InvalidTaskException.class, () -> list.getTask(1));
        String errorMessage = exception.getMessage();
        assertEquals("OOPS!!! Task 2 does not exist.\n", errorMessage);
    }
}
