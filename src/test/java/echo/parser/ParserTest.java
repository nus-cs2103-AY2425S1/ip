package echo.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import echo.task.Event;
import echo.task.Task;

public class ParserTest {
    @Test
    public void testParseInputFromTextFile() {
        Parser parser = new Parser();
        String input = "Event | 0 | work | /from 11-11-1111 1111 /to 12-12-1212 1212";
        Task actual = parser.parseInputFromTextFile(input);
        String[] eventArray = { "work", "11-11-1111 1111", "12-12-1212 1212" };
        Event expected = new Event(eventArray);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testParseInputFromTextFile_exceptionThrown() {
        try {
            Parser parser = new Parser();
            String input = "Eventssssss | 0 | work | /from 11-11-1111 1111 /to 12-12-1212 1212";
            Task actual = parser.parseInputFromTextFile(input);
            String[] eventArray = { "work", "11-11-1111 1111", "12-12-1212 1212" };
            Event expected = new Event(eventArray);
            assertEquals(expected, actual);
            fail();
        } catch (Exception e) {
            assertEquals("File has been corrupted, invalid syntax stored", e.getMessage());
        }
    }
}
