package victor.parser;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import victor.commands.Command;
import victor.commands.DeadlineCommand;
import victor.commands.EventCommand;
import victor.commands.MarkCommand;
import victor.commands.ToDoCommand;

public class ParserTest {
    @Test
    public void parseInput_toDo_toDoReturn() {
        Parser parser = new Parser();
        ToDoCommand comm = (ToDoCommand) parser.parseInput("todo test event");
        ToDoCommand newComm = new ToDoCommand(new String[] {"todo", "test", "event"});
        assertAll("Verify parsed to-do is the same", () -> assertEquals(comm.getToDo(), newComm.getToDo()), ()
                -> assertEquals(comm.getTaskList(), newComm.getTaskList()), ()
                -> assertArrayEquals(comm.getAdditionalInput(), newComm.getAdditionalInput()));
    }

    @Test
    public void parseInput_noNameDeadline_blankDeadline() {
        Parser parser = new Parser();
        DeadlineCommand comm = (DeadlineCommand) parser.parseInput("deadline");
        DeadlineCommand newComm = new DeadlineCommand(new String[] {"deadline"});
        assertAll("Verify that empty input for deadline is same", ()
                -> assertEquals(comm.getDeadline(), newComm.getDeadline()), ()
                -> assertArrayEquals(comm.getAdditionalInput(), newComm.getAdditionalInput()), ()
                -> assertEquals(comm.getTaskList(), newComm.getTaskList()));
    }

    @Test
    public void parseInput_eventInput_eventCommand() {
        Parser parser = new Parser();
        // must type case to use getEvent() method
        EventCommand comm = (EventCommand) parser.parseInput("event test /from 2023-02-11 /to 2023-03-01");
        EventCommand newComm = new EventCommand(new String[] {
            "event", "test", "/from", "2023-02-11", "/to", "2023-03-01"});
        assertAll("Verify parsed event is the same", () -> assertEquals(comm.getEvent(), newComm.getEvent()), ()
                -> assertEquals(comm.getTaskList(), newComm.getTaskList()), ()
                -> assertArrayEquals(comm.getAdditionalInput(), newComm.getAdditionalInput()));
    }

    @Test
    public void parseInput_noInput_blankDefaultCommand() {
        Parser parser = new Parser();
        Command comm = parser.parseInput("");
        Command newComm = new DeadlineCommand(new String[] {""});
        assertAll("Verify that empty input to parser gives blank output", ()
                -> assertArrayEquals(comm.getAdditionalInput(), newComm.getAdditionalInput()), ()
                -> assertEquals(comm.getTaskList(), newComm.getTaskList()));
    }

    @Test
    public void parseInput_markInput_markCommand() {
        Parser parser = new Parser();
        // must type case to use getEvent() method
        MarkCommand comm = (MarkCommand) parser.parseInput("mark 2");
        MarkCommand newComm = new MarkCommand(new String[] {"mark", "2"});
        assertAll("Verify parsed event is the same", () -> assertEquals(
                comm.getNumber(), newComm.getNumber()), ()
                -> assertEquals(comm.getTaskList(), newComm.getTaskList()), ()
                -> assertArrayEquals(comm.getAdditionalInput(), newComm.getAdditionalInput()));
    }

    @Test
    public void parseInput_exitInput_exitCommand() {
        Parser parser = new Parser();
        Command comm = parser.parseInput("exit");
        Command newComm = new DeadlineCommand(new String[] {"exit"});
        assertAll("Verify that exit input to parser gives exit command", ()
                -> assertArrayEquals(comm.getAdditionalInput(), newComm.getAdditionalInput()), ()
                -> assertEquals(comm.getTaskList(), newComm.getTaskList()), ()
                -> assertTrue(comm.isExit()));
    }

    @Test
    public void parseInput_byeInput_exitCommand() {
        Parser parser = new Parser();
        Command comm = parser.parseInput("bye");
        Command newComm = new DeadlineCommand(new String[] {"bye"});
        assertAll("Verify that bye input to parser gives exit command", ()
                -> assertArrayEquals(comm.getAdditionalInput(), newComm.getAdditionalInput()), ()
                -> assertEquals(comm.getTaskList(), newComm.getTaskList()), ()
                -> assertTrue(comm.isExit()));
    }
}
