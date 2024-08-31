package muller.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class TaskTest {
    @Test
    void testSetDate() {
        Task task = new Task("return book");
        task.setType("D");
        LocalDate date = LocalDate.of(2023, 12, 25);
        task.setDate(date);
        assertTrue(task.isOnDate(date));
    }
}
