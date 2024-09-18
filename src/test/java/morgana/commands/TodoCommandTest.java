package morgana.commands;

import static morgana.commands.AddCommand.MESSAGE_SUCCESS;
import static morgana.commands.TodoCommand.MESSAGE_MISSING_DESCRIPTION;
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

public class TodoCommandTest {
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
    public void todoCommand_validInput_success() throws MorganaException {
        String description = "read book";

        Command command = new TodoCommand(description);
        Todo todo = new Todo(description);

        String expected = MESSAGE_SUCCESS.formatted(todo, 1, "");
        String actual = command.execute(tasks, storage);
        assertEquals(expected, actual);
    }

    @Test
    public void todoCommand_missingDescription_exceptionThrown() {
        Command command = new TodoCommand("");
        MorganaException exception = assertThrows(MorganaException.class, () ->
                command.execute(tasks, storage));
        assertEquals(MESSAGE_MISSING_DESCRIPTION, exception.getMessage());
    }
}
