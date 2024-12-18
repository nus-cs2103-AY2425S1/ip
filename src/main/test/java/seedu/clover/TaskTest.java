import barry.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testMarkAsDone() {
        Task task = new Task("Test Task");
        task.markAsDone();
        assertTrue(task.isDone(), "Task should be marked as done.");
    }
}
