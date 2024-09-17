package weeny;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class WeenyTest {

    @Test
    public void testValidateIndexWithinBounds() {
        Assertions.assertDoesNotThrow(() -> Weeny.validateIndex(0, 3, "mark"));
    }

    @Test
    public void testValidateIndexOutOfBounds() {
        IndexOutOfBoundsException exception = Assertions.assertThrows(IndexOutOfBoundsException.class, (
                ) -> Weeny.validateIndex(5, 3, "delete"));
        Assertions.assertEquals("Invalid index for delete action.", exception.getMessage());
    }

    @Test
    public void testValidateIndexNegative() {
        IndexOutOfBoundsException exception = Assertions.assertThrows(IndexOutOfBoundsException.class, (
                )-> Weeny.validateIndex(-1, 3, "unmark"));
        Assertions.assertEquals("Invalid index for unmark action.", exception.getMessage());
    }
}
