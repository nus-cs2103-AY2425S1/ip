package seedu.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.task.TaskList;

public class ParserTest {
    @Test
    public void markTaskAsDoneParser_invalidInput_unexpectedOutcome() throws Exception {
        try {
            Parser p = new Parser();
            p.markTaskAsDoneParser("mark blah blaah", new TaskList());
            fail();
        } catch (Exception e) {
            assertEquals("Invalid format", e.getMessage());
        }
    }

    @Test
    public void markTaskAsDoneParser_validInput_expectedOutcome() throws Exception {
        Parser p = new Parser();
        TaskList t = new TaskList();
        p.addToDoParser("todo todo", t);
        String out = p.markTaskAsDoneParser("mark 1", t);

        String expected = "Nice! I've marked this task as done:\n" + "[T][X] todo";
        assertEquals(expected, out);
    }
}
