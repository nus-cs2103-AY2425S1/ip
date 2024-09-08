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
    public void delete_test_exception(){
        DummyArona(".\\testdata.txt");

        // Delete task zero
        try {
            parser.parse("delete ABC");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! Please input a number.", e.getMessage());
        }

        // Delete a task that doesn't exist
        try {
            parser.parse("delete 100");
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
            parser.parse("mark ABC");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! Please input a number.", e.getMessage());
        }

        // Delete a task that doesn't exist
        try {
            parser.parse("unmark 100");
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
            parser.parse("deadline   ");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! Please input description and by date.", e.getMessage());
        }

        // No by date
        try {
            parser.parse("deadline /by");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! Please input description and by date.", e.getMessage());
        }

        // Wrong date format
        try {
            parser.parse("deadline homework /by ????-??-??");
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
            parser.parse("event   ");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! Please input description, from date, and to date.", e.getMessage());
        }

        // No from and to date
        try {
            parser.parse("event party /from /to");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Error! Please input description, from date, and to date.", e.getMessage());
        }

        // Wrong date format
        try {
            parser.parse("event party /from ????-??-?? /to ????-??-??");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Please input your date in yyyy-mm-dd format.", e.getMessage());
        }
    }
}
