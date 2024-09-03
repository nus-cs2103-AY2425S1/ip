package commands;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import exceptions.BrockException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

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

    // The approach of switching output stream to read system output
    // Was adapted from https://www.baeldung.com/java-testing-system-out-println
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
