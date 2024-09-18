package tayoo.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void toString_anyString_returnsCorrectString() {
        String taskName = "Return Book";
        Task testTask1 = new Task(taskName, false);
        String expected = "[ ] Return Book";
        String actual = testTask1.toString();
        assertEquals(expected, actual);

        Task testTask2 = new Task(taskName, true);
        expected = "[X] Return Book";
        actual = testTask2.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void toTxt_anyTask_returnsCorrectTxtRepresentation() {
        assertDoesNotThrow( () -> {
            String taskName = "Return Book";
            Task testTask1 = new Task(taskName, true);
            String expected = "Task | true | Return Book";
            String actual = testTask1.toTxt();
            assertEquals(expected, actual);

            Task testTask2 = new Task(taskName, false);
            expected = "Task | false | Return Book";
            actual = testTask2.toTxt();
            assertEquals(expected, actual);
        });
    }
}
