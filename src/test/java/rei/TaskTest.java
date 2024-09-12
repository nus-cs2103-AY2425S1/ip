package rei;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void storeDataTest() {
        Task aToDo = Task.createToDo("task");
        Task aDeadline = Task.createDeadline("task2",
                LocalDateTime.of(2024, Month.SEPTEMBER, 12, 14, 00));
        Task anEvent = Task.createEvent("task3",
                LocalDateTime.of(2024, Month.AUGUST, 17, 8, 00),
                LocalDateTime.of(2024, Month.AUGUST, 17, 22, 00));

        aDeadline.markAsDone();

        assertEquals("T | 0 | task", aToDo.toStoringFormat());
        assertEquals("D | 1 | task2 | 2024-09-12T14:00", aDeadline.toStoringFormat());
        assertEquals("E | 0 | task3 | 2024-08-17T08:00 to 2024-09-17T22:00", anEvent.toStoringFormat());
    }


}
