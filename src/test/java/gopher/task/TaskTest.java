package gopher.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import gopher.exception.EmptyTaskDescriptionException;
import gopher.exception.InvalidDurationException;
import gopher.exception.InvalidTokenException;
import gopher.exception.MissingTokenException;
import gopher.exception.UnknownCommandException;

public class TaskTest {
    /**
     * A simple create task method that handles the exceptions for tester.
     * This method assumes that the task creation command for test cases are valid
     * and would not result in any exception, hence allowing tester to focus on
     * testing the desired logic without wasting time on handling exception.
     * In case an exception is thrown, the test case would still fail because the
     * return value is null.
     *
     * @param command command to be tested
     * @return task created by the command
     */
    private Task createTask(String command) {
        try {
            return Task.of(command);
        } catch (UnknownCommandException
            | EmptyTaskDescriptionException
            | MissingTokenException | InvalidTokenException
                | InvalidDurationException e) {
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

    @Test
    public void update_validCommand_updateSuccess()
            throws InvalidTokenException, InvalidDurationException {
        Task todo = createTask("todo Event 1");
        todo.update(new String[]{"update", "1", "Hello"});
        assertEquals("[T][ ] Hello",
                todo.toString());

        Task deadline = createTask("deadline Event 2 /by 2024-08-30");
        deadline.update(new String[]{"update", "2", "Goodbye World", "/by", "2025-01-01"});
        assertEquals("[D][ ] Goodbye World (by Jan 01 2025 00:00)",
                deadline.toString());

        Task event = createTask("event Event 3 /from 2024-08-30 /to 2024-09-02");
        event.update(new String[]{"update", "3", "Orbital Splashdown", "/from", "2024-01-01",
                                  "/to", "2025-01-01"});
        assertEquals("[E][ ] Orbital Splashdown (from Jan 01 2024 00:00 to Jan 01 2025 00:00)",
                event.toString());
    }

    @Test
    public void of_unknownCommand_exceptionThrown() {
        assertThrows(UnknownCommandException.class, () -> {
            Task.of("hello");
        });
    }

    @Test
    public void of_emptyDescription_exceptionThrown() {
        assertThrows(EmptyTaskDescriptionException.class, () -> {
            Task.of("todo");
        });
    }

    @Test
    public void of_missingByTokenWhenCreatingDeadline_exceptionThrown() {
        assertThrows(MissingTokenException.class, () -> {
            Task.of("deadline Goodbye World 2024-09-11");
        });
    }

    @Test
    public void of_invalidDurationForEventTask_exceptionThrown() {
        assertThrows(InvalidDurationException.class, () -> {
            Task.of("event hello /from 2024-01-01 /to 2023-01-01");
        });
    }

    @Test
    public void of_fromTokenUsedToCreateDeadline_exceptionThrown() {
        assertThrows(InvalidTokenException.class, () -> {
            Task.of("deadline hello /from 2024-01-01");
        });
    }

    @Test
    public void update_byTokenUsedToUpdateEvent_exceptionThrown() {
        Task event = createTask("event hello /from 2020-01-01 /to 2021-01-01");
        assertThrows(InvalidTokenException.class, () -> {
            String[] tokens = new String[]{"update", "4", "/by", "2024-09-11"};
            event.update(tokens);
        });
    }

    @Test
    public void update_invalidDuration_exceptionThrown() {
        Task event = createTask("event hello /from 2020-01-01 /to 2021-01-01");
        assertThrows(InvalidDurationException.class, () -> {
            String[] tokens = new String[]{"update", "4", "/from", "2024-01-01"};
            event.update(tokens);
        });
    }
}
