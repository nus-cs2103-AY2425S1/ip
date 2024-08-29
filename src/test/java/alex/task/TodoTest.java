package alex.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] CS coding assignment",
                new Todo("CS coding assignment", false).toString());
    }
}
