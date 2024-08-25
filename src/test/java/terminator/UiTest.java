package terminator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    private final PrintStream standardOut = System.out;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

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

    private static final String ERR_MSG = """
            List command takes no arguments.
            
            Usage: list""";

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
        Ui ui = new Ui();
        String expectedGreetingMsg = HLINE + LOGO + "Device booted successfully. State your request.\n" + HLINE;
        ui.greet();
        assertEquals(expectedGreetingMsg, outputStreamCaptor.toString());
    }
}
