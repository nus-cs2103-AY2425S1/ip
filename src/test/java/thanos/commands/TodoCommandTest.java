package thanos.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import thanos.exceptions.InvalidCommandException;
import thanos.stubs.StorageStub;
import thanos.tasks.TaskList;
import thanos.tasks.Todo;

public class TodoCommandTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new StorageStub());
    }

    @Test
    public void execute_emptyArgument_throwsInvalidCommandException() {
        TodoCommand command = new TodoCommand("");

        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("No task description provided. Please use the correct format: 'todo [task]'",
                exception.getMessage());
    }

    @Test
    public void execute_validTask_addTaskSuccess() throws InvalidCommandException {
        TodoCommand command = new TodoCommand("read book");
        command.execute(taskList);

        assertEquals(1, taskList.size(), "TaskList should contain 1 task after adding a todo");
        assertInstanceOf(Todo.class, taskList.getTaskList().get(0), "Expected a Todo class");
    }
}
