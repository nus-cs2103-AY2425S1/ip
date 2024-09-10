package bean.parser;

import bean.exception.UnknownCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    private final Parser parser = new Parser();

    @Test
    public void testInvalidCommand() {
        // Test that an invalid command throws an UnknownCommandException
        assertThrows(UnknownCommandException.class, () -> {
            parser.parseCommand("asdkjhask");  // Invalid command
        });
    }
}
