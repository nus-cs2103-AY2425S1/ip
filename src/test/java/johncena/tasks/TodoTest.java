package johncena.tasks;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
