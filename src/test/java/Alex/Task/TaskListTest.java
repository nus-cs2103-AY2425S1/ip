package Alex.Task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Alex.Exceptions.AlexException;

public class TaskListTest {

    @Test
    public void addTask_validTodoTask_taskAddedSuccessfully() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("Test todo");
        taskList.addTask(todo);

        assertEquals(1, taskList.size());
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    public void addTask_validDeadlineTask_taskAddedSuccessfully() throws AlexException {
        TaskList taskList = new TaskList();
        Deadline deadline = new Deadline("Submit report", "2024-09-05 15:55");
        taskList.addTask(deadline);

        assertEquals(1, taskList.size());
        assertEquals(deadline, taskList.getTask(0));
    }

    @Test
    public void addTask_validEventTask_taskAddedSuccessfully() {
        TaskList taskList = new TaskList();
        Event event = new Event("Project meeting", "2024-09-05 14:00", "2024-09-05 16:00");
        taskList.addTask(event);

        assertEquals(1, taskList.size());
        assertEquals(event, taskList.getTask(0));
    }

    @Test
    public void removeTask_validTask_taskRemovedSuccessfully() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("Test todo");
        taskList.addTask(todo);
        taskList.deleteTask(0);

        assertEquals(0, taskList.size());
    }
}
