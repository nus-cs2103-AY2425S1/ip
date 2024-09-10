package vuewee.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import vuewee.parser.IllegalCommandException;

public class CommandTypeTest {

    @Test
    public void testCreateCommand() {
        for (CommandType commandType : CommandType.values()) {
            Command command = commandType.createCommand();
            assertNotNull(command);
            switch (commandType) {
            case BYE:
                assertTrue(command instanceof ByeCommand);
                break;
            case LIST:
                assertTrue(command instanceof ListCommand);
                break;
            case MARK:
                assertTrue(command instanceof MarkCommand);
                break;
            case UNMARK:
                assertTrue(command instanceof UnmarkCommand);
                break;
            case DELETE:
                assertTrue(command instanceof DeleteCommand);
                break;
            case TODO:
                assertTrue(command instanceof TodoCommand);
                break;
            case DEADLINE:
                assertTrue(command instanceof DeadlineCommand);
                break;
            case EVENT:
                assertTrue(command instanceof EventCommand);
                break;
            case FIND:
                assertTrue(command instanceof FindCommand);
                break;
            case SCHEDULE:
                assertTrue(command instanceof ScheduleCommand);
                break;
            default:
                fail("Unknown command type");
            }
        }
    }

    @Test
    public void testFromString() {
        for (CommandType commandType : CommandType.values()) {
            CommandType fromString = CommandType.fromString(commandType.name().toLowerCase());
            assertEquals(commandType, fromString);
        }

        assertThrows(IllegalCommandException.class, () -> CommandType.fromString("unknown"));
    }

    @Test
    public void testToString() {
        for (CommandType commandType : CommandType.values()) {
            String toString = CommandType.toString(commandType);
            assertEquals(commandType.name().toLowerCase(), toString);
        }
    }
}
