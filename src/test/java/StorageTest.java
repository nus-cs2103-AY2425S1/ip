import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hoodini.Deadline;
import hoodini.Storage;
import hoodini.ToDo;
import hoodini.Ui;



public class StorageTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void testOutput() throws IOException {
        outputStreamCaptor.reset();
        Storage storage = new Storage(new Ui());
        storage.add(new ToDo("todo read book"));
        storage.add(new Deadline("deadline return book /by 2020-12-12"));
        storage.output();
        assertEquals("Here are the list of tasks that needs to be completed: \n"
                + "1. [T] [ ] read book\n2. [D] [ ] return book (by: Dec 12 2020)\n", outputStreamCaptor.toString());
    }

}

