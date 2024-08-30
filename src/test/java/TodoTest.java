import Tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {
    @Test
    public void toFileFormatTest() {
        Todo todo = new Todo("1");
        String toFileFormat = todo.toFileFormat();
        assertEquals("T | 0 | 1", toFileFormat);
    }
}