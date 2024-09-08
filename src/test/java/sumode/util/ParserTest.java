package sumode.util;

import org.junit.jupiter.api.Test;
import sumode.exception.WrongSyntaxForCommandException;
import sumode.util.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {

    @Test
    public void TestNormalEvent() {
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
    public void TestFlipEvent() {
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
    public void TestEmptyStringEvent() {
        try {
            String[] parsed = Parser.parseEvent("nothing /to /from");
        } catch (Exception e) {
            fail("Exception is thrown when is it not supposed to be. Exception message below\n"
                    + e.getMessage());
        }
    }

}
