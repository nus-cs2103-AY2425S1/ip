package gopher.task;

import gopher.exception.EmptyTaskDescriptionException;
import gopher.exception.MissingTokenException;
import gopher.exception.UnknownCommandException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    private Task createTask(String command) {
        try{
            return Task.of(command);
        } catch(UnknownCommandException
        | EmptyTaskDescriptionException
        | MissingTokenException e) {
            return null;
        }
    }

    @Test
    public void getSaveMessageTest() {
        Task todo = createTask("todo Event 1");
        assertEquals("T |   | Event 1",
                todo.getSaveMessage());

        Task deadline = createTask("deadline Event 2 /by 2024-08-30");
        assertEquals("D |   | Event 2 | 2024-08-30 00:00",
                deadline.getSaveMessage());

        Task event = createTask("event Event 3 /from 2024-08-30 /to 2024-09-02");
        assertEquals("E |   | Event 3 | 2024-08-30 00:00 | 2024-09-02 00:00",
                event.getSaveMessage());
    }

    @Test
    public void getStatusIconTest() {
        Task todo = createTask("todo Event 1");
        assertEquals(" ", todo.getStatusIcon());
        todo.markAsDone();
        assertEquals("X", todo.getStatusIcon());
        todo.markAsNotDone();
        assertEquals(" ", todo.getStatusIcon());
    }

    @Test
    public void markAsDoneTest() {
        Task todo = createTask("todo Event 1");
        todo.markAsDone();
        assertEquals("T | X | Event 1",
                todo.getSaveMessage());

        Task deadline = createTask("deadline Event 2 /by 2024-08-30");
        deadline.markAsDone();
        assertEquals("D | X | Event 2 | 2024-08-30 00:00",
                deadline.getSaveMessage());

        Task event = createTask("event Event 3 /from 2024-08-30 /to 2024-09-02");
        event.markAsDone();
        assertEquals("E | X | Event 3 | 2024-08-30 00:00 | 2024-09-02 00:00",
                event.getSaveMessage());
    }

    @Test
    public void markAsNotDoneTest() {
        Task todo = createTask("todo Event 1");
        todo.markAsDone();
        todo.markAsNotDone();
        assertEquals("T |   | Event 1",
                todo.getSaveMessage());

        Task deadline = createTask("deadline Event 2 /by 2024-08-30");
        deadline.markAsDone();
        deadline.markAsNotDone();
        assertEquals("D |   | Event 2 | 2024-08-30 00:00",
                deadline.getSaveMessage());

        Task event = createTask("event Event 3 /from 2024-08-30 /to 2024-09-02");
        event.markAsDone();
        event.markAsNotDone();
        assertEquals("E |   | Event 3 | 2024-08-30 00:00 | 2024-09-02 00:00",
                event.getSaveMessage());
    }

    @Test
    public void toStringTest() {
        Task todo = createTask("todo Event 1");
        assertEquals("[T][ ] Event 1",
                todo.toString());

        Task deadline = createTask("deadline Event 2 /by 2024-08-30");
        assertEquals("[D][ ] Event 2 (by Aug 30 2024 00:00)",
                deadline.toString());

        Task event = createTask("event Event 3 /from 2024-08-30 /to 2024-09-02");
        assertEquals("[E][ ] Event 3 (from Aug 30 2024 00:00 to Sep 02 2024 00:00)",
                event.toString());
    }
}
