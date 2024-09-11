package assistinator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    private Parser parser = new Parser();
    @Test
    void parseTask_invalidTodoFormat_throwsException() {
        AssitinatorExceptions exception = assertThrows(AssitinatorExceptions.class, () -> {
            parser.parseTask(Command.TODO, "todo");
        });
        assertEquals("Please follow format: todo {task description}", exception.getMessage());
    }

    @Test
    void parseTask_invalidDeadlineFormat_throwsException() {
        AssitinatorExceptions exception = assertThrows(AssitinatorExceptions.class, () -> {
            parser.parseTask(Command.DEADLINE, "deadline Submit report");
        });
        assertEquals("Please follow format: deadline {task description} /by {deadline}", exception.getMessage());
    }

    @Test
    void parseTask_invalidEventFormat_throwsException() {
        AssitinatorExceptions exception = assertThrows(AssitinatorExceptions.class, () -> {
            parser.parseTask(Command.EVENT, "event Team meeting /from 2023-08-15 14:00");
        });
        assertEquals("Please follow format: event {task description} /from {start} /to {end}", exception.getMessage());
    }
}
