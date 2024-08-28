package joe.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidIndexExceptionTest {
    @Test
    public void testInvalidIndexException() {
        InvalidIndexException invalidIndexException = new InvalidIndexException(-1);
        assertEquals("\"-1\" is not a valid index.\n" +
                "Type list to see the list of available indexes.", invalidIndexException.getMessage());
    }
}
