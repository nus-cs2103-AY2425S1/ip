package edith.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private String taskDescription = "Return book";
    private String dueDateString = "2/11/2024 1800";

    @Test
    public void constructor_validDate_success() {
        Deadline deadline = new Deadline(taskDescription, dueDateString);

        assertEquals("Return book", deadline.taskString());
        assertEquals(LocalDateTime.of(2024, 11, 2, 18, 0), deadline.getDueDate().atTime(18, 0));
    }

    @Test
    public void savedTaskString_validInput_success() {
        Deadline deadline = new Deadline(taskDescription, dueDateString);
        assertEquals("Return book /by 2/11/2024 1800", deadline.savedTaskString());
    }

    @Test
    public void toString_validInput_success() {
        Deadline deadline = new Deadline(taskDescription, dueDateString);
        String expectedString = "[D] [ ] Return book (due: Sat, 2 Nov 2024, 6PM)";
        assertEquals(expectedString, deadline.toString());
    }

    @Test
    public void getDueDate_validInput_success() {
        Deadline deadline = new Deadline(taskDescription, dueDateString);
        assertEquals(LocalDate.of(2024, 11, 2), deadline.getDueDate());
    }
}
