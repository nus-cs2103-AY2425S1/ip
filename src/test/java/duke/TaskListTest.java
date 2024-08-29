package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void test() throws DuckException {
        TaskList list = new TaskList(new Task[100], 0);
        list.add(new Todo("t1"));
        list.add(new Todo("t2"));
        list.add(new Todo("t3"));
        assertEquals(list.getCmdNum(), 3);
        list.delete(2);
        assertEquals(list.getCmdNum(), 2);
        list.mark(1);
        assertEquals(list.get(0).isDone, true);

    }
}