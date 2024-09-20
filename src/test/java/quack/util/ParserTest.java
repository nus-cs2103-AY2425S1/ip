package quack.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import quack.command.AddTagCommand;
import quack.command.AddTaskCommand;
import quack.command.Command;
import quack.command.DeleteTaskCommand;
import quack.command.ExitCommand;
import quack.command.FindTaskCommand;
import quack.command.HelpCommand;
import quack.command.ListCommand;
import quack.command.RemoveTagCommand;
import quack.command.UpdateTaskCommand;
import quack.exception.InvalidCommandException;

/**
 * This class is to test the functionality of the Parser class.
 */
public class ParserTest {

    /** Parser object to handle user inputs */
    private Parser parser;

    /**
     * Initializes and sets all variables to its default value after each test.
     */
    @BeforeEach
    public void initializeVariables() {
        this.parser = new Parser(null, null, null);
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a list command is returned when given a input, list.
     */
    @Test
    void testProcessListCommand() {

        Command actualCommand = assertDoesNotThrow(() -> this.parser.processCommand("list"),
                "Function is not throw supposed to throw an exception");

        assertInstanceOf(ListCommand.class, actualCommand,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a exit command is returned when given a input, bye.
     */
    @Test
    void testProcessByeCommand() {

        Command actualCommand = assertDoesNotThrow(() -> this.parser.processCommand("bye"),
            "Function is not throw supposed to throw an exception");

        assertInstanceOf(ExitCommand.class, actualCommand,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a add task command is returned when given a input, add.
     */
    @Test
    void testProcessAddCommand() {

        Command actualCommand = assertDoesNotThrow(() -> this.parser.processCommand("add"),
            "Function is not throw supposed to throw an exception");

        assertInstanceOf(AddTaskCommand.class, actualCommand,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a update task command is returned when given a input, mark.
     */
    @Test
    void testProcessMarkTaskCommand() {

        Command actualCommand = assertDoesNotThrow(() -> this.parser.processCommand("mark"),
            "Function is not throw supposed to throw an exception");

        assertInstanceOf(UpdateTaskCommand.class, actualCommand,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a update task command is returned when given a input, unmark.
     */
    @Test
    void testProcessUnmarkTaskCommand() {

        Command actualCommand = assertDoesNotThrow(() -> this.parser.processCommand("unmark"),
            "Function is not throw supposed to throw an exception");

        assertInstanceOf(UpdateTaskCommand.class, actualCommand,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a delete command is returned when given a input, delete.
     */
    @Test
    void testProcessDeleteCommand() {

        Command actualCommand = assertDoesNotThrow(() -> this.parser.processCommand("delete"),
            "Function is not throw supposed to throw an exception");

        assertInstanceOf(DeleteTaskCommand.class, actualCommand,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a find command is returned when given a input, find.
     */
    @Test
    void testProcessFindCommand() {

        Command actualCommand = assertDoesNotThrow(() -> this.parser.processCommand("find"),
            "Function is not throw supposed to throw an exception");

        assertInstanceOf(FindTaskCommand.class, actualCommand,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a tag command is returned when given a input, tag.
     */
    @Test
    void testProcessTagCommand() {

        Command actualCommand = assertDoesNotThrow(() -> this.parser.processCommand("tag"),
            "Function is not throw supposed to throw an exception");

        assertInstanceOf(AddTagCommand.class, actualCommand,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a remove tag command is returned when given a input, untag.
     */
    @Test
    void testProcessUntagCommand() {

        Command actualCommand = assertDoesNotThrow(() -> this.parser.processCommand("untag"),
            "Function is not throw supposed to throw an exception");

        assertInstanceOf(RemoveTagCommand.class, actualCommand,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a help command is returned when given a input, help.
     */
    @Test
    void testProcessHelpCommand() {

        Command actualCommand = assertDoesNotThrow(() -> this.parser.processCommand("help"),
            "Function is not throw supposed to throw an exception");

        assertInstanceOf(HelpCommand.class, actualCommand,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command with uppercases.
     * <p>
     * Ensures that the correct command is returned even if the input has some uppercases.
     */
    @Test
    void testProcessCapsInput() {

        Command actualCommand = assertDoesNotThrow(() -> this.parser.processCommand("LiSt"),
                "Function is not throw supposed to throw an exception");

        assertInstanceOf(ListCommand.class, actualCommand,
                "Parser is returning the wrong command");

        actualCommand = assertDoesNotThrow(() -> this.parser.processCommand("DeLeTE"),
                "Function is not throw supposed to throw an exception");

        assertInstanceOf(DeleteTaskCommand.class, actualCommand,
                "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can handle invalid inputs.
     * <p>
     * Ensures that the function can throws an exception when the user inputs
     * an invalid command.
     */
    @Test
    void testProcessInvalidInput() {

        InvalidCommandException exception = assertThrows(InvalidCommandException.class, () ->
                this.parser.processCommand("Funnyyy"),
                "Function did not throw an exception when it is supposed to");

        String expectedMessage = "I have no idea what you mean by Funnyyy, please try another command";

        assertEquals(expectedMessage, exception.getMessage(),
                "The exception message is incorrect");
    }
}
