package greetbot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseCommand_Test() {
        String command_One = "toDo test command";
        String[] parsedResults_One = Parser.parseCommand(command_One);

        assertEquals("todo".toUpperCase(), parsedResults_One[0]);
        assertEquals("test command", parsedResults_One[1]);

        String command_Two = "someRandomNonsense 114514";
        String[] parsedResults_Two = Parser.parseCommand(command_Two);

        assertEquals("someRandomNonsense".toUpperCase(), parsedResults_Two[0]);
        assertEquals("114514", parsedResults_Two[1]);
    }

    @Test
    public void parseEvent_Test() {
        String section = "some test /from Aug 9th /to Aug 21st";
        String[] parsedResults = Parser.parseEvent(section);

        assertEquals("some test", parsedResults[0]);
        assertEquals("Aug 9th", parsedResults[1]);
        assertEquals("Aug 21st", parsedResults[2]);
    }

}