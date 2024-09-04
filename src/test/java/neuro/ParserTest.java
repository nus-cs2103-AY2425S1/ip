package neuro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void getDeadlineByIndex_invalidInput_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () ->
                Parser.getDeadlineByIndex(new String[]{}));
    }

    @Test
    public void getDeadlineByIndex_validInput_correctIndexReturned() {
        int index = 2;
        assertEquals(index, Parser.getDeadlineByIndex(new String[]{"test", "test", "/by", "test"}));
    }
}
