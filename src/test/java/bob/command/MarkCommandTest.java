package bob.command;

import bob.tasks.TaskList;
import bob.tasks.ToDos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarkCommandTest {

    @Test
    public void IsRunningTest() {
        MarkCommand markCommand = new MarkCommand(1);
        assertTrue(markCommand.isRunning());
    }

    @Test
    public void testExecute() {
        TaskList myTasks = new TaskList();
        ToDos toDos = new ToDos("Hello");
        myTasks.addTask(toDos);
        MarkCommand markCommand = new MarkCommand(0);
        markCommand.execute(myTasks);
        assertEquals(toDos.toString(), "[T][X] Hello");
    }
}
