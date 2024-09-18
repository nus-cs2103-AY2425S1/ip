package henry.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import henry.HenryException;
import henry.task.Task;
import henry.task.Todo;
import henry.util.TaskList;
import henry.util.Ui;

public class ChangeStatusCommandTest {

    private Ui ui;
    private TaskList taskList;
    private Task task;


    public ChangeStatusCommandTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        task = new Todo("read book");
        tasks.add(task);
        taskList = new TaskList(tasks);
    }

    @Test
    public void execute_markTask_success() throws HenryException {
        Command c = new ChangeStatusCommand("mark 1".split(" "));
        c.execute(taskList, ui);
        assertEquals(true, taskList.getTasks().get(0).isDone());
    }

    @Test
    public void execute_unmarkTask_success() throws HenryException {
        taskList.getTasks().get(0).mark();
        Command c = new ChangeStatusCommand("unmark 1".split(" "));
        c.execute(taskList, ui);
        assertEquals(false, taskList.getTasks().get(0).isDone());
    }

    @Test
    public void execute_indexZero_exceptionThrown() {
        try {
            new ChangeStatusCommand("mark 0".split(" ")).execute(taskList, ui);
        } catch (HenryException e) {
            assertEquals("Number must be greater than zero!", e.getMessage());
        }
    }

    @Test
    public void execute_invalidIndex_exceptionThrown() {
        try {
            new ChangeStatusCommand("mark 2".split(" ")).execute(taskList, ui);
        } catch (HenryException e) {
            assertEquals("There is only 1 task!", e.getMessage());
        }
    }

    @Test
    public void execute_notNumber_exceptionThrown() {
        try {
            new ChangeStatusCommand("mark abc".split(" ")).execute(taskList, ui);
        } catch (HenryException e) {
            assertEquals("This is not a number!!", e.getMessage());
        }
    }

    @Test
    public void execute_alreadyMarked_exceptionThrown() {
        taskList.getTasks().get(0).mark();
        try {
            new ChangeStatusCommand("mark 1".split(" ")).execute(taskList, ui);
        } catch (HenryException e) {
            assertEquals("The task is already marked!", e.getMessage());
        }
    }

    @Test
    public void execute_alreadyUnmarked_exceptionThrown() {
        try {
            new ChangeStatusCommand("unmark 1".split(" ")).execute(taskList, ui);
        } catch (HenryException e) {
            assertEquals("The task is already unmarked!", e.getMessage());
        }
    }

}
