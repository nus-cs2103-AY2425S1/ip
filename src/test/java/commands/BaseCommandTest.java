package commands;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import exceptions.BrockException;
import storage.Storage;
import task.TaskList;

public abstract class BaseCommandTest {
    protected static final Storage STORAGE = new Storage();
    protected static final TaskList TASKS = new TaskList(new ArrayList<>());

    @BeforeAll
    public static void createSaveFile() {
        try {
            STORAGE.createFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void resetFile() {
        try {
            STORAGE.writeToFile("", false);
        } catch (BrockException e) {
            System.out.println(e.getMessage());
        }
    }
}
