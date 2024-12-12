package duch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    class TaskListStub extends TaskList {
        TaskListStub() {
            super();
        }
    }
    
    @Test
    public void testConcat() {
        Parser parser = new Parser(new TaskListStub());
        String[] arr = {"a1", "a2", "a3", "a4", "a5"};
        String expected = "a2 a3";
        assertEquals(expected, parser.concat(arr, 1, 3));
    }

    @Test
    public void testConcatIndexOutOfBounds() {
        try {
            Parser parser = new Parser(new TaskListStub());
            String[] arr = {"a1", "a2", "a3", "a4", "a5"};
            parser.concat(arr, 0, 10);
            fail();
        } catch (Exception e) {
            // success
        }
        

    }
}
