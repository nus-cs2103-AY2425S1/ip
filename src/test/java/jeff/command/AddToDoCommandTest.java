package jeff.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.task.ToDoTask;

public class AddToDoCommandTest {
    private static final String WRONG_FORMAT_ERROR = "The format is wrong! It should be \"todo(or t) xx\"";

    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() throws JeffException {
        new File("data/tasks.txt").delete();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.loadTaskListFromDatabase());
    }

    @Test
    public void execute_addToDoTask() throws JeffException {
        Command c = new AddToDoCommand("todo read book");
        String response = c.execute(tasks, storage);

        assertEquals(1, tasks.size());
        assertInstanceOf(ToDoTask.class, tasks.getTaskByIndex(0));
        assertEquals("[T][  ] read book", tasks.getTaskByIndex(0).toString());
        assertEquals(" Got it. I've added this task:\n"
                        + "    [T][  ] read book\n"
                        + " Now you have 1 tasks in the list.\n",
                response);
    }

    @Test
    public void execute_addToDoTaskAlias() throws JeffException {
        Command c = new AddToDoCommand("t read book");
        String response = c.execute(tasks, storage);

        assertEquals(1, tasks.size());
        assertInstanceOf(ToDoTask.class, tasks.getTaskByIndex(0));
        assertEquals("[T][  ] read book", tasks.getTaskByIndex(0).toString());
        assertEquals(" Got it. I've added this task:\n"
                        + "    [T][  ] read book\n"
                        + " Now you have 1 tasks in the list.\n",
                response);
    }

    @Test
    public void execute_noDescriptionToDoTask_throwsException() throws JeffException {
        Command c = new AddToDoCommand("todo");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_noDescriptionToDoTaskAlias_throwsException() throws JeffException {
        Command c = new AddToDoCommand("t");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_noSpacingToDoTask_throwsException() throws JeffException {
        Command c = new AddToDoCommand("todoborrow book");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_noSpacingToDoTaskAlias_throwsException() throws JeffException {
        Command c = new AddToDoCommand("tborrow book");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }
}
