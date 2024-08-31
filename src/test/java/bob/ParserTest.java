package bob;

import bob.command.*;
import bob.exceptions.EmptyArgumentException;
import bob.exceptions.InvalidInputException;
import bob.exceptions.InvalidTaskNumberException;
import bob.exceptions.MissingArgumentException;
import bob.tasks.Deadline;
import bob.tasks.EventTask;
import bob.tasks.ToDo;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void ParseCommandTest() throws InvalidInputException, MissingArgumentException, InvalidTaskNumberException, EmptyArgumentException {
        assertInstanceOf(ByeCommand.class, Parser.parseCommand("bye"));
        assertInstanceOf(ListCommand.class, Parser.parseCommand("list"));
        assertInstanceOf(MarkCommand.class, Parser.parseCommand("mark 0"));
        assertInstanceOf(UnmarkCommand.class, Parser.parseCommand("unmark 0"));
        assertInstanceOf(AddTaskCommand.class, Parser.parseCommand("todo hello"));
        assertInstanceOf(AddTaskCommand.class, Parser.parseCommand("deadline hello /by 19/12/2001"));
        assertInstanceOf(AddTaskCommand.class, Parser.parseCommand("event hello /from 19/02/2001 /to 20/02/2001"));
        assertInstanceOf(DeleteCommand.class, Parser.parseCommand("delete 10"));
        assertThrows(InvalidInputException.class, () -> Parser.parseCommand("RANDOMINPUT"));
    }

    @Test
    public void ParseCommandWithInvalidInputTest() {
        assertThrows(InvalidInputException.class, () -> Parser.parseCommand("RANDOMINPUT"));
    }

    @Test
    public void NewToDoTest() throws EmptyArgumentException {
        assertInstanceOf(ToDo.class, Parser.newToDo("Hello"));
        assertThrows(EmptyArgumentException.class, () -> Parser.newToDo(""));
    }

    @Test
    public void NewToDoTestWithInvalidInput() {
        assertThrows(EmptyArgumentException.class, () -> Parser.newToDo(""));
    }

    @Test
    public void NewDeadlineTest() throws MissingArgumentException, EmptyArgumentException {
        assertInstanceOf(Deadline.class, Parser.newDeadline("TEST /by 19/12/2001 1800"));
        assertInstanceOf(Deadline.class, Parser.newDeadline("TEST /by 19/12/2001"));
    }

    @Test
    public void NewDeadlineTestWithInvalidInput() {
        assertThrows(DateTimeParseException.class, () -> Parser.newDeadline("TEST /by 19/20/2001"));
        assertThrows(DateTimeParseException.class, () -> Parser.newDeadline("TEST /by 19/20/2001 1800"));
        assertThrows(DateTimeParseException.class, () -> Parser.newDeadline("TEST /by 19-12-2001"));
        assertThrows(DateTimeParseException.class, () -> Parser.newDeadline("TEST /by 19/12/2001 1860"));

        assertThrows(EmptyArgumentException.class, () -> Parser.newDeadline("/by 19/12/2001"));
        assertThrows(EmptyArgumentException.class, () -> Parser.newDeadline("hello /by"));

        assertThrows(MissingArgumentException.class, () -> Parser.newDeadline("hello"));
    }

    @Test
    public void NewEventTaskTest() throws MissingArgumentException, EmptyArgumentException {
        assertInstanceOf(EventTask.class, Parser.newEvent("TEST /from 19/12/2001 /to 19/12/2001"));
        assertInstanceOf(EventTask.class, Parser.newEvent("TEST /from 19/12/2001 /to 19/12/2001 2000"));
        assertInstanceOf(EventTask.class, Parser.newEvent("TEST /from 19/12/2001 1800 /to 19/12/2001"));
        assertInstanceOf(EventTask.class, Parser.newEvent("TEST /from 19/12/2001 1800 /to 19/12/2001 2000"));
    }

    @Test
    public void NewEventTaskWithInvalidInputTest() {
        assertThrows(DateTimeParseException.class, () -> Parser.newEvent("TEST /from 19/13/2001 /to 19/12/2001"));
        assertThrows(DateTimeParseException.class, () -> Parser.newEvent("TEST /from 19/12/2001 /to 19/13/2001"));
        assertThrows(DateTimeParseException.class, () -> Parser.newEvent("TEST /from 19/12/2001 2500 /to 19/12/2001"));
        assertThrows(DateTimeParseException.class, () -> Parser.newEvent("TEST /from 19/12/2001 /to 19/12/2001 18000"));
        assertThrows(DateTimeParseException.class, () -> Parser.newEvent("TEST /from 19-12-2001 /to 19/12/2001"));
        assertThrows(DateTimeParseException.class, () -> Parser.newEvent("TEST /from 19/12/2001 /to 19-12-2001"));

        assertThrows(EmptyArgumentException.class, () -> Parser.newEvent("/from 19/12/2001 /to 19/12/2001"));
        assertThrows(EmptyArgumentException.class, () -> Parser.newEvent("TEST /from /to 19/12/2001"));
        assertThrows(EmptyArgumentException.class, () -> Parser.newEvent("TEST /from 19/12/2001 /to"));

        assertThrows(MissingArgumentException.class, () -> Parser.newEvent("TEST"));
        assertThrows(MissingArgumentException.class, () -> Parser.newEvent("TEST /from 19/12/2001"));
        assertThrows(MissingArgumentException.class, () -> Parser.newEvent("TEST /to 19/12/2001"));
        assertThrows(MissingArgumentException.class, () -> Parser.newEvent("TEST /from 19/12/2001 19/12/2001 1800"));
    }
}
