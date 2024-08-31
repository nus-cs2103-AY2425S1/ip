package bob.command;

import bob.exceptions.InvalidTaskNumberException;
import bob.tasks.TaskList;
import bob.tasks.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmarkCommandTest {

    @Test
    public void IsRunningTest() {
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
