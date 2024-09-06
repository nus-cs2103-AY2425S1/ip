package Mentos.components;

import Mentos.MentosException.MentosException;
import Mentos.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    void testListCommand() {
        ActionTaskIndexTuple result = parser.taskHandler("list");
        assertNotNull(result);
        assertEquals("list", result.getAction());
        assertNull(result.getTask());
        assertEquals(-1, result.getIndex());
    }

    @Test
    void testMarkCommandValid() {
        ActionTaskIndexTuple result = parser.taskHandler("mark 1");
        assertNotNull(result);
        assertEquals("mark", result.getAction());
        assertNull(result.getTask());
        assertEquals(1, result.getIndex());
    }

    @Test
    void testDeleteCommandValid() {
        ActionTaskIndexTuple result = parser.taskHandler("delete 3");
        assertNotNull(result);
        assertEquals("delete", result.getAction());
        assertNull(result.getTask());
        assertEquals(3, result.getIndex());
    }
}
