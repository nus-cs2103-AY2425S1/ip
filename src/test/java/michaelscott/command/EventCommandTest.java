package michaelscott.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import michaelscott.task.Event;
import michaelscott.task.TaskList;
import michaelscott.utils.MichaelScottException;

public class EventCommandTest {

    @Test
    void testExecuteWithValidInput() {
        TaskList taskList = new TaskList();
        try {
            EventCommand eventCommand = new EventCommand("Class /from 2024-03-03 12:00 /to 2024-03-03 12:30");
            String result = eventCommand.execute(taskList);

            assertEquals(1, taskList.size());
            Event addedTask = (Event) taskList.getTask(0);
            assertEquals("Class", addedTask.getDescription());
            assertEquals(LocalDateTime.of(2024, 3, 3, 12, 0), addedTask.getFrom());
            assertEquals(LocalDateTime.of(2024, 3, 3, 12, 30), addedTask.getTo());

            assertEquals("Got it. I've added this task: \n"
                    + "   " + addedTask.toString() + "\n" + "Now you have 1 task in the list.", result);
        } catch (MichaelScottException e) {
            fail("Exception should not have been thrown for valid input.");
        }
    }

    @Test
    void testExecuteWithInvalidDateFormat() {
        MichaelScottException exception = assertThrows(MichaelScottException.class, () -> {
            new EventCommand("homework /from 2024/03/02 12:00 /to 2024/04/02 12:30");
        });

        assertEquals("Whoa, that date’s all messed up! Try again, but this time use the format "
                + "like this:YYYY-MM-DD HH:MM. It’s not rocket science... I think!", exception.getMessage());
    }

    @Test
    void testExecuteWithMissingDescriptionOrDate() {
        TaskList taskList = new TaskList();
        MichaelScottException exception = assertThrows(MichaelScottException.class, () -> {
            new EventCommand("homework /from");
        });

        assertEquals("Alright, I need the event description, the start time, and the end time. "
                + "Easy-peasy.Here’s a little example to make it crystal "
                + "clear:'Event Career fair /from 2024-02-02 12:00 /to 2024-02-02 17:00.", exception.getMessage());
    }
}
