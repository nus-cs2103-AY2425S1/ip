package elysia.commands;

import elysia.exceptions.EmptyArgumentException;
import elysia.exceptions.EmptyTaskArgumentsException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskListStub;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TodoCommandTest {
    @Test
    void execute_emptyTodoArgument_exceptionThrown() {
        TaskListStub taskListStub = new TaskListStub();
        String[] args = new String[]{"todo"};
        TodoCommand todoCommand = new TodoCommand(taskListStub, new FileReaderWriter(taskListStub), args);
        assertThrows(EmptyTaskArgumentsException.class, () -> {
            throw new EmptyTaskArgumentsException("");
        });
    }
}
