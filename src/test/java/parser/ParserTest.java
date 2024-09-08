package jade.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import jade.parser.Parser;
import org.junit.jupiter.api.Test;

import jade.exception.JadeException;
import jade.task.Deadline;
import jade.task.Event;
import jade.task.Task;
import jade.task.Todo;

public class ParserTest {
    @Test
    void testParseTaskCommandTodo() throws JadeException {
        Parser parser = new Parser();
        Task task = parser.parseTaskCommand("todo Read a book");

        assertInstanceOf(Todo.class, task);
        assertEquals("Read a book", task.getDescription());
    }

    @Test
    void testParseTaskCommandDeadline() throws JadeException {
        Parser parser = new Parser();
        Task task = parser.parseTaskCommand("deadline Submit report /by 2024-12-01 1200");

        assertInstanceOf(Deadline.class, task);
        assertEquals("Submit report", task.getDescription());
    }

    @Test
    void testParseTaskCommandEvent() throws JadeException {
        Parser parser = new Parser();
        Task task = parser.parseTaskCommand("event Project meeting /from 2024-12-01 1200 /to 2024-12-01 1400");

        assertInstanceOf(Event.class, task);
        assertEquals("Project meeting", task.getDescription());
    }

    @Test
    void testParseInvalidDeadlineFormat() {
        Parser parser = new Parser();
        Exception exception = assertThrows(JadeException.class, () -> {
            parser.parseTaskCommand("deadline Submit report /by wrong-format");
        });

        assertEquals("Please use yyyy-MM-dd HHmm format for time.\n"
                + "       eg. 2024-12-25 2130", exception.getMessage());
    }

    @Test
    void testParseInvalidEventFormat() {
        Parser parser = new Parser();
        Exception exception = assertThrows(JadeException.class, () -> {
            parser.parseTaskCommand("event Meeting /from wrong-format /to wrong-format");
        });

        assertEquals("Please use yyyy-MM-dd HHmm format for time.\n"
                + "       eg. 2024-12-25 2130", exception.getMessage());
    }
}
