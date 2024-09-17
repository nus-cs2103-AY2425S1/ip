package commands;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import exceptions.BrockException;
import storage.task.TaskStorage;
import task.TaskList;

public abstract class BaseCommandTest {
    protected static final TaskStorage TASK_STORAGE = new TaskStorage();
    protected static final TaskList TASKS = new TaskList(new ArrayList<>());

    @BeforeAll
    public static void createSaveFile() {
        try {
            TASK_STORAGE.createFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void resetFile() {
        try {
            TASK_STORAGE.writeToFile("", false);
        } catch (BrockException e) {
            System.out.println(e.getMessage());
        }
    }
}
