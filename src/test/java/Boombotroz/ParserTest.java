package Boombotroz;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    Parser p = new Parser();
    TaskList tl = new TaskList();
    Storage s = new Storage("../data-test.txt");
    Ui ui = new Ui();

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
