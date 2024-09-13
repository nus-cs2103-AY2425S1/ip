package diomon;

import diomon.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandTest {
    @Test
    public void runCommand1(){
        RuntimeException e = assertThrows(RuntimeException.class, () -> new Command().runCommand(Command.Types.LIST, "", new TaskList()));
        assertEquals("Unknown argument/ Function not implemented yet", e.getMessage());
    }

    @Test
    public void runCommand2(){
        RuntimeException e = assertThrows(RuntimeException.class, () -> new Command().runCommand(Command.Types.TODO, null, new TaskList()));
        assertEquals("Missing argument/ Function not implemented", e.getMessage());
    }

    @Test
    public void markTest1() {
        TaskList taskList = new TaskList(new Task[] {new Todo("weee")});
        Command c = new Command();
        c.runMark(taskList, "1");
        assertEquals(new Todo(true, "weee"), taskList.get(0));
    }

    @Test
    public void markTest2() {
        TaskList taskList = new TaskList(new Task[] {new Todo(false, "weee")});
        Command c = new Command();
        c.runMark(taskList, "s");
        assertEquals(new Todo(false, "weee"), taskList.get(0));
    }

    @Test
    public void unmarkTest1() {
        TaskList taskList = new TaskList(new Task[] {new Todo(true, "weee")});
        Command c = new Command();
        c.runUnmark(taskList, "1");
        assertEquals(new Todo(false, "weee"), taskList.get(0));
    }

    @Test
    public void unmarkTest2() {
        TaskList taskList = new TaskList(new Task[] {new Todo(true, "weee")});
        Command c = new Command();
        c.runUnmark(taskList, "s");
        assertEquals(new Todo(true, "weee"), taskList.get(0));
    }
}
