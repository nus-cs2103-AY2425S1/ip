package karen.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void toString_normalTodo_correctString() {
        String input1 = "shower";
        Todo t1 = new Todo(input1);
        assertEquals("[T][ ] shower", t1.toString());

        String input2 = "brush teeth";
        Todo t2 = new Todo(input2);
        assertEquals("[T][ ] brush teeth", t2.toString());

        String input3 = "take a poop";
        Todo t3 = new Todo(input3);
        assertEquals("[T][ ] take a poop", t3.toString());
    }

    @Test
    public void toFileString_normalTodo_correctString() {
        String input1 = "shower";
        Todo t1 = new Todo(input1);
        assertEquals("T | 0 | shower", t1.toFileString());

        String input2 = "brush teeth";
        Todo t2 = new Todo(input2);
        assertEquals("T | 0 | brush teeth", t2.toFileString());

        String input3 = "take a poop";
        Todo t3 = new Todo(input3);
        assertEquals("T | 0 | take a poop", t3.toFileString());
    }
}
