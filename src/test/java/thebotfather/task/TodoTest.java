package thebotfather.task;

import java.util.StringTokenizer;
import org.junit.jupiter.api.Test;
import thebotfather.util.TheBotFatherException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {

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

    @Test void testParseTodoWithIncompleteInput() {
        String input = "";
        StringTokenizer tokenizer = new StringTokenizer(input);

        TheBotFatherException exception = assertThrows(TheBotFatherException.class, () -> {
            Todo.makeTodo(tokenizer);
        });

        assertEquals("Why to do a todo if there is no todo to do :/ \n" +
                "\tIf you have a todo, type : \"todo <description>\"", exception.getMessage());
    }
}
