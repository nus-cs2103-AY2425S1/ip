package elsa.ui;

import elsa.command.*;
import elsa.ElsaException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testTodoCommandEmptyDescription() {
        assertThrows(ElsaException.class, () -> Parser.parse("todo "));
    }

    @Test
    public void testDeadlineCommandInvalidFormat() {
        assertThrows(ElsaException.class, () -> Parser.parse("deadline submit report /by invalid-date"));
    }

    @Test
    public void testDeleteCommand() throws ElsaException {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }
}
