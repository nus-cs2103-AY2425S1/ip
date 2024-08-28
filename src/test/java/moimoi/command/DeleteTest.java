package moimoi.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import moimoi.Storage;
import moimoi.TaskList;
import moimoi.Ui;
import moimoi.exception.InvalidIndexException;
import moimoi.exception.MoiMoiException;
import moimoi.task.Task;
import moimoi.task.Todo;

public class DeleteTest {

    private Storage storage;
    private Task task = new Todo("dummy");
    private TaskList tasks = new TaskList(new ArrayList<Task>());
    private Ui ui = new Ui();

    @Test
    public void testisExit() {
        this.tasks.add(this.task);
        DeleteCommand deleteCommand = new DeleteCommand("1");
        assertFalse(deleteCommand.isExit());
    }

    @Test
    public void execute_invalidIndex_invalidIndexExceptionThrown() {

        try {
            DeleteCommand deleteCommand = new DeleteCommand("1");
            deleteCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        this.tasks.add(this.task);

        try {
            DeleteCommand deleteCommand = new DeleteCommand("a");
            deleteCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        try {
            DeleteCommand deleteCommand = new DeleteCommand("10");
            deleteCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        try {
            DeleteCommand deleteCommand = new DeleteCommand("-1");
            deleteCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        try {
            DeleteCommand deleteCommand = new DeleteCommand("0");
            deleteCommand.execute(this.storage, this.tasks, this.ui);
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
            deleteCommand.execute(this.storage, this.tasks, this.ui);
        } catch (MoiMoiException e) {
            fail();
        }

    }

}
