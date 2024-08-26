package killua.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class KilluaExceptionTest {

    @Test
    public void testKilluaExceptionMessage() {
        String errorMessage = "Something went wrong";
        KilluaException exception = new KilluaException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testKilluaExceptionThrowing() {
        String errorMessage = "An error occurred";

        assertThrows(KilluaException.class, () -> {
            throw new KilluaException(errorMessage);
        });
    }
}

