package victor.parser;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import victor.commands.Command;
import victor.commands.DeadlineCommand;
import victor.commands.ToDoCommand;

public class ParserTest {
    @Test
    public void parseBasicTest() {
        Parser parser = new Parser();
        ToDoCommand comm = (ToDoCommand) parser.parseInput("todo test event");
        ToDoCommand newComm = new ToDoCommand(new String[] {"todo", "test", "event"});
        assertAll("Verify parsed to-do is the same", () -> assertEquals(comm.getToDo(), newComm.getToDo()), ()
                -> assertEquals(comm.getTaskList(), newComm.getTaskList()), ()
                -> assertArrayEquals(comm.getAdditionalInput(), newComm.getAdditionalInput()));
    }

    @Test
    public void parseNoNameDeadlineTest() {
        Parser parser = new Parser();
        DeadlineCommand comm = (DeadlineCommand) parser.parseInput("deadline");
        DeadlineCommand newComm = new DeadlineCommand(new String[] {"deadline"});
        assertAll("Verify that empty input for deadline is same", ()
                -> assertEquals(comm.getDeadline(), newComm.getDeadline()), ()
                -> assertArrayEquals(comm.getAdditionalInput(), newComm.getAdditionalInput()), ()
                -> assertEquals(comm.getTaskList(), newComm.getTaskList()));
    }

    @Test
    public void parseNoInputTest() {
        Parser parser = new Parser();
        Command comm = parser.parseInput("");
        Command newComm = new DeadlineCommand(new String[] {""});
        assertAll("Verify that empty input to parser gives blank output", ()
                -> assertArrayEquals(comm.getAdditionalInput(), newComm.getAdditionalInput()), ()
                -> assertEquals(comm.getTaskList(), newComm.getTaskList()));
    }
}
