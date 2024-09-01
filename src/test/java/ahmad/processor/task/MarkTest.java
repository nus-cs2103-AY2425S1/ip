package ahmad.processor.task;

import ahmad.Parser;
import ahmad.exceptions.AhmadException;
import ahmad.exceptions.deadline.DeadlineEmptyNameException;
import ahmad.exceptions.deadline.DeadlineInvalidArgsException;
import ahmad.exceptions.deadline.DeadlineInvalidTimeException;
import ahmad.exceptions.event.EventEmptyNameException;
import ahmad.exceptions.event.EventInvalidArgsException;
import ahmad.exceptions.event.EventInvalidTimeException;
import ahmad.exceptions.mark.MarkIndexOutOfBoundsException;
import ahmad.exceptions.mark.MarkInvalidArgsException;
import ahmad.exceptions.mark.MarkInvalidNumberException;
import ahmad.exceptions.todo.TodoEmptyNameException;

import ahmad.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MarkTest {
    @Test
    public void validMarkTest() throws DeadlineInvalidTimeException, EventEmptyNameException, TodoEmptyNameException, DeadlineEmptyNameException, EventInvalidTimeException, EventInvalidArgsException, DeadlineInvalidArgsException {
        TaskList.addTask(Task.of(TaskType.Todo, "test"));
        try {
            Response response = Mark.process("mark 1");
            List<String> allResp = response.getResponse();
            assertEquals(allResp.size(), 1);
            assertEquals(allResp.get(0), "[T][X] test");
        } catch (AhmadException e) {
            fail("valid mark test fail");
        }
    }

    @Test
    public void indexOutOfBoundsMarkTest() {
        assertThrows(MarkIndexOutOfBoundsException.class, () -> Mark.process("mark 0"));
    }

    @Test
    public void invalidNumberMarkTest() {
        assertThrows(MarkInvalidNumberException.class, () -> Mark.process("mark invalid"));
    }
    @Test
    public void invalidArgsMarkTest() {
        assertThrows(MarkInvalidArgsException.class, () -> Mark.process("mark 12 12 12 12 12"));
    }
}