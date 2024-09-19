package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exception.ElliotException;

public class CommandTypeTest {

    @Test
    public void invalidArgument_exceptionThrown() {
        assertThrows(ElliotException.class, () -> {
            CommandType.parseStringToCommand("blah");
        });
    }

    @Test
    public void parseStringToCommand_success() throws ElliotException {
        assertEquals(CommandType.LIST, CommandType.parseStringToCommand("list"));
        assertEquals(CommandType.MARK, CommandType.parseStringToCommand("MARK"));
        assertEquals(CommandType.UNMARK, CommandType.parseStringToCommand("unMark"));
        assertEquals(CommandType.DELETE, CommandType.parseStringToCommand("DELete"));
        assertEquals(CommandType.TODO, CommandType.parseStringToCommand("ToDo"));
        assertEquals(CommandType.DEADLINE, CommandType.parseStringToCommand("DeadLine"));
        assertEquals(CommandType.EVENT, CommandType.parseStringToCommand("EvEnT"));
        assertEquals(CommandType.BYE, CommandType.parseStringToCommand("bYe"));
    }
}
