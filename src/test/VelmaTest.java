package velma;
import velma.task.Task;
import velma.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class VelmaTest {
    private Velma velma;
    private final InputStream systemIn = System.in;

    @BeforeEach
    public void setUp() {
        velma = new Velma("data/tasks.txt");
    }

    @Test
    public void testRun_addTodo() {
        String input = "todo read book";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        velma.run();

        ArrayList<Task> tasks = velma.getTasks().getTasks();
        assertEquals(1, tasks.size(), "There should be one task in the task list.");
        assertTrue(tasks.get(0) instanceof Todo, "The task is a todo task.");
        assertEquals("read book", tasks.get(0).getDescription(), "The task description should be 'read book'.");

    }
}
