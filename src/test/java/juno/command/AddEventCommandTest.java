package juno.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import juno.manager.FileManager;
import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.task.Event;
import juno.task.Task;

public class AddEventCommandTest {

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
    public void runCommand_validAddEventCommand_success() throws TaskManagerException {

        AddEventCommand addEventCommand = new AddEventCommand(
                "add event Finish homework /2024 10 11 11.59PM / 2024 10 12 11.59PM",
                this.mockTaskManager, this.mockFileManager);

        addEventCommand.runCommand();

        assertEquals(1, this.mockTaskList.size());
        Task t = this.mockTaskList.get(0);
        assertInstanceOf(Event.class, t);
        assertEquals("Finish homework", t.getDescription());

        verify(this.mockFileManager).writeTasksToFile(this.mockTaskList);
    }
}
