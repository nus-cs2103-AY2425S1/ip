package potong;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testCreateTask() {
        assertEquals("[T][X][ ] read book", Parser.loadSavedTasks("T | 1 |  | read book").toString());
    }
}
