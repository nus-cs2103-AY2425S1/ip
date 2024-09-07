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
    public void execute_invalidToDoTask_throwsException() throws JeffException {
        Command c = new AddToDoCommand("todo");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals("Sorry, the description of a todo cannot be empty!", exception.toString());
    }
}
