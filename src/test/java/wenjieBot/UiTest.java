package wenjieBot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
public class UiTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testShowFarewell() {
        ui.showFarewell();
        String expectedOutput = "____________________________________________________________\r\n" +
                "Paiseh bro I zao liao, see you around ah bro.\r\n" +
                "____________________________________________________________\r\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testShowLine() {
        ui.showLine();
        String expectedOutput = "____________________________________________________________\r\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testShowError() {
        String errorMessage = "Error: Something went wrong!";
        ui.showError(errorMessage);
        String expectedOutput = "____________________________________________________________\r\n" +
                "Error: Something went wrong!\r\n" +
                "____________________________________________________________\r\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testReadCommand() {
        String input = "test command";
        Scanner scanner = new Scanner(input);
        String command = ui.readCommand(scanner);
        assertEquals("test command", command);
    }

}
