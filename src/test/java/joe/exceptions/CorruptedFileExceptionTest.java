package joe.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CorruptedFileExceptionTest {
    @Test
    public void testCorruptedFileException() {
        CorruptedFileException corruptedFileException = new CorruptedFileException();
        assertEquals("The file is corrupted", corruptedFileException.getMessage());
    }
}
