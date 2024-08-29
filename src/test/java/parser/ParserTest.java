package parser;

import command.Command;
import org.junit.jupiter.api.Test;
import task.InvalidTaskException;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testParseTask() {
        try {
            Task task = Parser.parseTask(Task.TYPE.TODO, new Command("todo read book"));
            assertEquals("[T][ ] read book", task.toString());

            task = Parser.parseTask(Task.TYPE.DEADLINE, new Command("deadline return book /by 12-12-2021"));
            assertEquals("[D][ ] return book (by: 12 Dec 2021)", task.toString());

            task = Parser.parseTask(Task.TYPE.EVENT, new Command("event project meeting /from 12-12-2021 1400 /to 1600"));
            assertEquals("[E][ ] project meeting (from: 12 Dec 2021 02:00 PM to: 04:00 PM)", task.toString());
        } catch (InvalidTaskException e) {
            fail();
        }
    }

    @Test
    public void testParseDate() {
        try {
            assertEquals("12 Dec 2021", Parser.dateToString(Parser.parseDate("12-12-2021")));
        } catch (InvalidDateException e) {
            fail();
        }
    }

    @Test
    public void testParseTime() {
        try {
            assertEquals("02:00 PM", Parser.timeToString(Parser.parseTime("1400")));
        } catch (InvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void testParseTime_invalidTime_exceptionThrown() {
        try {
            Parser.parseTime("2500");
            fail();
        } catch (InvalidTimeException e) {
            assertEquals("Invalid time format. Please use the format HHmm.", e.getMessage());
        }
    }
}
