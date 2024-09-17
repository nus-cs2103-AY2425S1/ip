package elsa.ui;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import elsa.ElsaException;
import elsa.command.Command;
import elsa.command.DeleteCommand;

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
