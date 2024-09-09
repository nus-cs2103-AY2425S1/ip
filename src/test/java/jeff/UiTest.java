package jeff;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class UiTest {

    @Test
    public void showWelcome_success() {
        Ui ui = new Ui();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.showWelcome();
        String output = outContent.toString();
        assertTrue(output.contains("Hello there"));

        System.setOut(System.out); // Reset the output to console
    }

    @Test
    public void showError_success() {
        Ui ui = new Ui();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.showError("This is an error");
        String output = outContent.toString();
        assertTrue(output.contains("This is an error"));

        System.setOut(System.out); // Reset the output to console
    }

    @Test
    public void showMessage_success() {
        Ui ui = new Ui();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.showMessage("This is a message");
        String output = outContent.toString();
        assertTrue(output.contains("This is a message"));

        System.setOut(System.out); // Reset the output to console
    }
}
