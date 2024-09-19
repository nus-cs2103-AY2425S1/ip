package juno.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.task.Deadline;
import juno.task.Task;
import juno.task.Todo;

public class ListCommandTest {
    private TaskManager mockTaskManager;
    private ListCommand listCommand;

    @BeforeEach
    public void startTestEnv() {
        this.mockTaskManager = Mockito.mock(TaskManager.class);
        when(this.mockTaskManager.getTasksArray()).thenReturn(new ArrayList<>());
        this.listCommand = new ListCommand(this.mockTaskManager);
    }

    @Test
    public void runCommand_emptyList_exceptionThrown() {

        TaskManagerException exception = assertThrows(TaskManagerException.class, () -> {
            this.listCommand.runCommand();
        });

        assertEquals(TaskManagerException.ErrorType.EMPTY_LIST, exception.getErrorType());
        assertEquals("\uD83C\uDF31 No tasks added yet! Why not plant the first seed? \uD83C\uDF31",
                exception.getMessage());
    }

    @Test
    public void runCommand_nonEmptyList_success() throws TaskManagerException {

        // Mock a non-empty task list
        ArrayList<Task> taskList = new ArrayList<>();
        Task mockTask1 = Mockito.mock(Todo.class);
        Task mockTask2 = Mockito.mock(Deadline.class);
        when(mockTask1.toString()).thenReturn("[todo] Mock Task 1");
        when(mockTask2.toString()).thenReturn("[deadline] Mock Task 2");

        taskList.add(mockTask1);
        taskList.add(mockTask2);

        // change the current mockTaskManager
        this.mockTaskManager = Mockito.mock(TaskManager.class);
        when(this.mockTaskManager.getTasksArray()).thenReturn(taskList);
        this.listCommand = new ListCommand(this.mockTaskManager);

        String actualOutput = this.listCommand.runCommand();

        // Expected output
        String expectedOutput = """
                Here's a rundown of all your tasks! \uD83D\uDE0A
                1. [todo] Mock Task 1
                2. [deadline] Mock Task 2
                \uD83C\uDFAF You have 2 tasks in the list. Keep going!""";

        verify(this.mockTaskManager, times(1)).getTasksArray();
        assertEquals(expectedOutput, actualOutput);

    }
}
