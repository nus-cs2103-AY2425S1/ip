package moimoi.util.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import moimoi.util.Storage;
import moimoi.util.TaskList;
import moimoi.util.exception.InvalidDateTimeException;
import moimoi.util.exception.InvalidPeriodException;
import moimoi.util.exception.MissingArgumentException;
import moimoi.util.exception.MoiMoiException;
import moimoi.util.task.Task;

public class AddCommandTest {

    private Storage storage = new Storage("data/test.txt");
    private TaskList tasks = new TaskList(new ArrayList<Task>());

    @Test
    public void testIsExit() {
        AddCommand addCommand = new AddCommand(CommandEnum.TODO, "dummy");
        assertFalse(addCommand.isExit());
    }

    @Test
    public void execute_missingArguments_missingArgumentExceptionThrown() {

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.DEADLINE, "dummy");
            addCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new MissingArgumentException().getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.DEADLINE, "dummy /before 2024-08-22 18:00");
            addCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new MissingArgumentException().getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.EVENT,
                    "dummy /start 2024-08-22 18:00 /to 2024-08-22 18:30");
            addCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new MissingArgumentException().getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.EVENT,
                    "dummy /from 2024-08-22 40:00 /end 2024-08-22 18:30");
            addCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new MissingArgumentException().getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.DEADLINE, " /by 2024-08-22 18:00");
            addCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new MissingArgumentException().getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.PERIOD, "dummy /for ");
            addCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new MissingArgumentException().getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.EVENT,
                    "dummy /from  /to 2024-08-22 18:30");
            addCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new MissingArgumentException().getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.EVENT,
                    "dummy /from 2024-08-22 18:00 /to ");
            addCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new MissingArgumentException().getMessage(), e.getMessage());
        }

    }

    @Test
    public void execute_invalidDateTime_invalidDateTimeExceptionThrown() {

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.DEADLINE, "dummy /by 2024-08-22 40:00");
            addCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidDateTimeException("date-time").getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.DEADLINE, "dummy /by 2024-8-22 18:00");
            addCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidDateTimeException("date-time").getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.DEADLINE, "dummy /by 2024-8-22 1:00");
            addCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidDateTimeException("date-time").getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.EVENT,
                    "dummy /from 2024-08-22 18:00 /to 2024-08-40 18:30");
            addCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidDateTimeException("date-time").getMessage(), e.getMessage());
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.EVENT,
                    "dummy /from 2024-08-40 18:00 /to 2024-09-22 18:30");
            addCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidDateTimeException("date-time").getMessage(), e.getMessage());
        }

    }

    @Test
    public void execute_invalidPeriod_invalidPeriodExceptionThrown() {
        try {
            AddCommand addCommand = new AddCommand(CommandEnum.PERIOD, "dummy /for lol");
            addCommand.execute(this.storage, this.tasks);
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new InvalidPeriodException().getMessage(), e.getMessage());
        }
    }

    @Test
    public void execute_validInputs_success() {

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.DEADLINE, "dummy /by 2024-08-22 18:00");
            addCommand.execute(this.storage, this.tasks);
        } catch (MoiMoiException e) {
            fail();
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.EVENT,
                    "dummy /from 2024-08-22 18:00 /to 2024-08-22 18:30");
            addCommand.execute(this.storage, this.tasks);
        } catch (MoiMoiException e) {
            fail();
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.PERIOD, "dummy /for 17");
            addCommand.execute(this.storage, this.tasks);
        } catch (MoiMoiException e) {
            fail();
        }

        try {
            AddCommand addCommand = new AddCommand(CommandEnum.PERIOD, "dummy /for 17.5");
            addCommand.execute(this.storage, this.tasks);
        } catch (MoiMoiException e) {
            fail();
        }

    }

}
