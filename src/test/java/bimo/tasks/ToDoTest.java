package bimo.tasks;

import bimo.BimoException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toString_emptyInput() throws BimoException {
        Task todo = new ToDo("Attend lesson");
        assertEquals("[T][ ] Attend lesson", todo.toString());
    }

}
