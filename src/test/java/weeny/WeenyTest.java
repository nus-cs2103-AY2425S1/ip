package weeny;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeenyTest {

    @Test
    public void testValidateIndexWithinBounds() {
        assertDoesNotThrow(() -> Weeny.validateIndex(0, 3, "mark"));
    }

    @Test
    public void testValidateIndexOutOfBounds() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> Weeny.validateIndex(5, 3, "delete"));
        assertEquals("Just a reminder. You can't delete tasks that don't exist!", exception.getMessage());
    }

    @Test
    public void testValidateIndexNegative() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> Weeny.validateIndex(-1, 3, "unmark"));
        assertEquals("Just a reminder. You can't unmark tasks that don't exist!", exception.getMessage());
    }
}
