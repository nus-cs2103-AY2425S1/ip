package botty.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import botty.exceptions.BottyException;
import botty.exceptions.EmptyArgumentException;
import botty.exceptions.TaskListEmptyException;
import botty.exceptions.TaskNumberNotFoundException;
import botty.exceptions.TasksNotFoundException;

public class TaskManagerTest {
    @Test
    public void taskFinding_validInputs_success()
            throws EmptyArgumentException, TasksNotFoundException, TaskListEmptyException {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Todo(new TodoData("description one")));
        taskManager.addTask(new Todo(new TodoData("description two")));
        assertEquals("1. [T] [ ] description one\n"
                + "2. [T] [ ] description two", taskManager.findTasks("description"));
    }

    @Test
    public void taskFinding_emptyTaskList_taskListEmptyExceptionThrown() {
        TaskManager taskManager = new TaskManager();
        assertThrows(TaskListEmptyException.class, () -> taskManager.findTasks("keyword"));
    }

    @Test
    public void findTasks_noMatchingTasks_throwsTasksNotFoundException() throws EmptyArgumentException {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Todo(new TodoData("description one")));
        assertThrows(TasksNotFoundException.class, () -> taskManager.findTasks("notfound"));
    }

    @Test
    public void findTasks_caseInsensitiveMatching_success()
            throws TaskListEmptyException, TasksNotFoundException, EmptyArgumentException {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Todo(new TodoData("Description One")));
        assertEquals("1. [T] [ ] Description One", taskManager.findTasks("description"));
    }

    @Test
    public void findTasks_keywordAsSubstring_noMatch() throws EmptyArgumentException {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Todo(new TodoData("description part")));
        assertThrows(TasksNotFoundException.class, () -> taskManager.findTasks("partially"));
    }

    @Test
    public void updateTask_validInput_success() throws BottyException {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Todo(new TodoData("Initial task")));
        TodoData updatedData = new TodoData("Updated task");
        taskManager.updateTask(0, updatedData);
        assertEquals("1. [T] [ ] Updated task", taskManager.list());
    }

    @Test
    public void updateTask_invalidTaskIndex_throwsTaskNumberNotFoundException() throws EmptyArgumentException {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Todo(new TodoData("Task")));
        TodoData updatedData = new TodoData("Updated task");
        assertThrows(TaskNumberNotFoundException.class, () -> taskManager.updateTask(5, updatedData));
    }

    @Test
    public void updateTask_invalidDataType_throwsBottyException() throws EmptyArgumentException {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Todo(new TodoData("Task")));
        DeadlineData invalidData = new DeadlineData("New deadline", "2023-10-10");
        assertThrows(BottyException.class, () -> taskManager.updateTask(0, invalidData));
    }

    @Test
    public void updateTask_updateFirstAndLastTasks_success() throws BottyException {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Todo(new TodoData("First task")));
        taskManager.addTask(new Todo(new TodoData("Second task")));

        // Update the first task
        TodoData updatedFirst = new TodoData("Updated first task");
        taskManager.updateTask(0, updatedFirst);
        assertEquals("1. [T] [ ] Updated first task\n2. [T] [ ] Second task", taskManager.list());

        // Update the last task
        TodoData updatedLast = new TodoData("Updated last task");
        taskManager.updateTask(1, updatedLast);
        assertEquals("1. [T] [ ] Updated first task\n2. [T] [ ] Updated last task", taskManager.list());
    }
}
