import Mario.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void testBetweenLines() {
        Ui ui = new Ui();

        String content = "Hello world!";
        String expected = """
                -----------------------------------------------
                     Hello world!
                -----------------------------------------------""";
        assertEquals(expected, ui.betweenLines(content));

    }

    @Test
    public void testGetLine() {
        Ui ui = new Ui();

        String line = "-----------------------------------------------\n";
        assertEquals(line, ui.getLine());

    }
}
