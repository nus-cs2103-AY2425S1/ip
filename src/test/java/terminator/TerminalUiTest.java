package terminator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import terminator.cli.TerminalUi;

public class TerminalUiTest {

    private static final String HLINE = "____________________________________________________________\n";

    private static final String LOGO =
            """
                               <((((((\\\\\\
                               /      . }\\
                               ;--..--._|}
            (\\                 '--/\\--'  )
             \\\\                | '-'  :'|
              \\\\               . -==- .-|
               \\\\               \\.__.'   \\--._
               [\\\\          __.--|       //  _/'--.
               \\ \\\\       .'-._ ('-----'/ __/      \\
                \\ \\\\     /   __>|      | '--.       |
                 \\ \\\\   |   \\   |     /    /       /
                  \\ '\\ /     \\  |     |  _/       /
                   \\  \\       \\ |     | /        /
                    \\  \\      \\        /
            """;

    private final PrintStream standardOut = System.out;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void greet_shouldPrintCorrectGreetingMessage() {
        TerminalUi terminalUi = new TerminalUi();
        String expectedGreetingMsg = HLINE + LOGO + "Device booted successfully. State your request.\n" + HLINE;
        terminalUi.greet();
        assertEquals(expectedGreetingMsg, outputStreamCaptor.toString());
    }
}
