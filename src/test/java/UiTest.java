import Hoodini.Storage;
import Hoodini.Input;
import Hoodini.Ui;
import Hoodini.ToDo;
import Hoodini.Deadline;
import Hoodini.Event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class UiTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testShowWelcome() {
        outputStreamCaptor.reset();
        Ui ui = new Ui();
        ui.showWelcome();
        assertEquals("Hello! I'm Hoodini\nHow may I assist you?\n", outputStreamCaptor.toString());
    }

    @Test
    public void testShowGoodbye() {
        outputStreamCaptor.reset();
        Ui ui = new Ui();
        ui.showGoodbye();
        assertEquals("Bye! Come back to Hoodini soon!\n", outputStreamCaptor.toString());
    }

    @Test
    public void testNoFileFound() {
        outputStreamCaptor.reset();
        Ui ui = new Ui();
        ui.noFileFound();
        assertEquals("File not found, I will create a new file for you!\n", outputStreamCaptor.toString());
    }


}
