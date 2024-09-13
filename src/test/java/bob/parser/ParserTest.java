package bob.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.task.Todo;
public class ParserTest {
    @Test
    public void validFileFormat() {
        Todo todo = new Todo("Code assignment");
        String expected = "T | 0 | Code assignment";
        String actual = todo.getFileFormat();
        assertEquals(expected, actual);
    }
}
