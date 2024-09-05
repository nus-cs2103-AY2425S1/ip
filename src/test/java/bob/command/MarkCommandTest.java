package bob.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bob.exceptions.InvalidTaskNumberException;
import bob.tasks.TaskList;
import bob.tasks.ToDo;

public class MarkCommandTest {

    @Test
    public void isRunningTest() {
        MarkCommand markCommand = new MarkCommand(1);
        assertTrue(markCommand.isRunning());
    }

    @Test
    public void testExecute() throws InvalidTaskNumberException {
        TaskList myTasks = new TaskList();
        ToDo toDo = new ToDo("Hello");
        myTasks.addTask(toDo);
        MarkCommand markCommand = new MarkCommand(0);
        markCommand.execute(myTasks);
        assertEquals("[T][X] Hello", toDo.toString());
    }
}
