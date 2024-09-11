package Mentos.components;

import Mentos.Commands.Command;
import Mentos.MentosException.MentosException;
import Mentos.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    void testListCommand() {
        try {
            Command result = parser.taskHandler("list");
            assertNotNull(result);
            assertEquals("list", result.getAction());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void testMarkCommandValid() {
        try {
            Command result = parser.taskHandler("mark 1");
            assertNotNull(result);
            assertEquals("mark", result.getAction());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testDeleteCommandValid() {
        try {
            Command result = parser.taskHandler("delete 3");
            assertNotNull(result);
            assertEquals("delete", result.getAction());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
