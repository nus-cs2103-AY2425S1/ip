package chatkaki;

import chatkaki.commands.*;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_validDeleteCommand_returnsCommandDelete() {
        Scanner scanner = new Scanner("delete 1");
        Command command = Parser.parse(scanner);
        assertTrue(command instanceof CommandDelete);
    }

    @Test
    public void parse_validTodoCommand_returnsCommandTodo() {
        Scanner scanner = new Scanner("todo read book");
        Command command = Parser.parse(scanner);
        assertTrue(command instanceof CommandTodo);
    }

    @Test
    public void parse_validDeadlineCommand_returnsCommandDeadline() {
        Scanner scanner = new Scanner("deadline return book /by 2/12/2019 1800");
        Command command = Parser.parse(scanner);
        assertTrue(command instanceof CommandDeadline);
    }

    @Test
    public void parse_validEventCommand_returnsCommandEvent() {
        Scanner scanner = new Scanner("event project meeting /at 2/12/2019 1800 to 2/12/2019 2000");
        Command command = Parser.parse(scanner);
        assertTrue(command instanceof CommandEvent);
    }

    @Test
    public void parse_validByeCommand_returnsCommandBye() {
        Scanner scanner = new Scanner("bye");
        Command command = Parser.parse(scanner);
        assertTrue(command instanceof CommandBye);
    }

    @Test
    public void parse_validMarkCommand_returnsCommandMark() {
        Scanner scanner = new Scanner("mark 1");
        Command command = Parser.parse(scanner);
        assertTrue(command instanceof CommandMark);
    }

    @Test
    public void parse_validUnmarkCommand_returnsCommandUnmark() {
        Scanner scanner = new Scanner("unmark 1");
        Command command = Parser.parse(scanner);
        assertTrue(command instanceof CommandUnmark);
    }

    @Test
    public void parse_validListCommand_returnsCommandList() {
        Scanner scanner = new Scanner("list");
        Command command = Parser.parse(scanner);
        assertTrue(command instanceof CommandList);
    }

    @Test
    public void parse_invalidCommand_returnsCommandUnknown() {
        Scanner scanner = new Scanner("invalid command");
        Command command = Parser.parse(scanner);
        assertTrue(command instanceof CommandUnknown);
    }
}