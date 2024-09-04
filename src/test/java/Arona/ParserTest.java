package Arona;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// todo READ ME IMPORTANT
// todo Set intellij to run test using intellij idea
// todo READ ME IMPORTANT

public class ParserTest {
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
    public void delete_test_exception(){
        DummyArona(".\\testdata.txt");

        // Delete task zero
        try {
            Parser.parse("delete ABC", storage, tasks, ui);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! Please input a positive number.", e.getMessage());
        }

        // Delete a task that doesn't exist
        try {
            Parser.parse("delete 100", storage, tasks, ui);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! This task does not exist!", e.getMessage());
        }
    }

    @Test
    public void markAndUnmark_test_exception(){
        DummyArona(".\\testdata.txt");

        // Delete task zero
        try {
            Parser.parse("mark ABC", storage, tasks, ui);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! Please input a positive number.", e.getMessage());
        }

        // Delete a task that doesn't exist
        try {
            Parser.parse("unmark 100", storage, tasks, ui);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! This task does not exist!", e.getMessage());
        }
    }

    @Test
    public void deadline_test_exception(){
        DummyArona(".\\testdata.txt");

        // No description
        try {
            Parser.parse("deadline   ", storage, tasks, ui);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! Please input a task description.", e.getMessage());
        }

        // No by date
        try {
            Parser.parse("deadline /by", storage, tasks, ui);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! Please specify a by date.", e.getMessage());
        }

        // Wrong date format
        try {
            Parser.parse("deadline homework /by ????-??-??", storage, tasks, ui);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Please input your date in yyyy-mm-dd format.", e.getMessage());
        }
    }

    @Test
    public void event_test_exception(){
        DummyArona(".\\testdata.txt");

        // No description
        try {
            Parser.parse("event   ", storage, tasks, ui);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! Please input a task description.", e.getMessage());
        }

        // No from and to date
        try {
            Parser.parse("event party /from /to", storage, tasks, ui);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! Please specify a from and to date.", e.getMessage());
        }

        // Wrong date format
        try {
            Parser.parse("event party /from ????-??-?? /to ????-??-??", storage, tasks, ui);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Please input your date in yyyy-mm-dd format.", e.getMessage());
        }
    }
}
