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
        assertArrayEquals(parsedTodo, new String[]{"test todo"});
    }

    @Test
    public void testParseTodoWithTrailingSpace() throws GladosException {
        String[] parsedTodo = Parser.parseTask(TaskType.TODO, "test todo    ");
        assertArrayEquals(parsedTodo, new String[]{"test todo"});
    }

    @Test
    public void testParseDeadline() throws GladosException {
        String[] parsedTodo = Parser.parseTask(TaskType.DEADLINE, "test deadline /by today");
        assertArrayEquals(parsedTodo, new String[]{"test deadline", "today"});
    }
    
    @Test
    public void testParseDeadlineWithNoDescription() throws GladosException {
        try {
            String[] parsedTodo = Parser.parseTask(TaskType.DEADLINE, "/by today");
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Description for a task cannot be empty.");
        }
    }

    @Test
    public void testParseDeadlineWithNoDeadline() throws GladosException {
        try {
            String[] parsedTodo = Parser.parseTask(TaskType.DEADLINE, "test deadline /by");
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Date after '/by' not specified or invalid");
        }
    }

    @Test
    public void testParseEvent() throws GladosException {
        String[] parsedTodo = Parser.parseTask(TaskType.EVENT, "test event /from today /to tomorrow");
        assertArrayEquals(parsedTodo, new String[]{"test event", "today", "tomorrow"});
    }

    @Test
    public void testParseEventWithNoDescription() throws GladosException {
        try {
            String[] parsedTodo = Parser.parseTask(TaskType.EVENT, "/from today /to tomorrow");
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Description for a task cannot be empty.");
        }
    }

    @Test
    public void testParseEventWithNoFromDate() throws GladosException {
        try {
            String[] parsedTodo = Parser.parseTask(TaskType.EVENT, "test event /to tomorrow");
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Date range between '/from' and '/to' not specified or invalid");
        }
    }

    @Test
    public void testParseEventWithNoDateRange() throws GladosException {
        try {
            String[] parsedTodo = Parser.parseTask(TaskType.EVENT, "test event");
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Date range between '/from' and '/to' not specified or invalid");
        }
    }
    
}
