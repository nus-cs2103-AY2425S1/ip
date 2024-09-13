package milo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import milo.ui.Ui;



class UiTest {
    @Test
    public void testGreetUser() {
        Ui ui = new Ui();
        ui.greetUser();
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String expectedOutput = """
                ____________________________________________________________
                Hello! I'm Milo.Milo
                What can I do for you?
                  ╱|、
                (˚ˎ 。7 \s
                 |、˜〵         \s
                 じしˍ,)ノ
                ____________________________________________________________
                """;
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}
