package taskalyn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ParserTest {

    @Test
    public void testParseByeCommand() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        System.setIn(new ByteArrayInputStream("bye\n".getBytes()));

        Taskalyn.main(null);
        System.setOut(System.out);
        System.setIn(System.in);

        String expectedOutput = "    ____________________________________________________________\n" +
                "    Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        assertEquals(expectedOutput.trim(), out.toString().trim());
    }
}
