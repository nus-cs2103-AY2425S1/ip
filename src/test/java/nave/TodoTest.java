package nave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todoCreationSuccess() {
        try {
            assertEquals(new Todo("test item 1").toString(), Todo.handleInput("test item 1").toString());
        } catch (WrongInputException e) {
            fail();
        }
    }

    @Test
    public void todoExceptionThrown() {
        try {
            Todo.handleInput("");
        } catch (WrongInputException e) {
            assertEquals("This todo doesn't have a name!", e.getMessage());
        }
    }
}
