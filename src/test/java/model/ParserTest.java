package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import action.Action;
import action.AddTaskAction;
import action.DeleteTaskAction;
import action.FindTasksAction;
import action.ListScheduleAction;
import action.ListTasksAction;
import action.MarkTaskAction;
import action.UnmarkTaskAction;
import exception.BotException;
import exception.InvalidCommandException;
import exception.InvalidCommandFormatException;
import exception.InvalidDateFormatException;
import exception.InvalidTaskIndexException;

public class ParserTest {
    @Test
    public void parseInput_emptyInput_exceptionThrown() {
        try {
            new Parser().parseInput("");
            fail();
        } catch (BotException e) {
            assertEquals(e.getClass(), InvalidCommandException.class);
        }
    }

    @Test
    public void parseInput_invalidCommand_exceptionThrown() {
        try {
            new Parser().parseInput("invalidcommand"); // this will never be added as a command
        } catch (BotException e) {
            assertEquals(e.getClass(), InvalidCommandException.class);
        }
    }

    @Test
    public void parseInput_invalidFormatTodo_exceptionThrown() {
        try {
            new Parser().parseInput("todo description1 description2");
        } catch (BotException e) {
            assertEquals(e.getClass(), InvalidCommandFormatException.class);
        }
    }

    @Test
    public void parseInput_validTodo_success() throws BotException {
        Action action = new Parser().parseInput("todo description");
        assertEquals(action.getClass(), AddTaskAction.class);
    }

    @Test
    public void parseInput_invalidFormatDeadline_exceptionThrown() {
        try {
            new Parser().parseInput("deadline description");
            fail();
        } catch (BotException e) {
            assertEquals(e.getClass(), InvalidCommandFormatException.class);
        }
    }

    @Test
    public void parseInput_invalidDateDeadline_exceptionThrown() {
        try {
            new Parser().parseInput("deadline description /by Today");
            fail();
        } catch (BotException e) {
            assertEquals(e.getClass(), InvalidDateFormatException.class);
        }
    }

    @Test
    public void parseInput_validDeadline_success() throws BotException {
        Action action = new Parser().parseInput("deadline description /by 2024-01-01");
        assertEquals(action.getClass(), AddTaskAction.class);
    }

    @Test
    public void parseInput_invalidFormatEvent_exceptionThrown() {
        try {
            new Parser().parseInput("event description /from Today /from Tomorrow /to Tomorrow");
            fail();
        } catch (BotException e) {
            assertEquals(e.getClass(), InvalidCommandFormatException.class);
        }
    }

    @Test
    public void parseInput_invalidDateEvent_exceptionThrown() {
        try {
            new Parser().parseInput("event description /from  2024-01-1 /to 2025-01-01");
            fail();
        } catch (BotException e) {
            assertEquals(e.getClass(), InvalidDateFormatException.class);
        }
    }

    @Test
    public void parseInput_validEvent_success() throws BotException {
        Action action = new Parser().parseInput("event description /from 2024-01-01 /to 2025-01-01");
        assertEquals(action.getClass(), AddTaskAction.class);
    }

    @Test
    public void parseInput_list_success() throws BotException {
        Action action = new Parser().parseInput("list");
        assertEquals(action.getClass(), ListTasksAction.class);
    }

    @Test
    public void parseInput_invalidFormatMark_exceptionThrown() {
        try {
            new Parser().parseInput("mark");
        } catch (BotException e) {
            assertEquals(e.getClass(), InvalidCommandFormatException.class);
        }
    }

    @Test
    public void parseInput_invalidIndexMark_exceptionThrown() {
        try {
            new Parser().parseInput("mark 0");
        } catch (BotException e) {
            assertEquals(e.getClass(), InvalidTaskIndexException.class);
        }
    }

    @Test
    public void parseInput_validMark_success() throws BotException {
        Action action = new Parser().parseInput("mark 1");
        assertEquals(action.getClass(), MarkTaskAction.class);
    }

    @Test
    public void parseInput_validUnmark_success() throws BotException {
        Action action = new Parser().parseInput("unmark 1");
        assertEquals(action.getClass(), UnmarkTaskAction.class);
    }

    @Test
    public void parseInput_validDelete_success() throws BotException {
        Action action = new Parser().parseInput("delete 1");
        assertEquals(action.getClass(), DeleteTaskAction.class);
    }

    @Test
    public void parseInput_validFind_success() throws BotException {
        Action action = new Parser().parseInput("find string");
        assertEquals(action.getClass(), FindTasksAction.class);
    }

    @Test
    public void parseInput_validSchedule_success() throws BotException {
        Action action = new Parser().parseInput("schedule 2024-01-01");
        assertEquals(action.getClass(), ListScheduleAction.class);
    }
}
