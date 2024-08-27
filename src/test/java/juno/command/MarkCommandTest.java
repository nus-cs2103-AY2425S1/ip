package juno.command;

import juno.manager.FileManager;
import juno.task.Deadline;
import juno.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.task.Task;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class MarkCommandTest {

    private TaskManager mockTaskManager;
    private FileManager mockFileManager;
    private ArrayList<Task> mockTasks;

    @BeforeEach
    public void startTestEnv() {
        this.mockTaskManager = Mockito.mock(TaskManager.class);
        this.mockFileManager = Mockito.mock(FileManager.class);
        this.mockTasks = new ArrayList<>();
        when(mockTaskManager.getTasksArray()).thenReturn(mockTasks);
    }

    @Test
    public void runCommand_markTask_success() throws TaskManagerException {
        Task mockTask = Mockito.mock(Todo.class);
        this.mockTasks.add(mockTask);
        when(mockTask.getIsDone()).thenReturn(false);
        when(mockTask.toString()).thenReturn("[todo] Mock Task");
        String userInput = "mark 1";

        MarkCommand markCommand = new MarkCommand(userInput, this.mockTaskManager, this.mockFileManager,
                true);
        markCommand.runCommand();

        verify(mockTask).markAsDone();
        verify(this.mockFileManager, times(1)).writeTasksToFile(mockTasks);
    }

//    @Test
//    public void runCommand_markTask_fail() throws TaskManagerException {
//        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStreamCaptor));
//        Task mockTask = Mockito.mock(Todo.class);
//        mockTask.markAsDone();
//        this.mockTasks.add(mockTask);
//        when(mockTask.getIsDone()).thenReturn(true);
//        when(mockTask.getDescription()).thenReturn("Mock Task");
//        when(mockTask.toString()).thenReturn("[todo] Mock Task");
//        String userInput = "mark 1";
//
//        MarkCommand markCommand = new MarkCommand(userInput, this.mockTaskManager, this.mockFileManager,
//                true);
//        markCommand.runCommand();
//
//        String expectedOutput = "You have completed the task \"Mock Task\" already!\n" + "[todo] Mock Task";
//        assertEquals(expectedOutput, outputStreamCaptor.toString());
//
//        verify(this.mockFileManager, times(1)).writeTasksToFile(mockTasks);
//    }

    @Test
    public void runCommand_unmarkTask_success() throws TaskManagerException {
        Task mockTask = Mockito.mock(Todo.class);
        this.mockTasks.add(mockTask);
        when(mockTask.getIsDone()).thenReturn(true);
        when(mockTask.toString()).thenReturn("[todo] Mock Task for unmark");
        String userInput = "unmark 1";

        MarkCommand unmarkCommand = new MarkCommand(userInput, mockTaskManager, mockFileManager, false);
        unmarkCommand.runCommand();

        verify(mockTask).markAsNotDone();
        verify(mockFileManager, times(1)).writeTasksToFile(mockTasks);
    }

//    @Test
//    public void runCommand_unmarkTask_fail() throws TaskManagerException {
//        Task mockTask = Mockito.mock(Todo.class);
//        this.mockTasks.add(mockTask);
//        when(mockTask.getIsDone()).thenReturn(false);
//        when(mockTask.toString()).thenReturn("[todo] Mock Task");
//        String userInput = "unmark 1";
//
//        MarkCommand markCommand = new MarkCommand(userInput, this.mockTaskManager, this.mockFileManager,
//                true);
//        markCommand.runCommand();
//
//        verify(mockTask).markAsNotDone();
//        verify(this.mockFileManager, times(1)).writeTasksToFile(mockTasks);
//    }

    @Test
    public void runCommand_markTaskOutOfRange_exceptionThrown() {
        String userInput = "mark 10";
        MarkCommand markCommand = new MarkCommand(userInput, this.mockTaskManager, this.mockFileManager,
                true);
        TaskManagerException exception = assertThrows(TaskManagerException.class, markCommand::runCommand);
        assertEquals(TaskManagerException.ErrorType.TASK_OUT_OF_RANGE, exception.getErrorType());
        assertEquals("\uD83D\uDEAB Oops! That task number is out of range. " +
                        "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)",
                exception.getMessage());
    }

    @Test
    public void runCommand_markWrongInput_exceptionThrown() {
        String userInput = "mark a";

        MarkCommand markCommand = new MarkCommand(userInput, this.mockTaskManager, this.mockFileManager,
                true);

        TaskManagerException exception = assertThrows(TaskManagerException.class, markCommand::runCommand);
        assertEquals(TaskManagerException.ErrorType.INVALID_MARK_TASK_NUMBER, exception.getErrorType());
        assertEquals("\uD83D\uDE15 Hmm, something went wrong. " +
                        "Please enter a task number after mark/unmark/delete command. " +
                        "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)",
                exception.getMessage());
    }


}
