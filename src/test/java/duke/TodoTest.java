package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void test1(){
        Task t = new Todo("submit work");
        assertEquals(t.isDone, false);
        t.mark();
        assertEquals(t.isDone, true);
    }

    @Test
    public void test2(){
        Task t = new Todo("submit work");
        assertEquals(t.isDone, false);
        t.unmark();
        assertEquals(t.isDone, false);
    }
}

