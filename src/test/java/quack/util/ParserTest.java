package quack.util;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import quack.command.AddTagCommand;
import quack.command.AddTaskCommand;
import quack.command.Command;
import quack.command.DeleteTaskCommand;
import quack.command.ExitCommand;
import quack.command.FindTaskCommand;
import quack.command.ListCommand;
import quack.command.RemoveTagCommand;
import quack.command.UpdateTaskCommand;

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

        Command output = this.parser.processCommand("list");

        assertInstanceOf(ListCommand.class, output,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a exit command is returned when given a input, bye.
     */
    @Test
    void testProcessByeCommand() {

        Command output = this.parser.processCommand("bye");

        assertInstanceOf(ExitCommand.class, output,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a add task command is returned when given a input, add.
     */
    @Test
    void testProcessAddCommand() {

        Command output = this.parser.processCommand("add");

        assertInstanceOf(AddTaskCommand.class, output,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a update task command is returned when given a input, mark or unmark.
     */
    @Test
    void testProcessUpdateTaskCommand() {

        Command output = this.parser.processCommand("mark");

        assertInstanceOf(UpdateTaskCommand.class, output,
            "Parser is returning the wrong command");

        output = this.parser.processCommand("unmark");

        assertInstanceOf(UpdateTaskCommand.class, output,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a delete command is returned when given a input, delete.
     */
    @Test
    void testProcessDeleteCommand() {

        Command output = this.parser.processCommand("delete");

        assertInstanceOf(DeleteTaskCommand.class, output,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a find command is returned when given a input, find.
     */
    @Test
    void testProcessFindCommand() {

        Command output = this.parser.processCommand("find");

        assertInstanceOf(FindTaskCommand.class, output,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a tag command is returned when given a input, tag.
     */
    @Test
    void testProcessTagCommand() {

        Command output = this.parser.processCommand("tag");

        assertInstanceOf(AddTagCommand.class, output,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command.
     * <p>
     * Ensures that a remove tag command is returned when given a input, untag.
     */
    @Test
    void testProcessUntagCommand() {

        Command output = this.parser.processCommand("untag");

        assertInstanceOf(RemoveTagCommand.class, output,
            "Parser is returning the wrong command");
    }

    /**
     * Tests if the parser can return the correct command with uppercases.
     * <p>
     * Ensures that the correct command is returned even if the input has some uppercases.
     */
    @Test
    void testProcessCapsInput() {

        Command output = this.parser.processCommand("LiSt");

        assertInstanceOf(ListCommand.class, output,
            "Parser is returning the wrong command");

        output = this.parser.processCommand("deLeTE");

        assertInstanceOf(DeleteTaskCommand.class, output,
                "Parser is returning the wrong command");
    }
}
