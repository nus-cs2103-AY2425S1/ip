package buddybot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for Parser class
 */
public class ParseTest {
    /**
     *Test if parser gives the correct output
     * @throws BuddyBotException
     */
    @Test
    public void testCommand() throws BuddyBotException {
        assertEquals(Commands.valueOf("TODO"), Parser.parseCmd("todo"));
    }

}
