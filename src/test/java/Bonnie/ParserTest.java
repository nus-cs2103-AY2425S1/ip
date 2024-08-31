package Bonnie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseTaskAddition_invalidCommand_exceptionThrown() {
        try {
            Parser.parseInput("this is not a valid input bonnie!");
            fail();
        } catch (Exception e) {
            assertEquals("Hey there, Bonnie does not understand what you mean by \"this is not a valid input bonnie!\"!",
                    e.getMessage());
        }
    }

    @Test
    public void parseTaskAddition_emptyTodoCommand_exceptionThrown() {
        try {
            Parser.parseInput("todo");
            fail();
        } catch (Exception e) {
            assertEquals("Hey there, the body of your todo task cannot be empty!",
                    e.getMessage());
        }
    }
}
