package terminator.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class CommandParserTest {

    @Test
    public void parse_validListPattern_success() throws TerminatorException {
        Command c = new CommandParser().parse("list");
        assertEquals("Listing current mission objectives:\n", c.execute(new ArrayList<>()));
    }

}
