package jackson.utils;

import jackson.actions.Actions;
import jackson.exceptions.SyntaxException;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parse_validInputs_correspondingResponseReturned(){
        try {
            assertEquals(Actions.ActionType.BYE, Parser.parse("bye").getAction());
            assertEquals(Actions.ActionType.SECRET, Parser.parse("secret").getAction());

            assertEquals(Actions.ActionType.UNMARK, Parser.parse("unmark 10").getAction());
            assertEquals("10", Parser.parse("unmark 10").getMatcher().group(1));

            assertEquals(Actions.ActionType.MARK, Parser.parse("mark 999").getAction());
            assertEquals("999", Parser.parse("unmark 999").getMatcher().group(1));

            assertEquals(Actions.ActionType.DELETE, Parser.parse("delete 1000").getAction());
            assertEquals("1000", Parser.parse("delete 1000").getMatcher().group(1));

            assertEquals(Actions.ActionType.TODO, Parser.parse("todo water plants").getAction());
            assertEquals("water plants", Parser.parse("todo water plants").getMatcher().group(1));

            assertEquals(Actions.ActionType.EVENT, Parser.parse("event aa "
                    + "/from 10-10-1010 /to 20-02-2929 19:00").getAction());
            assertEquals("aa", Parser.parse("event aa "
                    + "/from 10-10-1010 /to 20-02-2929 19:00").getMatcher().group(1));
            assertEquals("10-10-1010", Parser.parse("event aa "
                    + "/from 10-10-1010 /to 20-02-2929 19:00").getMatcher().group(2));
            assertEquals("20-02-2929 19:00", Parser.parse("event aa "
                    + "/from 10-10-1010 /to 20-02-2929 19:00").getMatcher().group(3));

            assertEquals(Actions.ActionType.DEADLINE, Parser.parse("deadline read /by 03-05-2002").getAction());
            assertEquals("read", Parser.parse("deadline read /by 03-05-2002").getMatcher().group(1));
            assertEquals("03-05-2002", Parser.parse("deadline read "
                    + "/by 03-05-2002").getMatcher().group(2));
        } catch (SyntaxException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidInputsBye_exceptionThrown() {
        SyntaxException exp = assertThrows(SyntaxException.class, () -> Parser.parse("bye bye"));
        assertEquals("bye", exp.getMessage());
    }

    @Test
    public void parse_invalidInputsUnmark_exceptionThrown() {
        SyntaxException exp1 = assertThrows(SyntaxException.class, () -> Parser.parse("unmark"));
        SyntaxException exp2 = assertThrows(SyntaxException.class, () -> Parser.parse("unmark one"));
        assertEquals("unmark", exp1.getMessage());
        assertEquals("unmark", exp2.getMessage());
    }

    @Test
    public void parse_invalidInputsMark_exceptionThrown() {
        SyntaxException exp1 = assertThrows(SyntaxException.class, () -> Parser.parse("mark"));
        SyntaxException exp2 = assertThrows(SyntaxException.class, () -> Parser.parse("mark two"));
        assertEquals("mark", exp1.getMessage());
        assertEquals("mark", exp2.getMessage());
    }

    @Test
    public void parse_invalidInputsDelete_exceptionThrown() {
        SyntaxException exp1 = assertThrows(SyntaxException.class, () -> Parser.parse("delete"));
        SyntaxException exp2 = assertThrows(SyntaxException.class, () -> Parser.parse("delete one"));
        assertEquals("delete", exp1.getMessage());
        assertEquals("delete", exp2.getMessage());
    }

    @Test
    public void parse_invalidInputsTodo_exceptionThrown() {
        SyntaxException exp = assertThrows(SyntaxException.class, () -> Parser.parse("todo"));
        assertEquals("todo", exp.getMessage());
    }

    @Test
    public void parse_invalidInputsDeadline_exceptionThrown() {
        SyntaxException exp1 = assertThrows(SyntaxException.class, () -> Parser.parse("deadline"));
        SyntaxException exp2 = assertThrows(SyntaxException.class, () -> Parser.parse("deadline haha"));
        assertEquals("deadline", exp1.getMessage());
        assertEquals("deadline", exp2.getMessage());
    }

    @Test
    public void parse_invalidInputsEvent_exceptionThrown() {
        SyntaxException exp1 = assertThrows(SyntaxException.class, () -> Parser.parse("event "
                + "hehe /from 29-10-2020"));
        SyntaxException exp2 = assertThrows(SyntaxException.class, () -> Parser.parse("event hehe"));
        SyntaxException exp3 = assertThrows(SyntaxException.class, () -> Parser.parse("event"));
        assertEquals("event", exp1.getMessage());
        assertEquals("event", exp2.getMessage());
        assertEquals("event", exp3.getMessage());
    }
}
