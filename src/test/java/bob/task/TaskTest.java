package bob.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    public void TaskConstructorTest() {
        Task task = new Task("description");
        assertEquals(task.getDescription(), "description");
        assertEquals(task.getStatusIcon(), " ");
    }

    public void markAsDoneTest() {
        Task task = new Task("description");
        task.markAsDone();
        assertEquals(task.getStatusIcon(), "X");
    }
}
