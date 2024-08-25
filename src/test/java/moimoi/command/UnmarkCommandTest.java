package moimoi.command;

import java.util.ArrayList;
import moimoi.Storage;
import moimoi.TaskList;
import moimoi.Ui;
import moimoi.exception.InvalidIndexException;
import moimoi.exception.MoiMoiException;
import moimoi.task.Task;
import moimoi.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class UnmarkCommandTest {

    Storage storage;
    Task task = new Todo("dummy");
    TaskList tasks = new TaskList(new ArrayList<Task>());
    Ui ui = new Ui();

    @Test
    public void testisExit() {
        this.tasks.add(this.task);
        UnmarkCommand unmarkCommand = new UnmarkCommand("1");
        assertFalse(unmarkCommand.isExit());
    }

    @Test
    public void execute_invalidIndex_invalidIndexExceptionThrown() {

        try {
            UnmarkCommand unmarkCommand = new UnmarkCommand("1");
            unmarkCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        this.tasks.add(this.task);

        try {
            UnmarkCommand unmarkCommand = new UnmarkCommand("a");
            unmarkCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        try {
            UnmarkCommand unmarkCommand = new UnmarkCommand("10");
            unmarkCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        try {
            UnmarkCommand unmarkCommand = new UnmarkCommand("-1");
            unmarkCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        try {
            UnmarkCommand unmarkCommand = new UnmarkCommand("0");
            unmarkCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

    }

    @Test
    public void execute_validIndex_success() {

        this.tasks.add(this.task);

        try {
            UnmarkCommand unmarkCommand = new UnmarkCommand("1");
            unmarkCommand.execute(this.storage, this.tasks, this.ui);
        } catch (MoiMoiException e) {
            fail();
        }

    }

}
