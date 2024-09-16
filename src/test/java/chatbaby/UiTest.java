package chatbaby;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class UiTest {

    @Test
    public void testGreet() {
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
