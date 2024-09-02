package tissue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tissue.parse.Parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ParserTest {

    @Test
    public void parseUntil_parses_correctly0() {
        String testString = "abc /by /l /to f kml";
        InputStream in = System.in;
        try {
            System.setIn(new ByteArrayInputStream(testString.getBytes()));
            assertEquals("abc /by /l ", new Parser().scanUntil("/to"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseUntil_parses_correctly1() {
        String testString = "abc /by /l /to f kml";
        InputStream in = System.in;
        try {
            System.setIn(new ByteArrayInputStream(testString.getBytes()));
            assertEquals("abc /by /l ", new Parser().scanUntil("/to"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseUntil_parses_correctly2() {
        String testString = "fgd 4455 66";
        InputStream in = System.in;
        try {
            System.setIn(new ByteArrayInputStream(testString.getBytes()));
            assertEquals("fgd 4455 ", new Parser().scanUntil("66"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseUntil_parses_correctly3() {
        String testString = "k20 * &&& $$ %    ";
        InputStream in = System.in;
        try {
            System.setIn(new ByteArrayInputStream(testString.getBytes()));
            assertEquals("k20 ", new Parser().scanUntil("*"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
