package megamind.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] test",
                new Todo("test").toString());
    }

    @Test
    public void testStringConversion2() {
        assertEquals("[T][ ] 1111",
                new Todo("1111").toString());
    }
}
