import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hoodini.Storage;




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
        Storage storage = new Storage(new UiStub());
        storage.add(new InputStub("[T] [ ] read book"));
        storage.add(new InputStub("[D] [ ] return book (by: Dec 12 2020)"));
        assertEquals("Here are the list of tasks that needs to be completed: \n"
                + "1. [T] [ ] read book\n2. [D] [ ] return book (by: Dec 12 2020)\n", storage.output());
    }

    @Test
    public void testStore() {
        outputStreamCaptor.reset();
        Storage storage = new Storage(new UiStub());
        assertEquals("Noted. " + "I have added this task:\n"
                + "[T] [ ] read book\n"
                + "You have 1 tasks in the list.", storage.store(new InputStub("[T] [ ] read book")));
    }

    @Test
    public void testDelete() {
        outputStreamCaptor.reset();
        Storage storage = new Storage(new UiStub());
        storage.store(new InputStub("[T] [ ] read book"));
        assertEquals("Noted. " + "I have deleted this task:\n"
                + "[T] [ ] read book\n"
                + "You have 0 tasks in the list.", storage.delete("delete 1"));
    }

}

