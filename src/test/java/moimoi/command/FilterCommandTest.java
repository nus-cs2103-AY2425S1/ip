package moimoi.command;

import java.util.ArrayList;
import moimoi.Storage;
import moimoi.TaskList;
import moimoi.Ui;
import moimoi.exception.InvalidDateTimeException;
import moimoi.exception.MoiMoiException;
import moimoi.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class FilterCommandTest {

    Command command;
    Storage storage;
    TaskList tasks = new TaskList(new ArrayList<Task>());
    Ui ui = new Ui();

    @Test
    public void testisExit() {
        this.command = new FilterCommand("2024-08-24");
        assertFalse(this.command.isExit());
    }

    @Test
    public void execute_invalidDate_invalidDateTimeExceptionThrown() {
        try {
            this.command = new FilterCommand("2024/08/24");
            this.command.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidDateTimeException("date").getMessage(), e.getMessage());
        }
    }

    @Test
    public void execute_validDate_success() {
        try {
            this.command = new FilterCommand("2024-08-24");
            this.command.execute(this.storage, this.tasks, this.ui);
        } catch (MoiMoiException e) {
            fail();
        }
    }

}
