package juno.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import juno.manager.FileManager;
import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.task.Task;

public class DeleteCommandTest {
    private TaskManager mockTaskManager;
    private FileManager mockFileManager;
    private ArrayList<Task> mockTaskList;

    @BeforeEach
    void startTestEnv() {

        this.mockTaskManager = mock(TaskManager.class);
        this.mockFileManager = mock(FileManager.class);

        this.mockTaskList = new ArrayList<>();
        Task mockTask1 = mock(Task.class);
        Task mockTask2 = mock(Task.class);
        this.mockTaskList.add(mockTask1);
        this.mockTaskList.add(mockTask2);

        when(mockTaskManager.getTasksArray()).thenReturn(mockTaskList);
    }

    @Test
    public void runCommand_validDeleteCommand_success() throws TaskManagerException {
        DeleteCommand deleteCommand = new DeleteCommand("delete 1", this.mockTaskManager, this.mockFileManager);
        deleteCommand.runCommand();

        assertEquals(1, this.mockTaskList.size());
    }

    @Test
    public void runCommand_invalidDeleteCommand_exceptionThrown() {
        DeleteCommand deleteCommand = new DeleteCommand("delete 3", this.mockTaskManager, this.mockFileManager);
        TaskManagerException exception = assertThrows(TaskManagerException.class, deleteCommand::runCommand);

        assertEquals(TaskManagerException.ErrorType.TASK_OUT_OF_RANGE, exception.getErrorType());
        assertEquals("\uD83D\uDEAB Oops! That task number is out of range. "
                        + "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)",
                exception.getMessage());
    }

    @Test
    public void runCommand_wrongTaskNumber_exceptionThrown() {
        DeleteCommand deleteCommand = new DeleteCommand("delete asd", mockTaskManager, mockFileManager);
        TaskManagerException exception = assertThrows(TaskManagerException.class, deleteCommand::runCommand);

        // Verify that the exception message matches
        assertEquals(TaskManagerException.ErrorType.INVALID_DELETE_TASK_NUMBER, exception.getErrorType());
        assertEquals("\uD83D\uDE15 Hmm, something went wrong. "
                + "Please enter a task number after mark/unmark/delete command. "
                + "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)", exception.getMessage());
    }
}
