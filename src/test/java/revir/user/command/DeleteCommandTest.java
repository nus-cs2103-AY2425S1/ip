package revir.user.command;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;

import revir.system.Storage;
import revir.tasks.Deadline;
import revir.tasks.Event;
import revir.tasks.TaskList;
import revir.tasks.Todo;
import revir.user.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeleteCommandTest {
    Storage storageMock = mock(Storage.class);
    Ui uiMock = mock(Ui.class);

    LocalDateTime date = LocalDateTime.parse("1/1/2000 0000", DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    Todo todo = new Todo("todo");
    Deadline deadline = new Deadline("deadline", date);
    Event event = new Event("event", date, date);

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