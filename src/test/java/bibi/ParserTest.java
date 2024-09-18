package bibi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bibi.task.TaskList;

public class ParserTest {
    @Test
    public void parseUnknownCommand_getUnknownCommandText() {
        String output = Parser.parseCommand("test").execute(new TaskList(), new Processor(), new Storage(null));

        assertEquals(String.format("test is an unknown command%n"), output);
    }
}
