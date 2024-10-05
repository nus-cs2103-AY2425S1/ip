package bocchi.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {
    @Test
    void parse_noParams_success() {
        Command command = Parser.parse("list");
        assertEquals("list", command.getName());
        assertNull(command.getParam());
        try {
            assertNull(command.getKeywordParam("key"));
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    void parse_noKeywordParams_success() {
        Command command = Parser.parse("mark 1");
        assertEquals("mark", command.getName());
        assertEquals("1", command.getParam());
        try {
            assertNull(command.getKeywordParam("key"));
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    void parse_hasKeywordParams_success() {
        Command command = Parser.parse("ddl thesis /by 2024-8-20");
        assertEquals("ddl", command.getName());
        assertEquals("thesis", command.getParam());
        assertEquals("2024-8-20", command.getKeywordParam("by"));
    }

    @Test
    void parse_hasSlashInParam_success() {
        Command command = Parser.parse("ddl thesis /by 2024/8/29");
        assertEquals("ddl", command.getName());
        assertEquals("thesis", command.getParam());
        assertEquals("2024/8/29", command.getKeywordParam("by"));
    }
}
