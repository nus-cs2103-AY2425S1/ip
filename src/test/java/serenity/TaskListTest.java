package serenity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import serenity.task.Deadline;
import serenity.task.Task;
import serenity.task.TaskList;
import serenity.task.Todo;

public class TaskListTest {

    @Test
    public void createTask_invalidTodo_exceptionThrown() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        try {
            taskList.createTask("todo ");
        } catch (SerenityException e) {
            assertEquals("Error: The description of a todo cannot be empty.",
                    e.getMessage());
        }
    }
    @Test
    public void createTask_validTodo_success() throws SerenityException {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        Task t = taskList.createTask("todo read book");
        assertEquals(new Todo("read book").toString(), t.toString());
    }

    @Test
    public void addTask_validDeadline_success() throws SerenityException {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        Deadline t = new Deadline("return book", "30/08/2024");
        String expected = "Got it. I've added this task:\n"
                + "[D][ ] return book (by: 30 Aug 2024)\n"
                + "Now you have 1 task in the list.";
        String actual = taskList.addTask(t);
        assertEquals(expected, actual);
    }

}
