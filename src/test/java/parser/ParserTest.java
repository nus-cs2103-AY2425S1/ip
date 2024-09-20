package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import enumeration.CommandType;
import exception.InvalidInputFormatException;
import task.Task;

public class ParserTest {
    @Test
    public void testCreateTaskFromInputEvent() throws InvalidInputFormatException {
        Task e = Parser.createTaskFromInput("event natlan exploration /from 1900-01-01 /to 2025-01-01");
        Task e1 = Parser.createTaskFromInput("event natlan exploration/from1900-01-01/to2025-01-01");
        Task e2 = Parser.createTaskFromInput("event natlan exploration/fromtues/towed");


        e1.markAsDone();
        assertEquals("[E][ ] natlan exploration (from: 1900-01-01 to: 2025-01-01)", e.toRawString());
        assertEquals("[E][X] natlan exploration (from: 1900-01-01 to: 2025-01-01)", e1.toRawString());
        assertEquals("[E][X] natlan exploration (from: Jan 1 1900 to: Jan 1 2025)", e1.toString());
        assertEquals("[E][ ] natlan exploration (from: tues to: wed)", e2.toString());
    }

    @Test
    public void testCreateTaskFromInputDeadline() throws InvalidInputFormatException {

        Task d = Parser.createTaskFromInput("deadline CS2103T iP /by 2024-08-30");
        Task d1 = Parser.createTaskFromInput("deadline CS2103T iP/by2024-08-30");
        Task d2 = Parser.createTaskFromInput("deadline CS2103T iP/byFriday");

        d1.markAsDone();
        assertEquals("[D][ ] CS2103T iP (by: 2024-08-30)", d.toRawString());
        assertEquals("[D][X] CS2103T iP (by: 2024-08-30)", d1.toRawString());
        assertEquals("[D][X] CS2103T iP (by: Aug 30 2024)", d1.toString());
        assertEquals("[D][ ] CS2103T iP (by: Friday)", d2.toString());
    }

    @Test
    public void testCreateTaskFromInputTodo() throws InvalidInputFormatException {
        Task t = Parser.createTaskFromInput("todo CS2103T iP ");
        Task t1 = Parser.createTaskFromInput("todo CS2103T iP");

        t1.markAsDone();
        assertEquals("[T][ ] CS2103T iP", t.toRawString());
        assertEquals("[T][X] CS2103T iP", t1.toString());

    }

    @Test
    public void testCreateCommandFromInput() throws InvalidInputFormatException {
        assertEquals(CommandType.BYE, Parser.createCommandFromInput("bye  "));
        assertEquals(CommandType.LIST, Parser.createCommandFromInput("list"));
        assertEquals(CommandType.MARK, Parser.createCommandFromInput("mark 1"));
        assertEquals(CommandType.UNMARK, Parser.createCommandFromInput("unmark 2"));
        assertEquals(CommandType.TODO, Parser.createCommandFromInput("todo hi"));
        assertEquals(CommandType.DEADLINE, Parser.createCommandFromInput("deadline"));
        assertEquals(CommandType.EVENT, Parser.createCommandFromInput("event  "));
        assertEquals(CommandType.DELETE, Parser.createCommandFromInput("delete  "));

    }
}
