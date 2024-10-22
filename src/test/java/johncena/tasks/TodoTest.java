package johncena.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * This class tests the Todo class.
 */
public class TodoTest {

    /**
     * Tests the string conversion of a Todo object.
     */
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] study", new Todo("study").toString());
    }
}
