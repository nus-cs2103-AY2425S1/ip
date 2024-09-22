package cstwooneohthree.glados.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import cstwooneohthree.glados.enums.TaskType;
import cstwooneohthree.glados.exceptions.GladosException;

public class ParserTest {

    @Test
    public void testParseTodo() throws GladosException {
        ParsedInfo parsedTodo = Parser.parseTask(TaskType.TODO, "test todo");
        assertEquals("test todo", parsedTodo.getDescription());
        assertEquals(0, parsedTodo.getDates().length); // No dates for a TODO
    }

    @Test
    public void testParseTodoWithTrailingSpace() throws GladosException {
        ParsedInfo parsedTodo = Parser.parseTask(TaskType.TODO, "test todo    ");
        assertEquals("test todo", parsedTodo.getDescription());
        assertEquals(0, parsedTodo.getDates().length);
    }

    @Test
    public void testParseDeadline() throws GladosException {
        ParsedInfo parsedDeadline = Parser.parseTask(TaskType.DEADLINE, "test deadline /by 2025-08-19");
        assertEquals("test deadline", parsedDeadline.getDescription());
        assertEquals(LocalDate.of(2025, 8, 19), parsedDeadline.getDates()[0]);
    }

    @Test
    public void testParseDeadlineWithNoDescription() throws GladosException {
        try {
            Parser.parseTask(TaskType.DEADLINE, "/by 2025-08-19");
            fail();
        } catch (Exception e) {
            assertEquals("Description for a task cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void testParseDeadlineWithNoDeadline() throws GladosException {
        try {
            Parser.parseTask(TaskType.DEADLINE, "test deadline /by");
            fail();
        } catch (Exception e) {
            assertEquals("Date after '/by' not specified or invalid", e.getMessage());
        }
    }

    @Test
    public void testParseDeadlineWithIncorrectDateFormat() throws GladosException {
        try {
            Parser.parseTask(TaskType.DEADLINE, "test deadline /by today");
            fail();
        } catch (Exception e) {
            assertEquals("Date is incorrectly formatted (should be yyyy-MM-dd)", e.getMessage());
        }
    }

    @Test
    public void testParseEvent() throws GladosException {
        ParsedInfo parsedEvent = Parser.parseTask(TaskType.EVENT, "test event /from 2025-08-19 /to 2025-08-19");
        assertEquals("test event", parsedEvent.getDescription());
        assertEquals(LocalDate.of(2025, 8, 19), parsedEvent.getDates()[0]);
        assertEquals(LocalDate.of(2025, 8, 19), parsedEvent.getDates()[1]);
    }

    @Test
    public void testParseEventWithNoDescription() throws GladosException {
        try {
            Parser.parseTask(TaskType.EVENT, "/from 2025-08-19 /to 2025-08-19");
            fail();
        } catch (Exception e) {
            assertEquals("Description for a task cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void testParseEventWithNoFromDate() throws GladosException {
        try {
            Parser.parseTask(TaskType.EVENT, "test event /to 2025-08-19");
            fail();
        } catch (Exception e) {
            assertEquals("Date range between '/from' and '/to' not specified or invalid", e.getMessage());
        }
    }

    @Test
    public void testParseEventWithNoDateRange() throws GladosException {
        try {
            Parser.parseTask(TaskType.EVENT, "test event");
            fail();
        } catch (Exception e) {
            assertEquals("Date range between '/from' and '/to' not specified or invalid", e.getMessage());
        }
    }

    @Test
    public void testParseEventWithIncorrectDateFormat() throws GladosException {
        try {
            Parser.parseTask(TaskType.EVENT, "test event /from today /to tomorrow");
            fail();
        } catch (Exception e) {
            assertEquals("Date is incorrectly formatted (should be yyyy-MM-dd)", e.getMessage());
        }
    }
}
