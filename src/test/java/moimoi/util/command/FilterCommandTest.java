package moimoi.util.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import moimoi.util.Storage;
import moimoi.util.TaskList;
import moimoi.util.exception.InvalidDateTimeException;
import moimoi.util.exception.MoiMoiException;
import moimoi.util.task.Task;

public class FilterCommandTest {

    private Storage storage;
    private TaskList tasks = new TaskList(new ArrayList<Task>());

    @Test
    public void testisExit() {
        FilterCommand filterCommand = new FilterCommand("2024-08-24");
        assertFalse(filterCommand.isExit());
    }

    @Test
    public void execute_invalidDate_invalidDateTimeExceptionThrown() {
        try {
            FilterCommand filterCommand = new FilterCommand("2024/08/24");
            filterCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidDateTimeException("date").getMessage(), e.getMessage());
        }
    }

    @Test
    public void execute_validDate_success() {
        try {
            FilterCommand filterCommand = new FilterCommand("2024-08-24");
            filterCommand.execute(this.storage, this.tasks);
        } catch (MoiMoiException e) {
            fail();
        }
    }

}
