package commands;

import org.junit.jupiter.api.Test;
import storage.Storage;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.TaskList;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventCommandTest {
    @Test
    public void execute_eventTask_correctString() {
        EventTask eventTask = new EventTask("Task", "2024-09-20", "2024-09-21");
        EventCommand eventCommand= new EventCommand(eventTask);
        String expected = "New event task! Grrr:\n" + eventTask + "\n" + String.format("Now you have %d tasks to PUNCH!%n", 1);
        assertEquals(expected, eventCommand.execute(new TaskList(), new Ui(), new Storage("doesnt matter")));
    }
}
