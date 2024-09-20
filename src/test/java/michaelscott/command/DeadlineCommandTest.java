package michaelscott.command;

import michaelscott.utils.MichaelScottException;
import michaelscott.task.TaskList;
import michaelscott.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

class DeadlineCommandTest {

    @Test
    void testExecuteWithValidInput() {
        TaskList taskList = new TaskList();
        try {
            DeadlineCommand deadlineCommand = new DeadlineCommand("homework /by 2024-03-02 12:00");
            String result = deadlineCommand.execute(taskList);

            assertEquals(1, taskList.size());
            Deadline addedTask = (Deadline) taskList.getTasks().get(0);
            assertEquals("homework", addedTask.getDescription());
            assertEquals(LocalDateTime.of(2024, 3, 2, 12, 0), addedTask.getDeadlineDate());

            assertEquals("Got it. I've added this task:\n" + addedTask.toString() +
                    "\nNow you have 1 task in the list.", result);

        } catch (MichaelScottException e) {
            fail("Exception should not have been thrown for valid input.");
        }
    }

    @Test
    void testExecuteWithInvalidDateFormat() {
        MichaelScottException exception = assertThrows(MichaelScottException.class, () -> {
            new DeadlineCommand("homework /by 2024/03/02 12:00");
        });

        assertEquals("Whoa, that date’s all messed up! Try again, but this time use the format like this:YYYY-MM-DD HH:MM. It’s not rocket science... I think!", exception.getMessage());
    }

    @Test
    void testExecuteWithMissingDescriptionOrDate() {
        TaskList taskList = new TaskList();
        MichaelScottException exception = assertThrows(MichaelScottException.class, () -> {
            new DeadlineCommand("homework");  // Missing /by and date
        });

        assertEquals("Okay, here’s what I need from you. Give me the task and the deadline—easy, right?Oh, and here’s an example, because I’m a great teacher:'deadline homework /by 2024-03-02 12:00.' ", exception.getMessage());
    }
}