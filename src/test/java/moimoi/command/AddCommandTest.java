package moimoi.command;

import java.util.ArrayList;
import moimoi.Storage;
import moimoi.TaskList;
import moimoi.Ui;
import moimoi.exception.InvalidDateTimeException;
import moimoi.exception.MissingArgumentException;
import moimoi.exception.MoiMoiException;
import moimoi.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {

    Storage storage;
    TaskList tasks = new TaskList(new ArrayList<Task>());
    Ui ui = new Ui();

    @Test
    public void testisExit() {
        AddCommand addCommand = new AddCommand(CommandEnum.TODO, "dummy");
        assertFalse(addCommand.isExit());
    }

    @Test
    public void execute_missingArguments_missingArgumentExceptionThrown() {

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.DEADLINE, "dummy");
            addCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new MissingArgumentException().getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.DEADLINE,"dummy /before 2024-08-22 18:00");
            addCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new MissingArgumentException().getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.EVENT,
                    "dummy /start 2024-08-22 18:00 /to 2024-08-22 18:30");
            addCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new MissingArgumentException().getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.EVENT,
                    "dummy /from 2024-08-22 40:00 /end 2024-08-22 18:30");
            addCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new MissingArgumentException().getMessage(), e.getMessage());
        }

    }

    @Test
    public void execute_invalidDateTime_invalidDateTimeExceptionThrown() {

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.DEADLINE, "dummy /by 2024-08-22 40:00");
            addCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidDateTimeException("date-time").getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.DEADLINE, "dummy /by 2024-8-22 18:00");
            addCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidDateTimeException("date-time").getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.DEADLINE, "dummy /by 2024-8-22 1:00");
            addCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidDateTimeException("date-time").getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.EVENT,
                    "dummy /from 2024-08-22 18:00 /to 2024-08-40 18:30");
            addCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidDateTimeException("date-time").getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.EVENT,
                    "dummy /from 2024-08-40 18:00 /to 2024-09-22 18:30");
            addCommand.execute(this.storage, this.tasks, this.ui);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidDateTimeException("date-time").getMessage(), e.getMessage());
        }

    }

    @Test
    public void execute_validInputs_success() {

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.DEADLINE, "dummy /by 2024-08-22 18:00");
            addCommand.execute(this.storage, this.tasks, this.ui);
        } catch (MoiMoiException e) {
            fail();
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.EVENT,
                    "dummy /from 2024-08-22 18:00 /to 2024-08-22 18:30");
            addCommand.execute(this.storage, this.tasks, this.ui);
        } catch (MoiMoiException e) {
            fail();
        }

    }

}
