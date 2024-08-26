package neko;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParseTodo() {
        assertEquals("[T][X] test", Parser.parseTask("T | 1 | test").toString());
    }

    @Test
    public void testParseDeadline() {
        assertEquals("[D][ ] iP (by: Thu, 29 Aug 2024 11:59pm)",
                Parser.parseTask("D | 0 | iP | Thu, 29 Aug 2024 11:59pm").toString());
    }

    @Test
    public void testParseEvent() {
        assertEquals("[E][ ] Interview with Jane Street (from: Mon, 26 Aug 2024 " +
                        "12:00pm to: Mon, 26 Aug 2024 2:00pm)",
                Parser.parseTask(
                        "E | 0 | Interview with Jane Street | Mon, 26 Aug 2024 12:00pm" +
                                " | Mon, 26 Aug 2024 2:00pm").toString());
    }

    @Test
    public void testParseCommand() {
        assertEquals("delete", Parser.parseCommand("delete 5"));
    }

    @Test
    public void testParseTime() {
        assertEquals("Sun, 25 Aug 2024 10:51pm",
                Parser.parseTime("2024-08-25T22:51")
                .format(DateTimeFormatter.ofPattern("eee, d MMM uuuu h:mma")));
    }

}
