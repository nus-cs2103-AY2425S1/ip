package morgana.commands;

import static morgana.commands.MarkCommand.MESSAGE_SUCCESS;
import static morgana.common.Messages.MESSAGE_INVALID_TASK_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import morgana.exceptions.MorganaException;
import morgana.storage.Storage;
import morgana.task.TaskList;
import morgana.task.Todo;

public class MarkCommandTest {
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() throws MorganaException {
        tasks = new TaskList();
        storage = new Storage("temp/morgana.txt");
    }

    @AfterEach
    public void tearDown() {
        new File("temp/morgana.txt").delete();
        new File("temp").delete();
    }

    @Test
    public void markCommand_validInput_success() throws MorganaException {
        Todo todo = new Todo("return book");
        tasks.add(todo);

        Command command = new MarkCommand(0);
        String expected = MESSAGE_SUCCESS.formatted(1, "[T][X] return book");
        String actual = command.execute(tasks, storage);
        assertEquals(expected, actual);
    }

    @Test
    public void markCommand_invalidTaskNumber_exceptionThrown() {
        Command command = new MarkCommand(-1);
        MorganaException exception = assertThrows(MorganaException.class, () ->
                command.execute(tasks, storage));
        assertEquals(MESSAGE_INVALID_TASK_NUMBER, exception.getMessage());
    }
}
