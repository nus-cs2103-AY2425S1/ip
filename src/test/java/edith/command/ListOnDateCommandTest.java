package edith.command;

import edith.Ui;
import edith.Storage;
import edith.EdithException;
import edith.task.Deadline;
import edith.task.Event;
import edith.task.TaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ListOnDateCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("./data/test-edith.txt");
    }

    @Test
    public void execute_validDateWithTasks_success() {
        tasks.addTask(new Deadline("Return book", "2/11/2024 1800"));
        tasks.addTask(new Event("Meeting", "2/11/2024 1800", "2/11/2024 2200"));
        ListOnDateCommand command = new ListOnDateCommand("2/11/2024");

        try {
            String output = command.execute(tasks, ui, storage);
            assertTrue(output.contains("Return book"));
            assertTrue(output.contains("Meeting"));
        } catch (EdithException e) {
            fail("Exception should not be thrown for a valid date.");
        }
    }

    @Test
    public void execute_validDateNoTasks_success() {
        ListOnDateCommand command = new ListOnDateCommand("2/11/2024");

        try {
            String output = command.execute(tasks, ui, storage);
            assertTrue(output.contains("NOTHING"));
        } catch (EdithException e) {
            fail("Exception should not be thrown for a valid date.");
        }
    }

    @Test
    public void execute_invalidDateFormat_edithExceptionThrown() {
        try {
            ListOnDateCommand command = new ListOnDateCommand("2nd November 2024");
            command.execute(tasks, ui, storage);
            fail();
        } catch (EdithException e) {
            assertEquals("Invalid date/time format. Please use 'day/month/year HHmm' (e.g '13/9/2024 1800').", e.getMessage());
        }
    }
}
