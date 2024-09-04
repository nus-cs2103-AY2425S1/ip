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
    private TaskList tasks;
    private Ui ui;

    public void DummyArona(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showException(e);
            tasks = new TaskList(new ArrayList<>());
        }
    }


    @Test
    public void get_test_exception(){
        DummyArona(".\\testdata.txt");

        // Delete negative task index
        try {
            tasks.get(-1);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! Please input a positive number.", e.getMessage());
        }

        // Delete a task that doesn't exist
        try {
            tasks.get(100);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! This task does not exist!", e.getMessage());
        }
    }

    @Test
    public void size_test_success(){
        DummyArona(".\\testdata.txt");

        try {
            ui.showList(tasks);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Get current task length
        assertEquals(0, tasks.size());

        // Add a task
        try {
            tasks.add("a");
        } catch (Exception e) {
            fail();
        }

        // Get task length after addition
        assertEquals(1, tasks.size());

        // Delete a task
        try {
            tasks.remove(0);
        } catch (Exception e) {
            fail();
        }

        // Get task length after deletion
        assertEquals(0, tasks.size());

    }
}
