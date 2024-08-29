package Boombotroz;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    Ui ui = new Ui();
    TaskList tl = new TaskList();

    @Test
    public void getAllTest() throws BoomException {

        Task task1 = new ToDo(true, "cook");
        Task task2 = new ToDo(false, "clean");
        tl.addTask(task1);
        tl.addTask(task2);
        assertEquals("[T][X] cook\n[T][ ] clean\n", tl.getAll());
        Task task3 = new Deadline(true, "eat", "2030-01-01");
        task3.hasDate(ui);
        tl.addTask(task3);
        assertEquals("[T][X] cook\n[T][ ] clean\n" +
                "[D][X] eat (by: Jan 1 2030)\n", tl.getAll());
    }

}
