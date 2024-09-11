package alex.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] CS coding assignment",
                new Todo("CS coding assignment", false).toString());
    }


}
