package potong;

import org.junit.jupiter.api.Test;
import potong.command.AddCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class ParserTest {

    @Test
    public void testParse() {
        assertEquals("[T][ ] read book", Parser.parse("todo read book").toString());
    }

    @Test
    public void testCreateTask() throws Exception {
        assertEquals("[T][X] read book", Parser.createTask("T | 1 | read book").toString());
    }
}
