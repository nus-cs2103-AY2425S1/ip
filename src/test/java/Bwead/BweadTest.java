package Bwead;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BweadTest {

    @Test
    public void byeInputRunTest() throws IOException {
        String userInput = String.format("todo return book%sbye",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Bye. Hope to see you again soon!";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        Bwead.main(null);

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        assertEquals(expected,actual);
    }

    @Test
    public void eventInputRunTest() throws IOException {
        String userInput = String.format("event project meeting /from 2019-10-15 1800 /to 2019-10-16 1900%sbye",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Got it. I've added this task: [E][ ] project meeting (from: Oct 15 2019, 18.00 to: Oct 16 2019, 19.00)";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        Bwead.main(null);

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 3];

        assertEquals(expected,actual);
    }

    @Test
    public void incompleteInputRunTest() throws IOException {
        String userInput = String.format("todo%sbye",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "OOPS!!! The description of a todo cannot be empty.";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        Bwead.main(null);

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];

        assertEquals(expected,actual);
    }

    @Test
    public void invalidInputRunTest() throws IOException {
        String userInput = String.format("yello%sbye",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "i don't know what that means :(";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        Bwead.main(null);

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];

        assertEquals(expected,actual);
    }

    @Test
    public void historyTest() throws IOException {
        assertThrows(BweadException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new History("./src/main/java/Bwead/histryFile.txt").load();
            }
        });
    }
}
