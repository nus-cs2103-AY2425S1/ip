package hana.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hana.HanaException;
import hana.command.Command;
import hana.command.FindByDateCommand;
import hana.command.ToDoCommand;

public class ParserTest {

    @Test
    public void parse_validAddCommand_returnsAddCommand() throws HanaException {
        Command command = Parser.parse("todo Test task");
        assertTrue(command instanceof ToDoCommand);
    }

    @Test
    public void parse_invalidCommand_throwsHanaException() {
        assertThrows(HanaException.class, () -> Parser.parse("invalid command"));
    }

    @Test
    public void parse_validFindByDateCommand_returnsFindByDateCommand() throws HanaException {
        Command command = Parser.parse("findByDate 2/12/2019");
        assertTrue(command instanceof FindByDateCommand);
    }
}
