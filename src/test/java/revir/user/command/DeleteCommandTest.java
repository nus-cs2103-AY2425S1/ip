package revir.user.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import revir.system.Storage;
import revir.tasks.TaskList;
import revir.tasks.Todo;
import revir.user.ui.Ui;


public class DeleteCommandTest {
    private Storage storageMock = mock(Storage.class);
    private Ui uiMock = mock(Ui.class);

    private Todo todo = new Todo("todo");

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList(storageMock, uiMock);
        try {
            taskList.add(todo);
        } catch (IOException e) {
            assert(false);
        }
        Delete command = new Delete(1);
        try {
            command.execute(uiMock, taskList);
        } catch (IOException e) {
            assert(false);
        }
        assertEquals(taskList.find("todo"), "");
    }

    @Test
    public void testDeleteInvalidTaskIndex() {
        TaskList taskList = new TaskList(storageMock, uiMock);
        try {
            taskList.add(todo);
        } catch (IOException e) {
            assert(false);
        }
        Delete command = new Delete(0);
        try {
            command.execute(uiMock, taskList);
        } catch (IOException e) {
            assert(false);
        } catch (IndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), "Invalid task index. Expected index between 1 and 1");
        }
    }

    @Test
    public void testDeleteEmptyList() {
        TaskList taskList = new TaskList(storageMock, uiMock);
        Delete command = new Delete(1);
        try {
            command.execute(uiMock, taskList);
        } catch (IOException e) {
            assert(false);
        } catch (IndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), "No tasks to delete.");
        }
    }
}
