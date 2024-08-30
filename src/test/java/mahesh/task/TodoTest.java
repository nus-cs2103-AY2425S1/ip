package mahesh.task;

import java.util.StringTokenizer;
import org.junit.jupiter.api.Test;

import mahesh.util.MaheshException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


public class TodoTest {
    
    @Test
    public void testParseTodo() {
        String input = "todo Buy milk";
        StringTokenizer tokenizer = new StringTokenizer(input);
        tokenizer.nextToken();

        try {
            Todo todo = Todo.parseTodo(tokenizer);
            assertEquals("[T][ ] Buy milk", todo.toString());
        } catch (MaheshException e) {
            fail("Exception should not be thrown for valid input");
        }
    }

    @Test
    public void testParseTodoWithInvalidInput() {
        String input = "todo";
        StringTokenizer tokenizer = new StringTokenizer(input);
        tokenizer.nextToken(); // Skip the "todo" part

        assertThrows(MaheshException.class, () -> {
            Todo.parseTodo(tokenizer);
        });
    }
}
