package buddybot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test for To Do class
 */
public class TodoTest {
    /**
     * Test if toFile method gives the correct output
     * @throws Exception
     */
    @Test
        public void testFile() throws Exception {
            String description = "testTask";
            Todo testTodo = new Todo("testTask");
            assertEquals("T|0|testTask", testTodo.toFile());
            testTodo.mark();
            assertEquals("T|X|testTask", testTodo.toFile());
        }



}
