package seedu.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.task.TaskList;
import seedu.parser.Parser;


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
}
