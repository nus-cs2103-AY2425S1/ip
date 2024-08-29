package misc;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;


public class ParserTest {
    @Test
    public void parser_standardInput_splitInput() {
        String input = "todo apple";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(inputStream);

        Parser parser = new Parser();
        String[] result = parser.requestInput();

        String[] expected = {"todo", "apple"};
        assertArrayEquals(expected, result);
    }

    @Test
    public void parser_singleWordInput_blankSecondArrayPos() {
        String input = "todo";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(inputStream);

        Parser parser = new Parser();
        String[] result = parser.requestInput();

        String[] expected = {"todo", ""};
        assertArrayEquals(expected, result);
    }

    
}
