package Hamyo.Misc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UITest {

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
        UI ui = new UI();
        setUpStreams();

        ui.greet();
        assertEquals(
            "____________________________________________________________________________________________\n" +
                "$$   $$   $$$$    $$$$ $$$$   $$   $$  $$$$$$\n" +
                "$$   $$  $$  $$  $$  $$$  $$  $$   $$  $$  $$\n" +
                "$$$$$$$  $$$$$$  $$  $$$  $$  $$$$$$$  $$  $$\n" +
                "$$   $$  $$  $$  $$  $$$  $$       $$  $$  $$\n" +
                "$$   $$  $$  $$  $$  $$$  $$  $$$$$$   $$$$$$\n" +
                "\nAnnyeonghaseyo! Hamyo here!\nHow may I assist you today?\n" +
                "____________________________________________________________________________________________\n",
            systemOutput.toString());

        restoreStreams();
        }

    @Test
    public void testTerminate() {
        UI ui = new UI();
        setUpStreams();

        ui.terminate();
        assertEquals("Annyeong! Till we meet again. <3\n" +
                "____________________________________________________________________________________________\n",
            systemOutput.toString());

        restoreStreams();
        }

    @Test
    public void testPrintException() {
        HamyoException e = new HamyoException("Testing an exception.");
        setUpStreams();

        UI.printException(e);
        assertEquals(e.toString() + "\n__________________________________________" +
                "__________________________________________________\n",
            systemOutput.toString());

        restoreStreams();
    }
}
