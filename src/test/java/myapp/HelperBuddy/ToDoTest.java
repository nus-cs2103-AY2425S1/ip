package myapp.helperbuddy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testParsing() {
        String taskData = "T | 1 | evening workout";
        String[] parts = taskData.split(" \\| ");
        String description = parts[2].trim();
        ToDo expectedTask = new ToDo(description);
        ToDo actualTask = ToDo.parseTask(taskData);
        assertEquals(expectedTask.getDescription(), actualTask.getDescription());
    }

    @Test
    public void testFormatting() {
        String taskData = "T | 1 | evening workout";
        String[] parts = taskData.split(" \\| ");
        String description = parts[2].trim();
        ToDo task = new ToDo(description);
        task.markDone();
        String expected = "T | " + (task.getDone() ? "1" : "0") + " | " + task.getDescription();
        String actual = "T | 1 | evening workout";
        assertEquals(expected, actual);
    }
}
