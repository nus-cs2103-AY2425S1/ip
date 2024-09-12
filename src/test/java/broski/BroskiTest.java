package broski;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import broski.output.Broski;

public class BroskiTest {
    @Test
    public void start_checkGreeting_success(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        new Broski().start();
        assertEquals("_________________________________________\n"
                + "Wassup! I'm Broski!\n"
                + "What can I do for you bro?\n"
                + "_________________________________________\n", outputStream.toString());
    }
}
