package jbot.util;

import jbot.command.ByeCommand;
import jbot.command.DeadlineCommand;
import jbot.command.DeleteCommand;
import jbot.command.EventCommand;
import jbot.command.InvalidCommandException;
import jbot.command.JBotCommand;
import jbot.command.ListCommand;
import jbot.command.MarkCommand;
import jbot.command.ToDoCommand;
import jbot.command.UnmarkCommand;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("FeatureEnvy")
public class ParserTest {

    @BeforeAll
    public static void setUp() {
        Parser.init();
    }

    @Test
    public void testParseValidCommands() {
        // Test valid commands
        assertDoesNotThrow(() -> {
            JBotCommand listCommand = Parser.parse("list");
            assertInstanceOf(ListCommand.class, listCommand);

            JBotCommand byeCommand = Parser.parse("bye");
            assertInstanceOf(ByeCommand.class, byeCommand);

            JBotCommand markCommand = Parser.parse("mark");
            assertInstanceOf(MarkCommand.class, markCommand);

            JBotCommand unmarkCommand = Parser.parse("unmark");
            assertInstanceOf(UnmarkCommand.class, unmarkCommand);

            JBotCommand todoCommand = Parser.parse("todo");
            assertInstanceOf(ToDoCommand.class, todoCommand);

            JBotCommand deadlineCommand = Parser.parse("deadline");
            assertInstanceOf(DeadlineCommand.class, deadlineCommand);

            JBotCommand eventCommand = Parser.parse("event");
            assertInstanceOf(EventCommand.class, eventCommand);

            JBotCommand deleteCommand = Parser.parse("delete");
            assertInstanceOf(DeleteCommand.class, deleteCommand);
        });
    }

    @Test
    public void testParseInvalidCommand() {
        // Test invalid command
        InvalidCommandException thrown = assertThrows(
                InvalidCommandException.class,
                () -> Parser.parse("invalidCommand"),
                "Expected parse() to throw, but it didn't"
        );
        assertEquals("Invalid command: invalidCommand", thrown.getMessage());
    }

    @Test
    public void testParseEmptyCommand() {
        // Test empty command string
        InvalidCommandException thrown = assertThrows(
                InvalidCommandException.class,
                () -> Parser.parse(""),
                "Expected parse() to throw, but it didn't"
        );
        assertEquals("Invalid command: ", thrown.getMessage());
    }


    @Test
    public void testParseCommandWithAdditionalArguments() {
        // Test command with additional arguments
        assertDoesNotThrow(() -> {
            JBotCommand todoCommand = Parser.parse("todo some extra arguments");
            assertInstanceOf(ToDoCommand.class, todoCommand);
        });
    }
}