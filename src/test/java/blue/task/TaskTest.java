package blue.task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskTest {

    @Test
    public void testFromFileString() {
        Task result = Task.fromFileString("D | 0 | return book | 12/9/2024 1800");
        assertEquals(new DeadlineTask("return book", "12/9/2024 1800"), result);
    }

    @Test
    public void testFromFileString2() {
        Task result = Task.fromFileString("T | 1 | Prepare for interview");
        Task expected = new ToDoTask("Prepare for interview");
        expected.markAsDone();
        assertEquals(expected, result);
    }
}
