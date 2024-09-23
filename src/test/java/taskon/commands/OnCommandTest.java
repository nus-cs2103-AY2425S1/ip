package taskon.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static taskon.common.Messages.MESSAGE_NO_TASKS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskon.exception.TaskonException;
import taskon.storage.Storage;
import taskon.task.Deadline;
import taskon.task.Event;
import taskon.task.TaskList;
import taskon.ui.Ui;

public class OnCommandTest {

    private TaskList taskList;
    private Ui ui;

    @BeforeEach
    public void setUp() throws TaskonException {
        taskList = new TaskList();
        ui = new Ui();

        // Adding test tasks
        taskList.addTask(new Deadline("Test deadline", "2024-09-30 1400"));
        taskList.addTask(new Event("Test event", "2024-09-30 0900", "2024-09-30 1700"));
        taskList.addTask(new Event("Another event", "2024-09-29 0900", "2024-09-29 1700"));
    }

    @Test
    public void testOnCommand() {
        OnCommand command = new OnCommand("2024-09-30");
        String result = command.execute(taskList, ui, new Storage("dummyPath"));

        String expected = "Tasks on 2024-09-30 :\n"
                + "[D][ ] Test deadline (by: Sep 30 2024, 2:00 PM)\n"
                + "[E][ ] Test event (from: Sep 30 2024, 9:00 AM to: Sep 30 2024, 5:00 PM)\n";

        assertEquals(expected, result);
    }

    @Test
    public void testOnCommandNoResults() {
        OnCommand command = new OnCommand("2024-10-01");
        String result = command.execute(taskList, ui, new Storage("dummyPath"));

        assertEquals(MESSAGE_NO_TASKS, result);
    }
}
