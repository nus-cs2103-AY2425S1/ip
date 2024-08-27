package utils;

import org.junit.jupiter.api.Test;

import enums.TaskType;
import exceptions.GladosException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void testParseTodo() throws GladosException {
        String[] parsedTodo = Parser.parseTask(TaskType.TODO, "test todo");
        assertArrayEquals(new String[]{"test todo"}, parsedTodo);
    }

    @Test
    public void testParseTodoWithTrailingSpace() throws GladosException {
        String[] parsedTodo = Parser.parseTask(TaskType.TODO, "test todo    ");
        assertArrayEquals(new String[]{"test todo"}, parsedTodo);
    }

    @Test
    public void testParseDeadline() throws GladosException {
        String[] parsedTodo = Parser.parseTask(TaskType.DEADLINE, "test deadline /by today");
        assertArrayEquals(new String[]{"test deadline", "today"}, parsedTodo);
    }
    
    @Test
    public void testParseDeadlineWithNoDescription() throws GladosException {
        try {
            String[] parsedTodo = Parser.parseTask(TaskType.DEADLINE, "/by today");
            fail();
        } catch (Exception e) {
            assertEquals("Description for a task cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void testParseDeadlineWithNoDeadline() throws GladosException {
        try {
            String[] parsedTodo = Parser.parseTask(TaskType.DEADLINE, "test deadline /by");
            fail();
        } catch (Exception e) {
            assertEquals("Date after '/by' not specified or invalid", e.getMessage());
        }
    }

    @Test
    public void testParseEvent() throws GladosException {
        String[] parsedTodo = Parser.parseTask(TaskType.EVENT, "test event /from today /to tomorrow");
        assertArrayEquals(new String[]{"test event", "today", "tomorrow"}, parsedTodo);
    }

    @Test
    public void testParseEventWithNoDescription() throws GladosException {
        try {
            String[] parsedTodo = Parser.parseTask(TaskType.EVENT, "/from today /to tomorrow");
            fail();
        } catch (Exception e) {
            assertEquals("Description for a task cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void testParseEventWithNoFromDate() throws GladosException {
        try {
            String[] parsedTodo = Parser.parseTask(TaskType.EVENT, "test event /to tomorrow");
            fail();
        } catch (Exception e) {
            assertEquals("Date range between '/from' and '/to' not specified or invalid", e.getMessage());
        }
    }

    @Test
    public void testParseEventWithNoDateRange() throws GladosException {
        try {
            String[] parsedTodo = Parser.parseTask(TaskType.EVENT, "test event");
            fail();
        } catch (Exception e) {
            assertEquals("Date range between '/from' and '/to' not specified or invalid", e.getMessage());
        }
    }
    
}
