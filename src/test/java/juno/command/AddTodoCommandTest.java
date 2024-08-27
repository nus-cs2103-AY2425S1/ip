package juno.command;

import juno.manager.FileManager;
import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.task.Task;
import juno.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

public class AddTodoCommandTest {

    private TaskManager mockTaskManager;
    private FileManager mockFileManager;
    private ArrayList<Task> mockTaskList;

    @BeforeEach
    void startTestEnv() {
        this.mockTaskManager = mock(TaskManager.class);
        this.mockFileManager = mock(FileManager.class);

        this.mockTaskList = new ArrayList<>();
        when(this.mockTaskManager.getTasksArray()).thenReturn(this.mockTaskList);
    }

    @Test
    public void runCommand_validAddTodoCommand_success() throws TaskManagerException {

        AddTodoCommand addTodoCommand = new AddTodoCommand(
                "add todo Finish homework", this.mockTaskManager, this.mockFileManager);

        addTodoCommand.runCommand();

        assertEquals(1, this.mockTaskList.size());
        Task t = this.mockTaskList.get(0);
        assertInstanceOf(Todo.class, t);
        assertEquals("Finish homework", t.getDescription());

        verify(this.mockFileManager).writeTasksToFile(this.mockTaskList);
    }
}
