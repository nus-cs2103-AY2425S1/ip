package mendel.discretetask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void showDeadline() {
        assertEquals("[T][ ] complete CS2103T",
                Todo.of("todo complete CS2103T").toString());
    }

    @Test
    public void noDescriptionException() {
        try {
            Todo.of("complete CS2103T");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! todo description cannot be empty.\nAdd description.", e.toString());
        }
    }
}
