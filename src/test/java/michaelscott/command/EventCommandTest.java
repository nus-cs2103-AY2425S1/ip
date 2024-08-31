package michaelscott.command;

import michaelscott.utils.MichaelScottException;
import michaelscott.task.TaskList;
import michaelscott.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

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

            assertEquals("Got it. I've added this task: \n" +
                    "   " + addedTask.toString() + "\n" + "Now you have 1 task in the list.", result);
        } catch (MichaelScottException e) {
            fail("Exception should not have been thrown for valid input.");
        }
    }
}
