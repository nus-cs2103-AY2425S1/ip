package moimoi.util.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import moimoi.util.Storage;
import moimoi.util.TaskList;
import moimoi.util.exception.InvalidIndexException;
import moimoi.util.exception.MoiMoiException;
import moimoi.util.task.Task;
import moimoi.util.task.Todo;

public class UnmarkCommandTest {

    private Storage storage = new Storage("data/test.txt");
    private Task task = new Todo("dummy");
    private TaskList tasks = new TaskList(new ArrayList<Task>());

    @Test
    public void testIsExit() {
        this.tasks.add(this.task);
        UnmarkCommand unmarkCommand = new UnmarkCommand("1");
        assertFalse(unmarkCommand.isExit());
    }

    @Test
    public void execute_invalidIndex_invalidIndexExceptionThrown() {

        try {
            UnmarkCommand unmarkCommand = new UnmarkCommand("1");
            unmarkCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        this.tasks.add(this.task);

        try {
            UnmarkCommand unmarkCommand = new UnmarkCommand("a");
            unmarkCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        try {
            UnmarkCommand unmarkCommand = new UnmarkCommand("10");
            unmarkCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        try {
            UnmarkCommand unmarkCommand = new UnmarkCommand("-1");
            unmarkCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        try {
            UnmarkCommand unmarkCommand = new UnmarkCommand("0");
            unmarkCommand.execute(this.storage, this.tasks);
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
            unmarkCommand.execute(this.storage, this.tasks);
        } catch (MoiMoiException e) {
            fail();
        }

    }

}
