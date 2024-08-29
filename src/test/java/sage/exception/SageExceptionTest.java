package sage.exception;

import org.junit.jupiter.api.Test;
import sage.Sage;

import static org.junit.jupiter.api.Assertions.*;

public class SageExceptionTest {
    @Test
    public void testSageExceptionMessage() {
        String errorMessage = "This is a custom error message";
        SageException exception = new SageException(errorMessage);

        // Verify that the message is correctly set
        assertEquals(errorMessage, exception.getMessage(), "SageException message should match the provided message");
    }

    @Test
    public void testSageExceptionThrown() {
        String errorMessage = "This is another custom error message";

        // Verify that the exception can be thrown and caught
        try {
            throw new SageException(errorMessage);
        } catch (SageException e) {
            assertEquals(errorMessage, e.getMessage(), "Caught SageException message should match the thrown message");
        }
    }
}
