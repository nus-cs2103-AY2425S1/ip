import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hoodini.Ui;

public class UiTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }



    @Test
    public void testShowGoodbye() {
        outputStreamCaptor.reset();
        Ui ui = new UiStub();

        assertEquals("Bye! Come back to Hoodini soon!", ui.showGoodbye());
    }

    @Test
    public void testNoFileFound() {

        Ui ui = new UiStub();

        assertEquals("File not found, I will create a new file for you!", ui.noFileFound());
    }


}
