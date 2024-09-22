package panorama; // same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import panorama.task.Task;
import panorama.task.Todo;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setup() {
        // Initialize an empty TaskList before each
        taskList = new TaskList(true);
    }

    @Test
    public void taskList_initializeEmpty_success() {
        List<Task> tasks = taskList.getTasks();
        assertEquals(0, tasks.size());
    }

    @Test
    public void taskList_initializeWithList_success() {
        List<Task> presetTasks = new ArrayList<>();
        presetTasks.add(new Todo("task 1"));
        presetTasks.add(new Todo("task 2"));

        // re-initialize
        taskList = new TaskList(presetTasks, true);

        List<Task> tasks = taskList.getTasks();
        assertEquals(presetTasks.size(), tasks.size());
    }

    @Test
    public void add_success() {
        Todo t = new Todo("task 1");
        taskList.add(t);

        Task task = taskList.get(0);
        assertEquals(t, task);
    }

    @Test
    public void mark_success() {
        Todo t = new Todo("task 1");
        taskList.add(t);

        assertEquals(false, taskList.get(0).getIsDone());

        // Mark the 1st item
        taskList.mark(0);

        assertEquals(true, taskList.get(0).getIsDone());
    }

    @Test
    public void unmark_success() {
        Todo t = new Todo("task 1");
        t.setDone(true);

        taskList.add(t);

        assertEquals(true, taskList.get(0).getIsDone());

        // Unmark the 1st item
        taskList.unmark(0);

        assertEquals(false, taskList.get(0).getIsDone());
    }

    @Test
    public void delete_success() {
        Todo t = new Todo("task 1");
        taskList.add(t);

        assertEquals(1, taskList.getTasks().size());

        Task deletedTask = taskList.delete(0);

        assertEquals(0, taskList.getTasks().size());
        assertEquals(t, deletedTask);
    }

    @Test
    public void find_allMatch_success() {
        taskList.add(new Todo("task 1"));

        TaskList result = taskList.find("task");

        assertEquals(taskList.getTasks(), result.getTasks());
    }

    @Test
    public void find_noneMatch_success() {
        taskList.add(new Todo("task 1"));

        TaskList result = taskList.find("event");

        assertEquals(0, result.getTasks().size());
    }

    @Test
    public void find_someMatch_success() {
        taskList.add(new Todo("task 1"));
        taskList.add(new Todo("another name"));

        TaskList result = taskList.find("task");

        assertEquals(1, result.getTasks().size());
    }
}
