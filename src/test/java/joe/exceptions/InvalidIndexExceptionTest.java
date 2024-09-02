package joe.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the InvalidIndexException class.
 */
public class InvalidIndexExceptionTest {
    /**
     * Tests the getMessage method in InvalidIndexException.
     */
    @Test
    public void testInvalidIndexException() {
        InvalidIndexException invalidIndexException = new InvalidIndexException(-1);
        assertEquals("\"-1\" is not a valid index.\n"
                + "Type list to see the list of available indexes.",
                invalidIndexException.getMessage());
    }
}
