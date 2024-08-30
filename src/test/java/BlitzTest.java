import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlitzTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void actionBasedOnInputTest1() {
        Blitz newB = new Blitz();
        newB.actionBasedOnInput("");
        assertEquals("", outputStreamCaptor.toString().trim());
    }

    @Test
    public void actionBasedOnInputTest2() {
        Blitz newB = new Blitz();
        newB.actionBasedOnInput("mark");
        assertEquals("A valid index has not been given!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void actionBasedOnInputTest3() {
        Blitz newB = new Blitz();
        newB.actionBasedOnInput("mark one");
        assertEquals("Come on, that is not a number bro. Don't worry, try again.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void actionBasedOnInputTest4() {
        Blitz newB = new Blitz();
        newB.actionBasedOnInput("delete");
        assertEquals("A valid index has not been given!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void actionBasedOnInputTest5() {
        Blitz newB = new Blitz();
        newB.actionBasedOnInput("delete one");
        assertEquals("Come on, that is not a number bro. Don't worry, try again.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void actionBasedOnInputTest6() {
        Blitz newB = new Blitz();
        newB.actionBasedOnInput("delete 100000");
        assertEquals("A valid index has not been given!", outputStreamCaptor.toString().trim());
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
