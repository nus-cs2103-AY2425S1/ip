package jackson.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {

    /* instance variable to capture baos stream*/
    private ByteArrayOutputStream stream;

    /* Save old PrintStream to reset System.setOut here */
    private PrintStream old;

    @BeforeEach
    public void setup() {
        this.stream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(stream); // connect print stream to byte array output stream
        this.old = System.out; // save old stream
        System.setOut(ps); // redirect system out to print stream
    }

    @AfterEach
    public void teardown() {
        // reset system out back to previous print stream
        System.out.flush();
        System.setOut(this.old);
    }

    @Test
    public void printFormatGuide_validInput_correctFormatGuide() {
        Ui ui = new Ui();
        String prefix = "Wrong format leh...\r\nTry formatting your command as such:\r\n"; // prefix

        ui.printFormatGuide("bye");
        assertEquals(prefix + "bye", this.stream.toString().strip());
        this.stream.reset();

        ui.printFormatGuide("delete");
        assertEquals(prefix + "delete [index]", this.stream.toString().strip());
        this.stream.reset();

        ui.printFormatGuide("unmark");
        assertEquals(prefix + "unmark [index]", this.stream.toString().strip());
        this.stream.reset();

        ui.printFormatGuide("mark");
        assertEquals(prefix + "mark [index]", this.stream.toString().strip());
        this.stream.reset();

        ui.printFormatGuide("list");
        assertEquals(prefix + "list", this.stream.toString().strip());
        this.stream.reset();

        ui.printFormatGuide("find");
        assertEquals(prefix + "find [keywords]", this.stream.toString().strip());
        this.stream.reset();

        ui.printFormatGuide("event");
        assertEquals(prefix + """
                event [task-name] /from [start-date] /to [end-date]\r
                All dates must be in DD-MM-YYYY HH:MM format (HH:MM is optional)""", this.stream.toString().strip());
        this.stream.reset();

        ui.printFormatGuide("deadline");
        assertEquals(prefix + """
                deadline [task-name] /by [due-date]\r
                All dates must be in DD-MM-YYYY HH:MM format (HH:MM is optional)""", this.stream.toString().strip());
        this.stream.reset();

        ui.printFormatGuide("todo");
        assertEquals(prefix + "todo [task-name]", this.stream.toString().strip());
        this.stream.reset();
    }

    @Test
    public void printFormatGuide_invalidInput_genericErrorFormat() {
        Ui ui = new Ui();
        String prefix = "Wrong format leh...\r\nTry formatting your command as such:\r\n"; // prefix

        ui.printFormatGuide("list list");
        assertEquals(prefix + "Unknown error...", stream.toString().strip());
        stream.reset();

        ui.printFormatGuide("unmark 1");
        assertEquals(prefix + "Unknown error...", stream.toString().strip());
        stream.reset();

        ui.printFormatGuide("todo event");
        assertEquals(prefix + "Unknown error...", stream.toString().strip());
        stream.reset();

        ui.printFormatGuide("find keyword");
        assertEquals(prefix + "Unknown error...", stream.toString().strip());
        stream.reset();
    }
}
