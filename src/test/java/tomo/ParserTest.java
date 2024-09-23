package tomo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import command.Command;
import exception.ToMoException;
import tasklist.TaskList;

public class ParserTest {

    @Test
    public void testParser() {
        Parser parser = new Parser();
        TaskList tasks = new TaskList();
        try {
            Command command = parser.parse("todo dummy 1");
            assertEquals("A task is added:\n"
                    + "[T][ ] dummy 1 tags:\n", command.getResponse(tasks));
        } catch (ToMoException e) {
            assertEquals(1, 0);
        }

        try {
            Command command = parser.parse("todo dummy 2");
            assertEquals("A task is added:\n"
                    + "[T][ ] dummy 2 tags:\n", command.getResponse(tasks));
        } catch (ToMoException e) {
            assertEquals(1, 0);
        }

        try {
            Command command = parser.parse("todo dummy 3");
            assertEquals("A task is added:\n"
                    + "[T][ ] dummy 3 tags:\n", command.getResponse(tasks));
        } catch (ToMoException e) {
            assertEquals(1, 0);
        }

        try {
            Command command = parser.parse("delete 2");
            assertEquals("A task is deleted:\n"
                    + "[T][ ] dummy 2 tags:\n", command.getResponse(tasks));
        } catch (ToMoException e) {
            assertEquals(1, 0);
        }

        try {
            Command command = parser.parse("tag 2 #dummy");
            assertEquals("A task is tagged:\n"
                    + "[T][ ] dummy 3 tags: #dummy\n", command.getResponse(tasks));
        } catch (ToMoException e) {
            assertEquals(1, 0);
        }
    }
}
