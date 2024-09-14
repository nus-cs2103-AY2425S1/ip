package duck.data.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DuckExceptionTest {

    @Test
    public void testDuckExceptionMessage() {
        DuckException de = new DuckException("Test error message");
        assertEquals("Test error message", de.getMessage(), "Exception message should match.");
    }
}
