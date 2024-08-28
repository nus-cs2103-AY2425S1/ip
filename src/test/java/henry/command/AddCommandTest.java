package henry.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import henry.HenryException;
import henry.task.Task;
import henry.util.TaskList;
import henry.util.Ui;

public class AddCommandTest {

    private Ui ui;
    private TaskList taskList;

    public AddCommandTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        taskList = new TaskList(tasks);
    }

    @Test
    public void execute_addTask_success() throws HenryException {
        new AddCommand("todo borrow book").execute(taskList, ui);
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    public void execute_invalidTodo_exceptionThrown() {
        try {
            new AddCommand("todo").execute(taskList, ui);
        } catch (HenryException e) {
            assertEquals("The todo description is wrong!! "
                    + "Ensure that you have included the activity. "
                    + "Example: todo read book", e.getMessage());
        }
    }

    @Test
    public void execute_invalidDeadline_exceptionThrown() {
        try {
            new AddCommand("deadline").execute(taskList, ui);
        } catch (HenryException e) {
            assertEquals("The deadline description is wrong!! "
                    + "Ensure that you have included the activity, "
                    + "followed by the deadline. "
                    + "Example: deadline return book /by 2019-12-01 1900", e.getMessage());
        }
    }

    @Test
    public void execute_invalidEvent_exceptionThrown() {
        try {
            new AddCommand("event").execute(taskList, ui);
        } catch (HenryException e) {
            assertEquals("The event description is wrong!! "
                    + "Ensure that you have included the activity, "
                    + "followed by the start time and end time. "
                    + "Example: event project meeting /from Mon 2pm /to 4pm", e.getMessage());
        }
    }

    @Test
    public void execute_invalidTask_exceptionThrown() {
        try {
            new AddCommand("blah").execute(taskList, ui);
        } catch (HenryException e) {
            assertEquals("This is not a task!! "
                    + "To write a task, start with "
                    + "\"" + "todo" + "\","
                    + " \"" + "deadline" + "\" or"
                    + " \"" + "event" + "\"", e.getMessage());
        }
    }

    @Test
    public void execute_invalidTime_exceptionThrown() {
        try {
            new AddCommand("deadline return book /by 12-12-2019 1900").execute(taskList, ui);
        } catch (HenryException e) {
            assertEquals("Please write the date and time in"
                    + " the following format: YYYY-MM-DD HHmm", e.getMessage());
        }
    }
}
