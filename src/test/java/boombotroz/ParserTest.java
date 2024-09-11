package boombotroz;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;



/**
 * Test methods in Parser class.
 */

public class ParserTest {

    private Parser p = new Parser();
    private TaskList tl = new TaskList();
    private Storage s;
    private Ui ui = new Ui();

    /**
     * Tests if deleteTask method in Parser class works as intended.
     *
     * @throws BoomException If position not given OR position out of range.
     * @throws IOException If writing to file has issue.
     */
    @Test
    public void deleteTaskTest() throws BoomException, IOException {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "ip", "src", "test");
        s = new Storage(path.toString() + "/data-text.txt");
        Task task1 = new ToDo(true, "cook", 1);
        Task task2 = new ToDo(false, "clean", 2);
        tl.addTask(task1);
        tl.addTask(task2);
        p.deleteTask(tl, "delete 2", s, ui);
        assertEquals("[T][X][1] cook\n", tl.getAll());
    }
}
