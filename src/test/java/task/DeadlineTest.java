package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testInit() {
        Task task = new Deadline("return book", "2024-09-23 1700");
        assertEquals("[D][ ] return book (by: Sep 23 2024, 05:00 PM) tags:", task.toString());
    }
}
