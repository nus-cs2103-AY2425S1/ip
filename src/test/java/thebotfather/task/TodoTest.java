package thebotfather.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.StringTokenizer;

import org.junit.jupiter.api.Test;

import thebotfather.util.TheBotFatherException;


/**
 * A test suite for the {@link Todo} class.
 * This class tests the parsing and creation of Todo tasks, ensuring that they are correctly instantiated and exceptions are properly thrown for invalid inputs.
 */
public class TodoTest {

    /**
     * Tests the parsing of a valid Todo input string.
     * Verifies that the {@link Todo} object is correctly created and formatted.
     */
    @Test
    public void testParseTodo() {
        String input = "return book";
        StringTokenizer tokenizer = new StringTokenizer(input);

        try {
            Todo todo = Todo.makeTodo(tokenizer);
            assertEquals("[T][ ] return book", todo.toString());
        } catch (TheBotFatherException e) {
            fail("Exception should not be thrown for valid input");
        }
    }
}
