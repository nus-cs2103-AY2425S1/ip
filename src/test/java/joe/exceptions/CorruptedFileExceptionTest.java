package joe.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the CorruptedFileException class.
 */
public class CorruptedFileExceptionTest {

    /**
     * Tests the getMessage method in CorruptedFileException.
     */
    @Test
    public void testCorruptedFileException() {
        CorruptedFileException corruptedFileException = new CorruptedFileException();
        assertEquals("The file is corrupted", corruptedFileException.getMessage());
    }
}
