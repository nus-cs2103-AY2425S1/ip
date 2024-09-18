package commands;

import org.junit.jupiter.api.Test;
import storage.Storage;
import tasks.DeadlineTask;
import tasks.TaskList;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {
    @Test
    public void execute_deadlineTask_correctString() {
        DeadlineTask deadlineTask = new DeadlineTask("Task", "2024-09-20");
        DeadlineCommand deadlineCommand= new DeadlineCommand(deadlineTask);
        String expected = "New deadline task! Grrr:\n" + deadlineTask + "\n" + String.format("Now you have %d tasks to PUNCH!%n", 1);
        assertEquals(expected, deadlineCommand.execute(new TaskList(), new Ui(), new Storage("doesnt matter")));
    }
}
