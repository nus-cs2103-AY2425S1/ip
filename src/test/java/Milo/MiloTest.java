package Milo;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MiloTest {
    @Test
    public void testGreetingAndExit() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        // Redirect System.out to capture printed output
        System.setOut(new PrintStream(outputStreamCaptor));
        // Simulate user input with "bye" to immediately exit
        System.setIn(new ByteArrayInputStream("bye\n".getBytes()));
        // Run the Milo application
        Milo milo = new Milo();
        milo.run();

        String expectedOutput = """
                ____________________________________________________________
                Hello! I'm Milo.Milo
                What can I do for you?
                  ╱|、
                (˚ˎ 。7 \s
                 |、˜〵         \s
                 じしˍ,)ノ
                ____________________________________________________________
                ____________________________________________________________
                Bye. Hope to see you again soon!
                 ∧,,,∧
                ( ̳• · •̳)
                /    づ♡
                ____________________________________________________________
                """;

        // Verify that the greeting and exit messages were printed
        assertEquals(expectedOutput, outputStreamCaptor.toString().replace("\r", ""));
    }
}