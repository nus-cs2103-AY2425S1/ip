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
        assertEquals("Here are the list of tasks that needs to be completed: \n1. [T] [ ] read book\n2. [D] [ ] return book (by: Dec 12 2020)\n", outputStreamCaptor.toString());
    }

}

