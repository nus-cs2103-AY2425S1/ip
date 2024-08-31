import Mario.Tasks.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testGetName() {
        ToDo todo1 = new ToDo("Task name", false);
        String expected1 = "[T][ ] Task name";
        assertEquals(expected1, todo1.getName());

        ToDo todo2 = new ToDo("Task name", true);
        String expected2 = "[T][X] Task name";
        assertEquals(expected2, todo2.getName());
    }

    @Test
    public void testToFileFormat() {
        ToDo todo1 = new ToDo("Task1 name", false);
        String expected1 = "T | 0 | Task1 name";
        assertEquals(expected1, todo1.toFileFormat());

        ToDo todo2 = new ToDo("Task2 name", true);
        String expected2 = "T | 1 | Task2 name";
        assertEquals(expected2, todo2.toFileFormat());
    }
}
