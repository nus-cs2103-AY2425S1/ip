package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.parser.Parser;
import seedu.task.TaskList;


public class ParserTest {
    @Test
    public void markTaskParser_invalidInput_unexpectedOutcome() throws Exception {
        try {
            Parser p = new Parser();
            p.markTaskParser("mark blah blaah", new TaskList());
            fail();
        } catch (Exception e) {
            assertEquals("Invalid format", e.getMessage());
        }
    }
}
