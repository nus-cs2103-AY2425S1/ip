package opus;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testMarkAsDone() {
        Task task = new ToDo("test task");
        task.markAsDone();
        assertEquals("[T][X] test task", task.toString());
    }
}
