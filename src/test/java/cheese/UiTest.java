package cheese;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cheese.exception.CheeseException;
import cheese.task.Task;

public class UiTest {
    private final Ui ui = new Ui();

    @Test
    public void testSayString() {
        String result = ui.say("Hello World!");
        assertEquals("Hello World!", result);
    }

    @Test
    public void testSayTaskList() throws CheeseException {
        TaskList tasks = new TaskList();
        tasks.add(new Task("Task 1"));
        tasks.add(new Task("Task 2"));
        String result = ui.say(tasks);
        String expected = "Got your cheese:\n1. [ ] Task 1\n2. [ ] Task 2";
        assertEquals(expected, result);
    }

    @Test
    public void testSayTaskWithDelete() throws CheeseException {
        Task task = new Task("Task to delete");
        TaskList tasks = new TaskList();
        tasks.add(task);
        String result = ui.say(task, tasks, true);
        String expected = """
            Removed cheese :)
            [ ] Task to delete
            1 cheese in the shelf""";
        assertEquals(expected, result);
    }

    @Test
    public void testSayTaskWithAdd() throws CheeseException {
        Task task = new Task("Task to add");
        TaskList tasks = new TaskList();
        tasks.add(task);
        String result = ui.say(task, tasks);
        String expected = """
            Added new cheese ;)
            [ ] Task to add
            1 cheese in the shelf""";
        assertEquals(expected, result);
    }

    @Test
    public void testSayUpdatedTask() throws CheeseException {
        Task task = new Task("Updated Task");
        TaskList tasks = new TaskList();
        tasks.add(task);
        String result = ui.say(task, tasks, false);
        String expected = """
            Updated cheese :)
            [ ] Updated Task
            1 cheese in the shelf""";
        assertEquals(expected, result);
    }

    @Test
    public void testSayError() {
        CheeseException exception = new CheeseException("Sample error message");
        String result = ui.say(exception);
        String expected = "Command not gouda.... \nSample error message";
        assertEquals(expected, result);
    }

    @Test
    public void testGreet() {
        String result = ui.greet();
        String expected = "Hello! I'm Wheely Big Cheese\nWhat can I do for you?";
        assertEquals(expected, result);
    }

    @Test
    public void testBye() {
        String result = ui.bye();
        String expected = "Schwooo Weeeeee!!! Shutting down..... Window closing in 3s :)";
        assertEquals(expected, result);
    }
}
