package diomon;

import diomon.command.Command;
import diomon.command.MarkCommand;
import diomon.command.UnmarkCommand;
import org.junit.jupiter.api.Test;
import diomon.task.Task;
import diomon.task.TaskList;
import diomon.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandTest {

    @Test
    public void markTest1() {
        TaskList taskList = new TaskList(new Task[] {new Todo("weee")});
        Command c = new MarkCommand("1");
        c.execute(taskList, new Storage("data/data.txt"));
        assertEquals(new Todo(true, "weee"), taskList.get(0));
    }

    @Test
    public void markTest2() {
        TaskList taskList = new TaskList(new Task[] {new Todo(false, "weee")});
        Command c = new MarkCommand("s");
        c.execute(taskList, new Storage("data/data.txt"));
        assertEquals(new Todo(false, "weee"), taskList.get(0));
    }

    @Test
    public void unmarkTest1() {
        TaskList taskList = new TaskList(new Task[] {new Todo(true, "weee")});
        Command c = new UnmarkCommand("1");
        c.execute(taskList, new Storage("data/data.txt"));
        assertEquals(new Todo(false, "weee"), taskList.get(0));
    }

    @Test
    public void unmarkTest2() {
        TaskList taskList = new TaskList(new Task[] {new Todo(true, "weee")});
        Command c = new UnmarkCommand("s");
        c.execute(taskList, new Storage("data/data.txt"));
        assertEquals(new Todo(true, "weee"), taskList.get(0));
    }
}
