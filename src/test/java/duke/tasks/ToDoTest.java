package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testUnmarked() {
        ToDo task = new ToDo("todo");
        assertEquals(task.toString(), "[T][ ] todo");
    }

    @Test
    public void testMarked() {
        ToDo task = new ToDo("todo");
        task.mark(true);
        assertEquals(task.toString(), "[T][X] todo");
    }
}
