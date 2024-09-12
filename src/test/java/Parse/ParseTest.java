package Parse;

import Task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseTest {
    @Test
    public void parseTodo_test() {
        assertEquals("read book", Parse.parseTodo("todo read book"));
    }
}
