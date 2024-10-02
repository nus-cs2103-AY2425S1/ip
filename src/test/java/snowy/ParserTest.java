package snowy;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parse_allCapitalCommand_returnLowerCase() {
        assertArrayEquals(new String[] {"todo", "read book"}, Parser.parse("TODO read book"));
    }

    public void parse_capitalDescription_retainCapital() {
        assertArrayEquals(new String[] {"todo", "Read Book"}, Parser.parse("todo Read Book"));
    }
}
