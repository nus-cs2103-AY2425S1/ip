package citadel;

import citadel.Task.TaskList;
import citadel.commands.HandleDeadline;
import citadel.exception.CitadelException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for handling Deadline tasks.
 */
public class DeadlineTest {

    /**
     * Tests the creation and handling of a deadline task.
     */
    @Test
    public void testDeadline() {
        try {
            String input = "deadline sleep /by 11/01/2024 13:00";
            TaskList tasks = new TaskList();
            new HandleDeadline(input, tasks).run();

            // Verify task creation
            System.out.println(tasks.get(0).printTask());
            assertEquals("[D][ ] sleep (by: 11/01/2024 13:00)", tasks.get(0).toString());
            assertEquals("[D][ ] sleep (by: 11 01 2024 13:00)", tasks.get(0).printTask());

            // Verify task marking as done
            tasks.get(0).markAsDone();
            assertEquals("[D][X] sleep (by: 11/01/2024 13:00)", tasks.get(0).toString());
            assertEquals("[D][X] sleep (by: 11 01 2024 13:00)", tasks.get(0).printTask());
        } catch (CitadelException e) {
            assertEquals(true, false);
        }
    }
}
