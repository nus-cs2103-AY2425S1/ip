package chatkaki;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class UiTest {

    @Test
    public void printMessage_validMessage_printsCorrectly() {
        String message = "Hello, World!";
        String expectedOutput = "\n____________________________________________________________\n Hello, World!\n____________________________________________________________\n\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui.printMessage(message);

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printMessage_emptyMessage_printsCorrectly() {
        String message = "";
        String expectedOutput = "\n____________________________________________________________\n \n____________________________________________________________\n\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui.printMessage(message);

        assertEquals(expectedOutput, outContent.toString());
    }
}