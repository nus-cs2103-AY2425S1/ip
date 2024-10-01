package garfield.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import garfield.commands.AddCommand;
import garfield.commands.Command;
import garfield.commands.DeleteCommand;
import garfield.commands.ExitCommand;
import garfield.commands.FindCommand;
import garfield.commands.ListCommand;
import garfield.commands.MarkCommand;
import garfield.commands.UnmarkCommand;
import garfield.exceptions.GarfieldException;

class ParserTest {

    @Test
    void testParse_byeCommand() throws GarfieldException {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command, "Command should be an ExitCommand");
    }

    @Test
    void testParse_listCommand() throws GarfieldException {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command, "Command should be a ListCommand");
    }

    @Test
    void testParse_markCommand() throws GarfieldException {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command, "Command should be a MarkCommand");
    }

    @Test
    void testParse_unmarkCommand() throws GarfieldException {
        Command command = Parser.parse("unmark 2");
        assertInstanceOf(UnmarkCommand.class, command, "Command should be an UnmarkCommand");
    }

    @Test
    void testParse_deleteCommand() throws GarfieldException {
        Command command = Parser.parse("delete 3");
        assertInstanceOf(DeleteCommand.class, command, "Command should be a DeleteCommand");
    }

    @Test
    void testParse_findCommand() throws GarfieldException {
        Command command = Parser.parse("find keyword");
        assertInstanceOf(FindCommand.class, command, "Command should be a FindCommand");
    }

    @Test
    void testParse_todoCommand() throws GarfieldException {
        Command command = Parser.parse("todo read a book");
        assertInstanceOf(AddCommand.class, command, "Command should be an AddCommand");
    }

    @Test
    void testParse_deadlineCommand() throws GarfieldException {
        Command command = Parser.parse("deadline submit assignment /by 2024-09-01 12:00");
        assertInstanceOf(AddCommand.class, command, "Command should be an AddCommand");
    }

    @Test
    void testParse_eventCommand() throws GarfieldException {
        Command command = Parser.parse("event project meeting /from 2024-12-01 12:00 /to 2024-12-01 13:00");
        assertInstanceOf(AddCommand.class, command, "Command should be an AddCommand");
    }

    @Test
    void testParse_invalidCommand_throwsGarfieldException() {
        Exception exception = assertThrows(GarfieldException.class, () -> Parser.parse("invalidcommand"));
        assertEquals("invalidcommand? I'm not sure what that means.", exception.getMessage());
    }

    @Test
    void testParse_invalidMarkCommand_throwsGarfieldException() {
        Exception exception = assertThrows(GarfieldException.class, () -> Parser.parse("mark abc"));
        assertEquals("abc is not a number!\n\nCorrect Usage: mark <task id>", exception.getMessage());
    }

    @Test
    void testParse_invalidDeleteCommand_throwsGarfieldException() {
        Exception exception = assertThrows(GarfieldException.class, () -> Parser.parse("delete"));
        assertEquals("No integers after the command to select task(s)!"
                + "\n\nCorrect Usage: delete <task id>", exception.getMessage());
    }

    @Test
    void testParse_invalidDeadlineFormat_throwsGarfieldException() {
        Exception exception = assertThrows(GarfieldException.class, () -> Parser.parse(
                "deadline project /by 2024/12/01 12:00"));
        assertEquals("Make sure your dates are valid and in the yyyy-MM-dd HH:mm (24h) format!\n\n"
                + "Correct Usage: deadline <task description> /by yyyy-MM-dd HH:mm", exception.getMessage());
    }

    @Test
    void testParse_eventMissingToFlag_throwsGarfieldException() {
        Exception exception = assertThrows(GarfieldException.class, () -> Parser.parse(
                "event project /from 2024-12-01 12:00"));
        assertEquals("You are missing a description or the '/from' and '/to' flags!\n\n"
                + "Correct Usage: event <task description> /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm",
                exception.getMessage());
    }
}
