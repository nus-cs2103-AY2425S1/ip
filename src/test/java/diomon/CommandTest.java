package diomon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandTest {
    @Test
    public void runCommand1(){
        RuntimeException e = assertThrows(RuntimeException.class, () -> new Commands().runCommand(Commands.Types.LIST, "", new TaskList()));
        assertEquals("Unknown argument/ Function not implemented yet", e.getMessage());
    }

    @Test
    public void runCommand2(){
        RuntimeException e = assertThrows(RuntimeException.class, () -> new Commands().runCommand(Commands.Types.TODO, null, new TaskList()));
        assertEquals("Missing argument/ Function not implemented", e.getMessage());
    }

    @Test
    public void markTest1() {
        TaskList taskList = new TaskList(new Task[] {new Todo("weee")});
        Commands c = new Commands();
        c.runMark(taskList, "1");
        assertEquals(new Todo(true, "weee"), taskList.get(0));
    }

    @Test
    public void markTest2() {
        TaskList taskList = new TaskList(new Task[] {new Todo(false, "weee")});
        Commands c = new Commands();
        c.runMark(taskList, "s");
        assertEquals(new Todo(false, "weee"), taskList.get(0));
    }

    @Test
    public void unmarkTest1() {
        TaskList taskList = new TaskList(new Task[] {new Todo(true, "weee")});
        Commands c = new Commands();
        c.runUnmark(taskList, "1");
        assertEquals(new Todo(false, "weee"), taskList.get(0));
    }

    @Test
    public void unmarkTest2() {
        TaskList taskList = new TaskList(new Task[] {new Todo(true, "weee")});
        Commands c = new Commands();
        c.runUnmark(taskList, "s");
        assertEquals(new Todo(true, "weee"), taskList.get(0));
    }
}
