package wenjigglybot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseTask_parseTodoTask_returnToDoTask() {
        Task parsed = Parser.parseTask("1. [T][ ] testtask");
        assertEquals("[T][ ] testtask", parsed.toString());
    }
}