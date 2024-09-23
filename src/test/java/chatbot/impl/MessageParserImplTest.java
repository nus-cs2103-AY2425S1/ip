package chatbot.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kat.chatbot.TaskStorage;
import kat.chatbot.impl.MessageParserImpl;
import kat.exceptions.InvalidMessageException;
import kat.tasks.Task;
import kat.tasks.impl.DeadlineTask;
import kat.tasks.impl.EventTask;
import kat.tasks.impl.TodoTask;

public class MessageParserImplTest {

    private MessageParserImpl messageParser;
    private TaskStorage mockStorage;

    @BeforeEach
    void setUp() {
        mockStorage = mock(TaskStorage.class);
        messageParser = new MessageParserImpl(mockStorage);
    }

    @Test
    void testHandleMessageEmptyInput() {
        assertThrows(InvalidMessageException.class, () -> messageParser.handleMessage(""));
    }

    @Test
    void testHandleMessageInvalidCommand() {
        assertThrows(InvalidMessageException.class, () -> messageParser.handleMessage("invalidCommand"));
    }

    @Test
    void testHandleMessageList() throws InvalidMessageException {
        when(mockStorage.toString()).thenReturn("1. [X] Task 1\n2. [ ] Task 2");
        String result = messageParser.handleMessage("list");
        assertEquals("Here are your tasks:\n1. [X] Task 1\n2. [ ] Task 2", result);
    }

    @Test
    void testHandleMessageMark() throws InvalidMessageException {
        Task mockTask = mock(Task.class);
        when(mockTask.toString()).thenReturn("[X] Task 1");
        when(mockStorage.getTask(0)).thenReturn(mockTask);

        String result = messageParser.handleMessage("mark 1");
        assertEquals("Nice! Marked as done:\n[X] Task 1", result);
        verify(mockStorage).setTaskAsDone(0);
    }

    @Test
    void testHandleMessageMarkOutOfRange() {
        doThrow(IndexOutOfBoundsException.class).when(mockStorage).setTaskAsDone(anyInt());
        assertThrows(InvalidMessageException.class, () -> messageParser.handleMessage("mark 100"));
    }

    @Test
    void testHandleMessageUnmark() throws InvalidMessageException {
        Task mockTask = mock(Task.class);
        when(mockTask.toString()).thenReturn("[ ] Task 1");
        when(mockStorage.getTask(0)).thenReturn(mockTask);

        String result = messageParser.handleMessage("unmark 1");
        assertEquals("Ah! Unmarked as not done:\n[ ] Task 1", result);
        verify(mockStorage).setTaskAsNotDone(0);
    }

    @Test
    void testHandleMessageUnmarkOutOfRange() {
        doThrow(IndexOutOfBoundsException.class).when(mockStorage).setTaskAsNotDone(anyInt());
        assertThrows(InvalidMessageException.class, () -> messageParser.handleMessage("unmark 100"));
    }

    @Test
    void testHandleMessageTodo() throws InvalidMessageException {
        when(mockStorage.getSize()).thenReturn(1);
        String result = messageParser.handleMessage("todo Buy groceries");
        assertEquals("Got it. Task saved:\n[T][ ] Buy groceries\n1 tasks in the list.", result);
        verify(mockStorage).addTask(any(TodoTask.class));
    }

    @Test
    void testHandleMessageDeadline() throws InvalidMessageException {
        when(mockStorage.getSize()).thenReturn(1);
        String result = messageParser.handleMessage("deadline Submit report /by 2024-12-02");
        assertEquals("Got it. Task saved:\n[D][ ] Submit report (by: 02 Dec 2024)\n1 tasks in the list.", result);
        verify(mockStorage).addTask(any(DeadlineTask.class));
    }

    @Test
    void testHandleMessageDeadlineMissingBy() {
        assertThrows(InvalidMessageException.class, () -> messageParser.handleMessage("deadline Submit report"));
    }

    @Test
    void testHandleMessageDeadlineEmptyBy() {
        assertThrows(InvalidMessageException.class, () -> messageParser.handleMessage("deadline Submit report /by"));
    }

    @Test
    void testHandleMessageDeadlineWithInvalidBy() {
        assertThrows(InvalidMessageException.class,
                () -> messageParser.handleMessage("deadline Submit report /by 2024-12"));
    }

    @Test
    void testHandleMessageEvent() throws InvalidMessageException {
        when(mockStorage.getSize()).thenReturn(1);
        String result = messageParser.handleMessage("event Team meeting /from 2024-12-02 /to 2024-12-31");
        assertEquals(
                "Got it. Task saved:\n[E][ ] Team meeting (from: 02 Dec 2024 to: 31 Dec 2024)\n1 tasks in the list.",
                result);
        verify(mockStorage).addTask(any(EventTask.class));
    }

    @Test
    void testHandleMessageEventMissingFromTo() {
        assertThrows(InvalidMessageException.class, () -> messageParser.handleMessage("event Team meeting"));
    }

    @Test
    void testHandleMessageEventMissingTo() {
        assertThrows(InvalidMessageException.class,
                () -> messageParser.handleMessage("event Team meeting /from 2024-12-02"));
    }

    @Test
    void testHandleMessageEventMissingFrom() {
        assertThrows(InvalidMessageException.class,
                () -> messageParser.handleMessage("event Team meeting /to 2024-12-02"));
    }

    @Test
    void testHandleMessageEventEmptyTo() {
        assertThrows(InvalidMessageException.class,
                () -> messageParser.handleMessage("event Team meeting /from 2024-12-02 /to"));
    }

    @Test
    void testHandleMessageEventEmptyFrom() {
        assertThrows(InvalidMessageException.class,
                () -> messageParser.handleMessage("event Team meeting /from /to 2024-12-02"));
    }

    @Test
    void testHandleMessageEventWithInvalidFromTo() {
        assertThrows(InvalidMessageException.class,
                () -> messageParser.handleMessage("event Team meeting /from 2023-05-10 /to 2023-05"));
    }

    @Test
    void testHandleMessageDelete() throws InvalidMessageException {
        Task mockTask = mock(Task.class);
        when(mockTask.toString()).thenReturn("Deleted task");
        when(mockStorage.getTask(0)).thenReturn(mockTask);
        when(mockStorage.getSize()).thenReturn(0);

        String result = messageParser.handleMessage("delete 1");
        assertEquals("Sure. Task deleted:\nDeleted task\n0 tasks in the list.", result);
        verify(mockStorage).deleteTask(0);
    }

    @Test
    void testHandleMessageDeleteOutOfRange() {
        doThrow(IndexOutOfBoundsException.class).when(mockStorage).getTask(anyInt());
        assertThrows(InvalidMessageException.class, () -> messageParser.handleMessage("delete 100"));
    }

    @Test
    void testHandleMessageFindWithMatches() throws InvalidMessageException {
        Task mockTask1 = mock(Task.class);
        Task mockTask2 = mock(Task.class);
        when(mockTask1.toString()).thenReturn("[T][ ] read BOOK");
        when(mockTask2.toString()).thenReturn("[D][X] return book (by: 02 Dec 2024)");
        when(mockStorage.findTasks("book")).thenReturn(Arrays.asList(mockTask1, mockTask2));

        String result = messageParser.handleMessage("find book");
        assertEquals(
                "Here are the matching tasks:\n1. [T][ ] read BOOK\n2. [D][X] return book (by: 02 Dec 2024)",
                result);
    }

    @Test
    void testHandleMessageFindNoMatches() throws InvalidMessageException {
        when(mockStorage.findTasks("nonexistent")).thenReturn(Collections.emptyList());

        String result = messageParser.handleMessage("find nonexistent");
        assertEquals("No matching tasks found.", result);
    }

    @Test
    void testHandleMessageFindEmptyKeyword() {
        assertThrows(InvalidMessageException.class, () -> messageParser.handleMessage("find"));
    }

}
