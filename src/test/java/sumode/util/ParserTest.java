package sumode.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import sumode.exception.WrongSyntaxForCommandException;




public class ParserTest {

    @Test
    public void testNormalEvent() {
        try {
            String[] parsed = Parser.parseEvent("parktour         /from now /to day");
            assertEquals("parktour", parsed[0]);
            assertEquals("now", parsed[1]);
            assertEquals("day", parsed[2]);
        } catch (WrongSyntaxForCommandException e) {
            fail("Exception is thrown when is it not supposed to be. Exception message below\n"
                    + e.getMessage());
        }
    }

    @Test
    public void testFlipEvent() {
        try {
            String[] parsed = Parser.parseEvent("parktour         /to day /from     now  ");
            assertEquals("parktour", parsed[0]);
            assertEquals("now", parsed[1]);
            assertEquals("day", parsed[2]);
        } catch (WrongSyntaxForCommandException e) {
            fail("Exception is thrown when is it not supposed to be. Exception message below\n"
                    + e.getMessage());
        }
    }

    @Test
    public void testEmptyStringEvent() {
        Exception exception = assertThrows(WrongSyntaxForCommandException.class, () -> {
            String[] parsed = Parser.parseEvent("nothing /to /from");
        });
        assertEquals("""
                        Sumo understood your command but dunno what you want! Please utilise "event" the correct way.
                        The correct syntax is event <task name> /from <date> /to <date>.""",
                exception.getMessage());
    }

}
