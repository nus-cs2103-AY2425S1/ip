package bigdog;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BigdogTest {

    @Test
    public void buildTest(){
        assertEquals(2, 2);
    }

    @Test
    public void testList() {
        assertEquals("Got it. I've added this task:\n[T][ ] read a book\nNow you have 1 tasks in the list.\n",
                (new TaskList(new ArrayList<Task>())).add(Todo.of("read a book")));
    }

}
