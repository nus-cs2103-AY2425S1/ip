package bob.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bob.exceptions.InvalidTaskNumberException;
import bob.tasks.TaskList;
import bob.tasks.ToDo;

public class UnmarkCommandTest {

    @Test
    public void isRunningTest() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(1);
        assertTrue(unmarkCommand.isRunning());
    }

    @Test
    public void testExecute() throws InvalidTaskNumberException {
        TaskList myTasks = new TaskList();
        ToDo toDo = new ToDo("Hello");
        myTasks.addTask(toDo);
        toDo.mark();
        UnmarkCommand unmarkCommand = new UnmarkCommand(0);
        unmarkCommand.execute(myTasks);
        assertEquals("[T][ ] Hello", toDo.toString());
    }
}
