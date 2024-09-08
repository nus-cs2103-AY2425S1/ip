package futureyou;

import org.junit.jupiter.api.Test;

import futureyou.task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@link Deadline} class.
 */
public class DeadlineTest {
    @Test
    public void Events_validCommand_eventCreated() {
        String taskName = "Submit 2103";

        DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMM yyyy - HH:mm");
        LocalDateTime deadline = LocalDateTime.of(2024, 9, 05, 14, 0);
        Deadline newDeasdlineTask = new Deadline(taskName, deadline);

        assertEquals(deadline.format(format), newDeasdlineTask.getDeadline());
    }

}
