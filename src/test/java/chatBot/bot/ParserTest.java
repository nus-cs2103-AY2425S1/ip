package chatbot.bot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseTaskSuccessMethodTest() {
        assertEquals("todo read book", Parser.parseTask("todo read book").getOriginalCommand());

        assertEquals("deadline 5pm /by 2019-12-01", Parser.parseTask("deadline 5pm /by 2019-12-01")
                .getOriginalCommand());

        assertEquals("event sdf /from 2019-12-12 /to 2020-12-12",
                Parser.parseTask("event sdf /from 2019-12-12 /to 2020-12-12")
                .getOriginalCommand());
    }

    @Test
    public void parseTaskEmptyMethodTest() {
        assertNull(Parser.parseTask("todo "));
        assertNull(Parser.parseTask("deadline "));
        assertNull(Parser.parseTask("event "));

        assertNull(Parser.parseTask("deadline abcde"));
        assertNull(Parser.parseTask("deadline abcde /by 5pm"));

        assertNull(Parser.parseTask("event abcde"));
        assertNull(Parser.parseTask("event abcde /from 5pm"));
        assertNull(Parser.parseTask("event abcde /from 5pm /to 5pm"));
        assertNull(Parser.parseTask("event abcde /from 2019-12-12 /to "));

    }
}
