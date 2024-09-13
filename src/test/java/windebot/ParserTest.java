package windebot;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ParserTest {
    private static final String WINDE_TEST = "./src/main/java/WindeTest.txt";

    @Test
    public void parseTest() {
        Parser parser = null;
        Reminder reminder = new Reminder();
        Ui ui = new Ui();
        History history = new History(WINDE_TEST);
        try {
            assertTrue(parser.parse("todo something").execute("todo something", reminder, ui, history));
            assertTrue(parser.parse("todo").execute("todo", reminder, ui, history));
            assertTrue(parser.parse("deadline something /by 23/04/9000 23:59")
                    .execute("deadline something /by 23/04/9000 23:59", reminder, ui, history));
            assertTrue(parser.parse("event something /from 23/04/9000 23:59 /to 23/12/9000 23:59")
                    .execute("event something /from 23/04/9000 23:59 /to 23/12/9000 23:59", reminder, ui, history));
            assertTrue(parser.parse("hello").execute("hello", reminder, ui, history));
            assertFalse(parser.parse("bye").execute("bye", reminder, ui, history));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
