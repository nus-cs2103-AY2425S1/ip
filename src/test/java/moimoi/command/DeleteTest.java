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

public class DeleteTest {

    Command command;
    Storage storage;
    Task task = new Todo("dummy");
    TaskList tasks = new TaskList(new ArrayList<Task>());
    Ui ui = new Ui();

    @Test
    public void testisExit() {
        this.tasks.add(this.task);
        this.command = new DeleteCommand("1");
        assertFalse(this.command.isExit());
    }

    @Test
    public void execute_invalidIndex_invalidIndexExceptionThrown() {

        try {
            this.command = new DeleteCommand("1");
            this.command.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        this.tasks.add(this.task);

        try {
            this.command = new DeleteCommand("a");
            this.command.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        try {
            this.command = new DeleteCommand("10");
            this.command.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        try {
            this.command = new DeleteCommand("-1");
            this.command.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

        try {
            this.command = new DeleteCommand("0");
            this.command.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidIndexException().getMessage(), e.getMessage());
        }

    }

    @Test
    public void execute_validIndex_success() {

        this.tasks.add(this.task);

        try {
            this.command = new DeleteCommand("1");
            this.command.execute(this.storage, this.tasks, this.ui);
        } catch (MoiMoiException e) {
            fail();
        }

    }

}
