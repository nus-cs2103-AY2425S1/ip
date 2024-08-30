package bob.command;

import bob.tasks.TaskList;
import bob.tasks.ToDos;
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
    public void testExecute() {
        TaskList myTasks = new TaskList();
        ToDos toDos = new ToDos("Hello");
        myTasks.addTask(toDos);
        toDos.mark();
        UnmarkCommand unmarkCommand = new UnmarkCommand(0);
        unmarkCommand.execute(myTasks);
        assertEquals(toDos.toString(), "[T][ ] Hello");
    }
}
