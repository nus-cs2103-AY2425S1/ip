package revir.user.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import revir.system.Storage;
import revir.tasks.Deadline;
import revir.tasks.Event;
import revir.tasks.TaskList;
import revir.tasks.Todo;
import revir.user.ui.Ui;

public class CreateCommandTest {
    private Storage storageMock = mock(Storage.class);
    private Ui uiMock = mock(Ui.class);

    private LocalDateTime date = LocalDateTime.parse("1/1/2000 0000", DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    private Todo todo = new Todo("todo");
    private Deadline deadline = new Deadline("deadline", date);
    private Event event = new Event("event", date, date);

    @Test
    public void testTodoTask() {
        TaskList taskList = new TaskList(storageMock, uiMock);
        Create command = new Create("todo");
        assertEquals(taskList.find("todo"), "");
        try {
            command.execute(uiMock, taskList);
        } catch (IOException e) {
            assert (false);
        }
        assertEquals(taskList.find("todo"), todo.toString());
    }

    @Test
    public void testDeadlineTask() {
        TaskList taskList = new TaskList(storageMock, uiMock);
        Create command = new Create("deadline", date);
        assertEquals(taskList.find("deadline"), "");
        try {
            command.execute(uiMock, taskList);
        } catch (IOException e) {
            assert (false);
        }
        assertEquals(taskList.find("deadline"), deadline.toString());
    }

    @Test
    public void testEventTask() {
        TaskList taskList = new TaskList(storageMock, uiMock);
        Create command = new Create("event", date, date);
        assertEquals(taskList.find("event"), "");
        try {
            command.execute(uiMock, taskList);
        } catch (IOException e) {
            assert (false);
        }
        assertEquals(taskList.find("event"), event.toString());
    }
}
