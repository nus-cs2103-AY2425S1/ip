package strand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import strand.command.AddCommand;
import strand.command.ByeCommand;
import strand.command.DeleteCommand;
import strand.command.ListCommand;
import strand.command.MarkCommand;
import strand.exception.StrandException;

/**
 * The strand.ParserTest class is a JUnit test for the class strand.Parser
 */
public class ParserTest {
    @Test
    public void testValidCommands() throws StrandException {
        assertInstanceOf(AddCommand.class, Parser.parse("todo read book"));
        assertInstanceOf(AddCommand.class, Parser.parse("deadline do homework /by 9/09/9090"));
        assertInstanceOf(AddCommand.class, Parser.parse(" event project meeting /from 2/12/2020 1800 /to 2/12/3030"));
        assertInstanceOf(AddCommand.class, Parser.parse("event do homework /from 1/01/1010 /to 9/09/9090"));
        assertInstanceOf(ByeCommand.class, Parser.parse("Bye"));
        assertInstanceOf(DeleteCommand.class, Parser.parse("Delete 1"));
        assertInstanceOf(DeleteCommand.class, Parser.parse(" delete 100"));
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
        assertInstanceOf(ListCommand.class, Parser.parse(" LIST "));
        assertInstanceOf(MarkCommand.class, Parser.parse("mark 0"));
        assertInstanceOf(MarkCommand.class, Parser.parse("mark 0 high"));
        assertInstanceOf(MarkCommand.class, Parser.parse("mark 0 mEdium"));
    }

    @Test
    public void testInvalidAddCommand() {
        try {
            Parser.parse("todo");
        } catch (StrandException e) {
            assertEquals("Description not found (×_×;）\n"
                    + "Please include a Description for this task", e.getMessage());
        }
        try {
            Parser.parse("deadline do homework /by tomorrow");
        } catch (StrandException e) {
            assertEquals("tomorrow is not a valid date (×_×;）\n"
                    + "Please include a date of the correct format (e.g. 2/12/2019 1800)", e.getMessage());
        }

        try {
            Parser.parse("event project meeting /from 8/88/8888 /to 9/99/9999");
        } catch (StrandException e) {
            assertEquals("8/88/8888 is not a valid date (×_×;）\n"
                    + "Please include a date of the correct format (e.g. 2/12/2019 1800)", e.getMessage());
        }

    }

    @Test
    public void testInvalidInteger() {
        try {
            Parser.parse("mark me");
        } catch (StrandException e) {
            assertEquals("Index for task not found (×_×;）"
                    + "Please input a number after mark. (e.g. mark 1)", e.getMessage());
        }

        try {
            Parser.parse("unmark 000");
        } catch (StrandException e) {
            assertEquals("Index for task not found (×_×;）"
                    + "Please input a number after unmark. (e.g. unmark 1)", e.getMessage());
        }

        try {
            Parser.parse("delete task");
        } catch (StrandException e) {
            assertEquals("Index for task not found (×_×;）"
                    + "Please input a number after delete. (e.g. delete 1)", e.getMessage());
        }

    }

    @Test
    public void testInvalidCommand() {
        try {
            Parser.parse("thisIsAnInvalidCommand");
        } catch (StrandException e) {
            assertEquals("Command not found (×_×;）", e.getMessage());
        }
        try {
            Parser.parse("todo read book");
            Parser.parse("mark 0 noPriority");
        } catch (StrandException e) {
            assertEquals("Command not found (×_×;）", e.getMessage());
        }

    }
}
