package command;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

import exception.InvalidCommandException;

public class UserCommandTest {
    @Test
    public void toCommand_exitCommand_success() {
        assertInstanceOf(ExitCommand.class, UserCommand.toCommand("bye"));
    }

    @Test
    public void toCommand_listCommand_success() {
        assertInstanceOf(ListCommand.class, UserCommand.toCommand("list"));
    }

    @Test
    public void toCommand_updateStatusCommand_success() {
        Class<UpdateStatusCommand> expectedClass = UpdateStatusCommand.class;
        assertInstanceOf(expectedClass, UserCommand.toCommand("mark"));
        assertInstanceOf(expectedClass, UserCommand.toCommand("unmark"));
    }

    @Test
    public void toCommand_addCommand_success() {
        Class<AddCommand> expectedClass = AddCommand.class;
        assertInstanceOf(expectedClass, UserCommand.toCommand("todo"));
        assertInstanceOf(expectedClass, UserCommand.toCommand("deadline"));
        assertInstanceOf(expectedClass, UserCommand.toCommand("event"));
    }

    @Test
    public void toCommand_deleteCommand_success() {
        assertInstanceOf(DeleteCommand.class, UserCommand.toCommand("delete"));
    }

    @Test
    public void toCommand_invalidCommand_invalidCommandExceptionThrow() {
        assertThrowsExactly(InvalidCommandException.class, () -> UserCommand.toCommand("invalid"));
    }
}
