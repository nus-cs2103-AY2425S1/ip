package duke.Parser; // same package as the class being tested

import duke.MyTask.Task;
import duke.MyTask.Todo;
import duke.MyException.EmptyDescriptionException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parseTodoInputTest() {
        String name = "Todo Task";
        String input = "todo " + name;

        try {
            Task t = Parser.parseTodoInput(input);

            Todo expected = new Todo(name);
            assertEquals(expected.toString(), t.toString());
        } catch (Exception e) {}
    }

    @Test
    public void parseEmptyDescriptionExceptionTest() {
        String input = "todo";

        EmptyDescriptionException e = assertThrows(EmptyDescriptionException.class, () -> {
            Parser.parseTodoInput(input);
        });

        String expectedMessage = "Description cannot be empty.";
        assertEquals(expectedMessage, e.getMessage());
    }
}
