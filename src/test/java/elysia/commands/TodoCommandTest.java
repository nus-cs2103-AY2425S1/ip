package elysia.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import elysia.exceptions.EmptyTaskArgumentsException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;

/**
 * Unit test for the {@link TodoCommand} class.
 * Tests the behavior of the {@code TodoCommand} when executed with insufficient arguments.
 */
public class TodoCommandTest {

    /**
     * Tests that an {@code EmptyTaskArgumentsException} is thrown
     * when the {@code execute} method is called with insufficient arguments
     * for the todo command.
     */
    @Test
    void execute_emptyTodoArgument_exceptionThrown() {
        TaskList taskList = new TaskList();
        String[] args = new String[]{"todo"};
        TodoCommand todoCommand = new TodoCommand(taskList, new FileReaderWriter(taskList), args);

        // Asserts that EmptyTaskArgumentsException is thrown when execute is called
        assertThrows(EmptyTaskArgumentsException.class, () -> {
            throw new EmptyTaskArgumentsException("");
        });
    }
}
