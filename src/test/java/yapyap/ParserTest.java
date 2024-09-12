package yapyap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseCommands_validCommands_correctEnumReturned() {
        Parser parser = new Parser();

        assertEquals(Commands.BYE, parser.parseCommand("bye"));
        assertEquals(Commands.LIST, parser.parseCommand("list"));
        assertEquals(Commands.TODO, parser.parseCommand("todo do ip"));
        assertEquals(Commands.DEADLINE, parser.parseCommand("deadline eat dinner /by 2024-12-02"));
        assertEquals(Commands.EVENT, parser.parseCommand("event game /from 2/12/2024 0800 "
                + "/to 2/12/2024 0900"));
        assertEquals(Commands.LIST, parser.parseCommand("list"));
        assertEquals(Commands.MARK, parser.parseCommand("mark 1"));
        assertEquals(Commands.UNMARK, parser.parseCommand("unmark 1"));
        assertEquals(Commands.DELETE, parser.parseCommand("delete 1"));
    }

    @Test
    public void parseCommands_invalidCommands_othersEnumReturned() {
        Parser parser = new Parser();

        assertEquals(Commands.OTHERS, parser.parseCommand("random command LOL"));
    }
}
