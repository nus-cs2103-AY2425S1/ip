package stobberi.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskTest {
    @Test
    public void testSetNotDone() {
        Task task1 = new Todo("sleep");
        Task task2 = new Deadline("homework", "27-08-2024 1900hrs");

        task1.setDone();
        task2.setDone();
        task1.setNotDone();
        task2.setNotDone();

        assertFalse(task1.getDone() && task2.getDone());
    }
}