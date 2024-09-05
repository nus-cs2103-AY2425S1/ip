package JayTest.parser;

import jay.command.Command;
import jay.parser.InvalidDateException;
import jay.parser.InvalidTimeException;
import jay.parser.Parser;
import org.junit.jupiter.api.Test;
import jay.task.InvalidTaskException;
import jay.task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testParseTask() {
        try {
            Task task = Parser.parseTask(Task.Type.ToDo, new Command("todo read book"));
            assertEquals("[T][ ] read book", task.toString());

            task = Parser.parseTask(Task.Type.Deadline, new Command("deadline return book /by 12-12-2021"));
            assertEquals("[D][ ] return book (by: 12 Dec 2021)", task.toString());

            task = Parser.parseTask(Task.Type.Event, new Command("event project meeting /from 12-12-2021 1400 /to 1600"));
            assertEquals("[E][ ] project meeting (from: 12 Dec 2021 02:00 PM to: 04:00 PM)", task.toString());
        } catch (InvalidTaskException e) {
            fail();
        }
    }

    @Test
    public void testParseDate() {
        try {
            assertEquals("12 Dec 2021", Parser.convertDateToString(Parser.parseDate("12-12-2021")));
        } catch (InvalidDateException e) {
            fail();
        }
    }

    @Test
    public void testParseTime() {
        try {
            assertEquals("02:00 PM", Parser.convertTimeToString(Parser.parseTime("1400")));
        } catch (InvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void parseTime_invalidTime_exceptionThrown() {
        try {
            Parser.parseTime("2500");
            fail();
        } catch (InvalidTimeException e) {
            assertEquals("Invalid time format. Please use the format HHmm.", e.getMessage());
        }
    }
}
