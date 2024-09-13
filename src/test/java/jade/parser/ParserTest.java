package jade.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jade.exception.JadeException;
import jade.storage.Storage;
import jade.task.Deadline;
import jade.task.Event;
import jade.task.Task;
import jade.task.TaskManager;
import jade.task.Todo;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    void setUp() {
        Storage storage = new Storage("data/test_tasks.txt");
        TaskManager taskManager = new TaskManager(storage);
        parser = new Parser(taskManager);
    }

    @Test
    void testParseTaskCommandTodo() throws JadeException {
        Task task = parser.parseTaskCommand("todo Read a book");

        assertInstanceOf(Todo.class, task);
        assertEquals("Read a book", task.getDescription());
    }

    @Test
    void testParseTaskCommandDeadline() throws JadeException {
        Task task = parser.parseTaskCommand("deadline Submit report /by 2024-12-01 1200");

        assertInstanceOf(Deadline.class, task);
        assertEquals("Submit report", task.getDescription());
    }

    @Test
    void testParseTaskCommandEvent() throws JadeException {
        Task task = parser.parseTaskCommand("event Project meeting /from 2024-12-01 1200 /to 2024-12-01 1400");

        assertInstanceOf(Event.class, task);
        assertEquals("Project meeting", task.getDescription());
    }

    @Test
    void testParseInvalidDeadlineFormat() {
        Exception exception = assertThrows(JadeException.class, () -> {
            parser.parseTaskCommand("deadline Submit report /by wrong-format");
        });

        assertEquals("Please use yyyy-MM-dd HHmm format for time.\n"
                + " ".repeat(7) + "eg. 2024-12-25 2130", exception.getMessage());
    }

    @Test
    void testParseInvalidEventFormat() {
        Exception exception = assertThrows(JadeException.class, () -> {
            parser.parseTaskCommand("event Meeting /from wrong-format /to wrong-format");
        });

        assertEquals("Please use yyyy-MM-dd HHmm format for time.\n"
                + " ".repeat(7) + "eg. 2024-12-25 2130", exception.getMessage());
    }
}
