package chatbaby;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.Normalizer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void greetTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Ui ui = new Ui();
        ui.greet();

        String expectedOutput = "Hello! I'm ChatBaby\n"
                + "What can I do for you?\n";

        assertEquals(expectedOutput.replace("\r\n", "\n"),
                outputStream.toString().replace("\r\n", "\n"));
    }
}
