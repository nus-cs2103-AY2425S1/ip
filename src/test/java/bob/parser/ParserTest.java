package bob.parser;

import bob.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void validFileFormat() {
        Todo todo = new Todo("Code assignment");
        String expected = "T | 0 | Code assignment";
        String actual = todo.fileFormat();
        assertEquals(expected, actual);
    }
}
