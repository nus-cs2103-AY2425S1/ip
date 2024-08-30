package thebotfather.task;

import java.util.StringTokenizer;
import org.junit.jupiter.api.Test;
import thebotfather.util.TheBotFatherException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

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

    /**
     * Tests the parsing of a Todo input string with incomplete input.
     * Verifies that a {@link TheBotFatherException} is thrown with the correct error message.
     */
    @Test
    void testParseTodoWithIncompleteInput() {
        String input = "";
        StringTokenizer tokenizer = new StringTokenizer(input);

        TheBotFatherException exception = assertThrows(TheBotFatherException.class, () -> {
            Todo.makeTodo(tokenizer);
        });

        assertEquals("Why to do a todo if there is no todo to do :/ \n" +
                "\tIf you have a todo, type : \"todo <description>\"", exception.getMessage());
    }
}
