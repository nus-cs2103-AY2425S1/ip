package hamyo.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class UiTest {

    private final ByteArrayOutputStream systemOutput = new ByteArrayOutputStream();
    private final PrintStream originalOutput = System.out;
    public void setUpStreams() {
        System.setOut(new PrintStream(systemOutput));
    }
    public void restoreStreams() {
        System.setOut(originalOutput);
    }

    @Test
    public void testGreet() {
        Ui ui = new Ui();
        setUpStreams();

        ui.greet();
        assertEquals(
            "____________________________________________________________________________________________\n"
                + "$$   $$   $$$$    $$$$ $$$$   $$   $$  $$$$$$\n"
                + "$$   $$  $$  $$  $$  $$$  $$  $$   $$  $$  $$\n"
                + "$$$$$$$  $$$$$$  $$  $$$  $$  $$$$$$$  $$  $$\n"
                + "$$   $$  $$  $$  $$  $$$  $$       $$  $$  $$\n"
                + "$$   $$  $$  $$  $$  $$$  $$  $$$$$$   $$$$$$\n"
                + "\nAnnyeonghaseyo! Hamyo here!\nHow may I assist you today?\n"
                + "____________________________________________________________________________________________\n",
            systemOutput.toString());

        restoreStreams();
        Ui.resetResponse();
    }

    @Test
    public void testTerminate() {
        Ui ui = new Ui();
        setUpStreams();

        ui.terminate();
        assertEquals("Annyeong! Till we meet again. <3\n"
                + "____________________________________________________________________________________________\n",
            systemOutput.toString());

        restoreStreams();
        Ui.resetResponse();
    }

    @Test
    public void testPrintException() {
        HamyoException e = new HamyoException("Testing an exception.");
        setUpStreams();

        Ui.setStringException(e);
        System.out.println(Ui.getResponse());
        assertEquals(e.toString() + "\n__________________________________________"
                + "__________________________________________________\n\n",
            systemOutput.toString());

        restoreStreams();
        Ui.resetResponse();
    }
}
