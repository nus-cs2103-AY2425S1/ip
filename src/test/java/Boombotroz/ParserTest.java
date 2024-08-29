package Boombotroz;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test methods in Parser class.
 */

public class ParserTest {

    Parser p = new Parser();
    TaskList tl = new TaskList();
    Storage s = new Storage("../data-test.txt");
    Ui ui = new Ui();

    /**
     * Tests if deleteTask method in Parser class works as intended.
     *
     * @throws BoomException If position not given OR position out of range.
     * @throws IOException If writing to file has issue.
     */
    @Test
    public void deleteTaskTest() throws BoomException, IOException {
        Task task1 = new ToDo(true, "cook");
        Task task2 = new ToDo(false, "clean");
        tl.addTask(task1);
        tl.addTask(task2);
        p.deleteTask(tl, "delete 2", s, ui);
        assertEquals("[T][X] cook\n", tl.getAll());
    }
}
