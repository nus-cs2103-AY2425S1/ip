package chatgpt.command;

import chatgpt.exception.ChatBotException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_addCommand() {
        AddCommand expectedClass = new AddCommand("TODO","test");
        Command actualClass;
        try {
            actualClass = Parser.parse("todo test");
            assertSame(expectedClass.getClass(), actualClass.getClass());
        } catch (ChatBotException e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand() {
        DeleteCommand expectedClass = new DeleteCommand(0);
        Command actualClass;
        try {
            actualClass = Parser.parse("delete 1");
            assertSame(expectedClass.getClass(), actualClass.getClass());
        } catch (ChatBotException e) {
            fail();
        }
    }

    @Test
    public void parse_completeCommand() {
        CompleteCommand expectedClass = new CompleteCommand(0, true);
        Command actualClass;
        try {
            actualClass = Parser.parse("mark 1");
            assertSame(expectedClass.getClass(), actualClass.getClass());
        } catch (ChatBotException e) {
            fail();
        }
    }

    @Test
    public void parse_listCommand() {
        ListCommand expectedClass = new ListCommand();
        Command actualClass;
        try {
            actualClass = Parser.parse("list");
            assertSame(expectedClass.getClass(), actualClass.getClass());
        } catch (ChatBotException e) {
            fail();
        }
    }

    @Test
    public void parse_findCommand() {
        FindCommand expectedClass = new FindCommand("test");
        Command actualClass;
        try {
            actualClass = Parser.parse("find test");
            assertSame(expectedClass.getClass(), actualClass.getClass());
        } catch (ChatBotException e) {
            fail();
        }
    }

    @Test
    public void parse_showCommand() {
        ShowCommand expectedClass = new ShowCommand(0);
        Command actualClass;
        try {
            actualClass = Parser.parse("show 0");
            assertSame(expectedClass.getClass(), actualClass.getClass());
        } catch (ChatBotException e) {
            fail();
        }
    }

    @Test
    public void parse_badCommand_exceptionThrown() {
        AddCommand expectedClass = new AddCommand("TODO","test");
        String expected = "\t Oops!! I don't understand what that means :((";
        Command actualClass;
        try {
            actualClass = Parser.parse("bad command");
            assertSame(expected.getClass(), actualClass.getClass());
            fail(); // Code should throw exception and not reach here
        } catch (ChatBotException e) {
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void parse_emptyInput_exceptionThrown() {
        AddCommand expectedClass = new AddCommand("TODO","test");
        String expected = "\t Oh no!! Inputs after the command cannot be empty";
        Command actualClass;
        try {
            actualClass = Parser.parse("todo");
            assertSame(expected.getClass(), actualClass.getClass());
            fail(); // Code should throw exception and not reach here
        } catch (ChatBotException e) {
            assertEquals(expected, e.getMessage());
        }
    }
}
