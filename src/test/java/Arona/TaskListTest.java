package Arona;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// todo READ ME IMPORTANT
// todo Set intellij to run test using intellij idea
// todo READ ME IMPORTANT

public class TaskListTest {
    // Set up dummy main class
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public void DummyArona(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showException(e);
            taskList = new TaskList();
        }
        parser = new Parser(storage, taskList, ui);
    }

    @Test
    public void get_test_exception(){
        DummyArona(".\\testdata.txt");

        // Delete negative task index
        try {
            taskList.get(-1);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! This task does not exist!", e.getMessage());
        }

        // Delete a task that doesn't exist
        try {
            taskList.get(100);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! This task does not exist!", e.getMessage());
        }
    }

    @Test
    public void size_test_success(){
        DummyArona(".\\testdata.txt");

        // Get current task length
        assertEquals(0, taskList.size());

        // Add a task
        try {
            taskList.add("a");
        } catch (Exception e) {
            fail();
        }

        // Get task length after addition
        assertEquals(1, taskList.size());

        // Delete a task
        try {
            taskList.remove(0);
        } catch (Exception e) {
            fail();
        }

        // Get task length after deletion
        assertEquals(0, taskList.size());
    }
}
