package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest {
    @Test
    public void addNote_normalInput_shouldAppendExisting() {
        Task todo = new Todo("test todo");
        todo.addNote("some note");
        assertEquals(todo.getNote(), "some note");
        todo.addNote("some note2");
        assertEquals("some note\nsome note2", todo.getNote());
    }
}
