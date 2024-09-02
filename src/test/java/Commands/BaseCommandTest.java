package Commands;

import Exceptions.BrockException;
import Storage.Storage;
import Tasks.TaskList;
import Ui.Ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public abstract class BaseCommandTest {
    protected static final Ui UI = new Ui();
    protected static final Storage STORAGE = new Storage();
    protected static final TaskList TASKS = new TaskList(new ArrayList<>());
    protected static final ByteArrayOutputStream NEW_OUT = new ByteArrayOutputStream();
    private static final PrintStream STANDARD_OUT = System.out;

    @BeforeAll
    public static void createSaveFile() {
        try {
            STORAGE.createFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @BeforeEach
    public void changeOutputStream() {
        System.setOut(new PrintStream(NEW_OUT));
    }

    @AfterEach
    public void restoreOutputStream() {
        System.setOut(STANDARD_OUT);
    }

    @AfterEach
    public void resetFile() {
        try {
            STORAGE.writeToFile("", false);
        } catch (BrockException e) {
            System.out.println(e.getMessage());
        }
    }

    protected String getOutput() {
        String output = NEW_OUT.toString();
        NEW_OUT.reset();
        return output;
    }
}
