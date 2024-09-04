package dudu.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dudu.command.Command;
import dudu.command.CommandDeadline;
import dudu.command.CommandList;
import dudu.task.Deadline;

public class ParserTest {
    @Test
    public void testDeadlineCommand() {
        Command command = Parser.parse("deadline this /by 2024-08-30");
        Deadline task = new Deadline("this", LocalDate.parse("2024-08-30"));
        assertEquals(new CommandDeadline(task), command);
    }

    @Test
    public void testListCommand() {
        Command command = Parser.parse("list");
        assertEquals(new CommandList(), command);
    }
}
