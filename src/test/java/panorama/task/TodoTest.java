package panorama.task; // same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    private String name = "Todo Task";
    private Todo t = new Todo(name);

    @Test
    public void toString_success() {
        String expected = "[T] [ ] " + name;
        assertEquals(expected, t.toString());
    }

    @Test
    public void toFileString_success() {
        String expected = "T|0|" + name;
        assertEquals(expected, t.toFileString());
    }
}

