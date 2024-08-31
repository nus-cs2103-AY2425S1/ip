package botty.tasks;

import org.junit.jupiter.api.Test;

import botty.exceptions.EmptyArgumentException;
import botty.exceptions.TaskListEmptyException;
import botty.exceptions.TasksNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {
    @Test
    public void taskFinding_validInputs_success() throws EmptyArgumentException, TasksNotFoundException, TaskListEmptyException {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Todo("description one"));
        taskManager.addTask(new Todo("description two"));
        assertEquals("1. [T] [ ] description one\n" +
                "2. [T] [ ] description two", taskManager.findTasks("description"));
    }
}
