package taskon.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] study for test", new Todo("study for test").toString());
    }

}
