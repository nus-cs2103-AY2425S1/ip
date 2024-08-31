package barcus.parser;

import barcus.command.*;

import barcus.exception.BarcusException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void testParseUnknownCommand() {
        try {
            Command c = Parser.parse("huhhh");
            assertEquals(c.getClass(), UnknownCommand.class);
        }  catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseEmptyCommand() {
        try {
            Command c = Parser.parse("");
            assertEquals(c.getClass(), UnknownCommand.class);
        } catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseExitCommand() {
        try {
            Command c = Parser.parse("bye");
            assertEquals(c.getClass(), ExitCommand.class);
        }  catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseListCommand() {
        try {
            Command c = Parser.parse("list");
            assertEquals(c.getClass(), ListCommand.class);
        }  catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseMarkCommand() {
        try {
            Command c = Parser.parse("mark 2");
            assertEquals(c.getClass(), MarkCommand.class);
        }  catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseMarkCommandWrongLength() {
        try {
            Command c = Parser.parse("mark");
            fail();
        }  catch (BarcusException e) {
            assertEquals("please have an integer after 'mark'", e.getMessage());
        }
    }

    @Test
    public void testParseWrongMarkCommandNoInteger() {
        try {
            Command c = Parser.parse("mark hehe");
            fail();
        }  catch (BarcusException e) {
            assertEquals("please have an integer after 'mark'", e.getMessage());
        }
    }

    @Test
    public void testParseUnmarkCommand() {
        try {
            Command c = Parser.parse("unmark 2");
            assertEquals(c.getClass(), UnmarkCommand.class);
        }  catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseWrongUnmarkCommandWrongLength() {
        try {
            Command c = Parser.parse("unmark");
            fail();
        }  catch (BarcusException e) {
            assertEquals("please have an integer after 'unmark'", e.getMessage());
        }
    }

    @Test
    public void testParseWrongUnmarkCommandNoInteger() {
        try {
            Command c = Parser.parse("unmark hehe");
            fail();
        }  catch (BarcusException e) {
            assertEquals("please have an integer after 'unmark'", e.getMessage());
        }
    }

    @Test
    public void testParseTodoCommand() {
        try {
            Command c = Parser.parse("todo homework");
            assertEquals(c.getClass(), AddTodoCommand.class);
        }  catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseWrongTodoCommandWrongLength() {
        try {
            Command c = Parser.parse("todo");
            fail();
        }  catch (BarcusException e) {
            assertEquals("please include a description of the todo", e.getMessage());
        }
    }

    @Test
    public void testParseDeadlineCommand() {
        try {
            Command c = Parser.parse("deadline homework /by 22/08/2024 16:00");
            assertEquals(c.getClass(), AddDeadlineCommand.class);
        }  catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseWrongDeadlineCommandWrongLength() {
        try {
            Command c = Parser.parse("deadline homework");
            fail();
        }  catch (BarcusException e) {
            assertEquals("please include '/by' and deadline after it", e.getMessage());
        }
    }

    @Test
    public void testParseEventCommand() {
        try {
            Command c = Parser.parse("event make friends /from 22/08/2024 16:00 /to 03/09/2024 16:00");
            assertEquals(c.getClass(), AddEventCommand.class);
        }  catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseWrongEventCommandWrongLength() {
        try {
            Command c = Parser.parse("event cry");
            fail();
        }  catch (BarcusException e) {
            assertEquals("please include '/from' and '/to' as well as dates after each of those words",
                    e.getMessage());
        }
    }

    @Test
    public void testParseDeleteCommand() {
        try {
            Command c = Parser.parse("delete 2");
            assertEquals(c.getClass(), DeleteCommand.class);
        }  catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testParseDeleteCommandWrongLength() {
        try {
            Command c = Parser.parse("delete");
            fail();
        }  catch (BarcusException e) {
            assertEquals("please have an integer after 'delete'", e.getMessage());
        }
    }

    @Test
    public void testParseWrongDeleteCommandNoInteger() {
        try {
            Command c = Parser.parse("delete hehe");
            fail();
        }  catch (BarcusException e) {
            assertEquals("please have an integer after 'delete'", e.getMessage());
        }
    }
}
