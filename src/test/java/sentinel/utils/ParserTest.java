package sentinel.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sentinel.exception.DeadlineException;
import sentinel.exception.EventException;
import sentinel.exception.SentinelException;
import sentinel.task.Task;
import sentinel.task.ToDo;
import sentinel.ui.Ui;
import sentinel.Sentinel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class ParserTest {
    private Ui uiMock;

    @BeforeEach
    public void setUp() {
        uiMock = mock(Ui.class);
    }

    @Test
    public void testParseForCommand() {
        Sentinel.CommandType commandType = Parser.parseForCommand("todo Task description");
        assertEquals(Sentinel.CommandType.todo, commandType);
    }

    @Test
    public void testParseTaskValidToDo() throws SentinelException {
        String input = "todo Test ToDo";
        Task expectedTask = new ToDo("Test ToDo");
        Task task = Parser.parseTask(Sentinel.CommandType.todo, input, uiMock);
        assertEquals(expectedTask.getDescription(), task.getDescription());
    }
    @Test
    public void testParseTaskNameValidToDo() throws SentinelException {
        String input = "todo Test Task";
        Sentinel.CommandType commandType = Sentinel.CommandType.todo;
        String taskName = Parser.parseTaskName(commandType, input, uiMock);
        assertEquals("Test Task", taskName);
    }

    @Test
    public void testParseTaskNameInvalidDeadline() throws SentinelException {
        String input = "deadline Test Deadline";
        Sentinel.CommandType commandType = Sentinel.CommandType.deadline;
        Exception exception = assertThrows(DeadlineException.class, () -> Parser.parseTaskName(commandType, input, uiMock));
        assertEquals("sentinel.task.Deadline sentinel.command.Command", exception.getMessage());
    }

    @Test
    public void testParseTaskNameInvalidEvent() throws SentinelException {
        String input = "event Test Event";
        Sentinel.CommandType commandType = Sentinel.CommandType.event;
        Exception exception = assertThrows(EventException.class, () -> Parser.parseTaskName(commandType, input, uiMock));
        assertEquals("sentinel.task.Event sentinel.command.Command", exception.getMessage());
    }

    @Test
    public void testParseIndex() {
        int index = Parser.parseIndex("delete 5");
        assertEquals(5, index);
    }

    @Test
    public void testParseIndexInvalid() {
        int index = Parser.parseIndex("delete invalid");
        assertEquals(-1, index);
    }
}
