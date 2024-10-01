package sigma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import sigma.task.Task;
public class TaskListTest {
    private TaskList taskList;
    private Parser parser;

    @BeforeEach
    public void setup() {
        taskList = new TaskList();
        parser = new Parser();
    }

    // sanity check - checks that parser class is working
    @Test
    public void testGreet() {
        String expected = "hello, I'm Sigma, your personal chatbot";
        String actual = parser.greet();
        assertEquals(expected, actual);
    }

    @Test
    public void testAddToList() {
        Task task = new Task("Test Task", false);
        TaskList.addToList(task);
        assertEquals(1, TaskList.getSize());
        assertEquals(task, TaskList.get(0));
    }

    @Test
    public void testHandleDelete() {
        Task task1 = new Task("Task 1", false);
        Task task2 = new Task("Task 2", false);
        TaskList.addToList(task1);
        TaskList.addToList(task2);

        parser.handleDelete("delete 1");
        assertEquals(1, TaskList.getSize());
        assertEquals(task2, TaskList.get(0));
    }

    @Test
    public void testGet() {
        Task task = new Task("Test Task", false);
        TaskList.addToList(task);
        assertEquals(task, TaskList.get(0));
    }

    @Test
    public void testGetSize() {
        assertEquals(0, TaskList.getSize());
        Task task = new Task("Test Task", false);
        TaskList.addToList(task);
        assertEquals(1, TaskList.getSize());
    }

    @Test
    public void testToPrettyList() {
        Task task1 = new Task("Task 1", false);
        Task task2 = new Task("Task 2", false);
        TaskList.addToList(task1);
        TaskList.addToList(task2);

        String expected = "\n1. [ ] Task 1\n2. [ ] Task 2";
        assertEquals(expected, TaskList.toPrettyList());
    }

}
