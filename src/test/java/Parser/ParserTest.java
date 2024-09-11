package Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    private Parser p = new Parser();

    @Test
    public void checkStringPrefix() {

        assertEquals(true, p.checkStringPrefix("Event", 5, "Event"));

        assertEquals(true, p.checkStringPrefix("Hello world", 5, "Hello"));

        assertEquals(false, p.checkStringPrefix("Hallo world", 5, "Hello"));

        assertEquals(false, p.checkStringPrefix("Hello world", 5, ""));

    }
}
