package easton;

import easton.model.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class EastonTest {

    @Test
    public void createToDo_emptyDescription_exceptionThrown() {
        String input = "todo ";
        assertThrows(EmptyDescriptionException.class, () -> {
            Easton.createToDo(input);
        });
    }

    @Test
    public void createToDo_withDescription_success() {
        String input = "todo hello world";
        assertDoesNotThrow(() -> {
            Easton.createToDo(input);
        });
    }
}
