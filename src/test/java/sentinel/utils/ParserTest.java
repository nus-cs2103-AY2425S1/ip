package sentinel.utils;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sentinel.Sentinel;
import sentinel.exception.DeadlineException;
import sentinel.exception.EventException;
import sentinel.exception.SentinelException;
import sentinel.task.Task;
import sentinel.task.ToDo;
import sentinel.ui.Ui;



public class ParserTest {
    private Ui uiMock;

    @BeforeEach
    public void setUp() {
        uiMock = mock(Ui.class);
    }

    @Test
    public void testParseForCommand() throws SentinelException {
        Sentinel.CommandType commandType = Parser.parseForCommand("todo Task description");
        assertEquals(Sentinel.CommandType.todo, commandType);
    }

    @Test
    public void testParseTaskValidToDo() throws SentinelException, IOException {
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
    public void testParseTaskNameInvalidDeadline() {
        String input = "deadline Test Deadline";
        Sentinel.CommandType commandType = Sentinel.CommandType.deadline;
        Exception exception = assertThrows(DeadlineException.class, ()
                -> Parser.parseTaskName(commandType, input, uiMock));
        assertEquals("Please state the deadline using /by {YYYY}-{MM}-{DD}T{Hour}:{Minute} "
                + "(e.g., deadline return book /by 2024-09-19T13:00)", exception.getMessage());
    }

    @Test
    public void testParseTaskNameInvalidEvent() {
        String input = "event Test Event";
        Sentinel.CommandType commandType = Sentinel.CommandType.event;
        Exception exception = assertThrows(EventException.class, ()
                -> Parser.parseTaskName(commandType, input, uiMock));
        assertEquals("Please state the start and end date using /from {YYYY}-{MM}-{DD}T{Hour}:{Minute} and"
                + "/to {YYYY}-{MM}-{DD}T{Hour}:{Minute} respectively (e.g., event project meeting "
                + "/from 30 Aug 2024 2pm /to 30 Aug 2024 4pm)", exception.getMessage());
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
