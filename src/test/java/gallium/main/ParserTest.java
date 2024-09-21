package gallium.main;

import gallium.command.AddCommand;
import gallium.command.ByeCommand;
import gallium.command.DateCommand;
import gallium.command.DeleteCommand;
import gallium.command.ListCommand;
import gallium.command.MarkCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    private static final String BYE = "bye";
    private static final String LIST = "list";

    @Test
    void parserTest() throws GalliumException {
        Ui ui = new Ui();
        Parser parser = new Parser(ui);
        assertTrue(parser.parse(BYE) instanceof ByeCommand);
        assertTrue(parser.parse(LIST) instanceof ListCommand);
        assertTrue(parser.parse("mark 1") instanceof MarkCommand);
        assertTrue(parser.parse("delete 1") instanceof DeleteCommand);
        assertTrue(parser.parse("todo test") instanceof AddCommand);
        assertTrue(parser.parse("deadline test /by 2024-09-09 1000") instanceof AddCommand);
        assertTrue(parser.parse("event event /from 2024-09-09 1000 /to 2024-09-09 1100") instanceof AddCommand);
        assertTrue(parser.parse("date 2024-09-09") instanceof DateCommand);
    }
}
