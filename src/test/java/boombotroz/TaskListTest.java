package boombotroz;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



/**
 * Test methods in TaskList class.
 */

public class TaskListTest {
    private Ui ui = new Ui();
    private TaskList tl = new TaskList();

    /**
     * Tests if getAll method in TaskList class works as intended.
     *
     * @throws BoomException If invalid date range given.
     */
    @Test
    public void getAllTest() throws BoomException {

        Task task1 = new ToDo(true, "cook", 1);
        Task task2 = new ToDo(false, "clean", 2);
        tl.addTask(task1);
        tl.addTask(task2);
        assertEquals("[T][X][1] cook\n[T][ ][2] clean\n", tl.getAll());
        Task task3 = new Deadline(true, "eat", "2030-01-01", 1);
        task3.hasDate(ui);
        tl.addTask(task3);
        assertEquals("[T][X][1] cook\n[T][ ][2] clean\n"
                + "[D][X][1] eat (by: Jan 1 2030)\n", tl.getAll());
    }

}
