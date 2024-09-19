package snowy.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import snowy.data.SnowyException;
import snowy.tasklist.TaskList;
import snowy.storage.Storage;
import snowy.tasklist.Task;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoCommandTest {

    private TaskList taskList;

    @TempDir
    public static Path testFolder;

    @BeforeEach
    public void setTaskList() throws SnowyException {
        taskList = new TaskList(new Storage(testFolder.toString(), "tasks.txt"));
    }

    @Test
    public void todoCommand_validDescription_taskAdded() throws SnowyException {
        String description = "Finish homework";
        TodoCommand command = new TodoCommand(description);
        command.setData(taskList);
        CommandResult result = command.execute();

        assertEquals(1, taskList.getSize());

        Task addedTask = taskList.getTask(0);
        assertEquals(description, addedTask.getDescription());

    }


}
