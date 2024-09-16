package luna;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import luna.task.Deadline;
import luna.task.Event;
import luna.task.Todo;

public class TaskListTest {
    @Test
    public void deleteTask_negativeTaskNumber_exceptionThrown() {
        try {
            Storage storage = new Storage();
            assertEquals(new Todo("return book"), new TaskList().deleteTask(-1, storage));
            fail();
        } catch (LunaException e) {
            assertEquals("Invalid task number. Type \"list\" to view tasks.", e.getMessage());
        }
    }

    @Test
    public void deleteTask_exceedTotalTask_exceptionThrown() {
        try {
            Storage storage = new Storage();
            assertEquals(new Todo("return book"), new TaskList().deleteTask(1, storage));
            fail();
        } catch (LunaException e) {
            assertEquals("Invalid task number. Type \"list\" to view tasks.", e.getMessage());
        }
    }

    @Test
    public void deleteTask_validTaskNumber_success() {
        try {
            Storage storage = new Storage();
            TaskList taskList = new TaskList();

            Todo todo = new Todo("return book");
            taskList.addTask(todo, storage);

            assertEquals(1, taskList.getTasks().size());
            assertEquals(todo, taskList.deleteTask(0, storage));
            assertEquals(0, taskList.getTasks().size());
        } catch (LunaException e) {
            fail();
        }
    }

    @Test
    public void addTask_todoTask_success() {
        Storage storage = new Storage();
        TaskList taskList = new TaskList();

        Todo todo = new Todo("Return book");
        String taskString = "Got it. I've added this task:\n"
                + "  " + todo + "\n"
                + "Now you have 1 tasks in the list.";

        assertEquals(taskString, taskList.addTask(todo, storage));
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    public void addTask_deadlineTask_success() {
        Storage storage = new Storage();
        TaskList taskList = new TaskList();

        Deadline deadline = new Deadline("Buy groceries", LocalDateTime.now());
        String taskString = "Got it. I've added this task:\n"
                + "  " + deadline + "\n"
                + "Now you have 1 tasks in the list.";

        assertEquals(taskString, taskList.addTask(deadline, storage));
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    public void addTask_eventTask_success() {
        Storage storage = new Storage();
        TaskList taskList = new TaskList();

        Event event = new Event("Project Meeting", LocalDateTime.now(), LocalDateTime.now());
        String taskString = "Got it. I've added this task:\n"
                + "  " + event + "\n"
                + "Now you have 1 tasks in the list.";

        assertEquals(taskString, taskList.addTask(event, storage));
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    public void addTask_multipleTask_success() {
        Storage storage = new Storage();
        TaskList taskList = new TaskList();

        Todo todo = new Todo("Return book");
        Deadline deadline = new Deadline("Buy groceries", LocalDateTime.now());
        Event event = new Event("Project Meeting", LocalDateTime.now(), LocalDateTime.now());

        taskList.addTask(todo, storage);
        taskList.addTask(deadline, storage);
        taskList.addTask(event, storage);

        assertEquals(3, taskList.getTasks().size());
        assertEquals(todo, taskList.getTasks().get(0));
        assertEquals(deadline, taskList.getTasks().get(1));
        assertEquals(event, taskList.getTasks().get(2));
    }
}

