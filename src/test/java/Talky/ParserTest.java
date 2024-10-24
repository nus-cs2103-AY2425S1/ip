package Talky;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void invalidCommandArgs() {
        String line = "abcd";
        TalkyException exception = assertThrows(TalkyException.class, () -> Parser.commandArgs("abc abc", "abc"));
        assertEquals("Invalid Command", exception.getMessage());
    }
}