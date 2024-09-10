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

public class DeleteTest {

    private Storage storage = new Storage("data/test.txt");
    private Task task = new Todo("dummy");
    private TaskList tasks = new TaskList(new ArrayList<Task>());

    @Test
    public void testIsExit() {
        this.tasks.add(this.task);
        DeleteCommand deleteCommand = new DeleteCommand("1");
        assertFalse(deleteCommand.isExit());
    }

    @Test
    public void execute_invalidIndex_invalidIndexExceptionThrown() {

        try {
            DeleteCommand deleteCommand = new DeleteCommand("1");
            deleteCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        this.tasks.add(this.task);

        try {
            DeleteCommand deleteCommand = new DeleteCommand("a");
            deleteCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        try {
            DeleteCommand deleteCommand = new DeleteCommand("10");
            deleteCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        try {
            DeleteCommand deleteCommand = new DeleteCommand("-1");
            deleteCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        try {
            DeleteCommand deleteCommand = new DeleteCommand("0");
            deleteCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

    }

    @Test
    public void execute_validIndex_success() {

        this.tasks.add(this.task);

        try {
            DeleteCommand deleteCommand = new DeleteCommand("1");
            deleteCommand.execute(this.storage, this.tasks);
        } catch (MoiMoiException e) {
            fail();
        }

    }

}
