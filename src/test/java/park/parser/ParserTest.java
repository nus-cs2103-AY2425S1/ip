package park.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_eventWithoutStart_exceptionThrown() {
        try {
            assertEquals(0, Parser.parse("event desc /from  /to 2000-12-12 1800"));
            fail();
        } catch (Exception e) {
            assertEquals("please provide desc, start, end", e.getMessage());
        }
    }

    @Test
    public void parse_invalidDateTime_exceptionThrown() {
        try {
            assertEquals(0, Parser.parse("deadline desc /by 2000 12 12 1800"));
            fail();
        } catch (Exception e) {
            assertEquals("please input DateTime in format: yyyy-MM-dd HHmm", e.getMessage());
        }
    }
}
