package Data;

import Exceptions.EmptyDescException;
import Exceptions.InvalidIndexException;
import Tasks.Task;
import Tasks.ToDos;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorelistTest {
    @Test
    public void addToDoItemCheck() {
        ArrayList<Task> items = new ArrayList<>();
        StoreList storeList = new StoreList(items);

        // Create a new ToDo task and add it to the StoreList
        storeList.addItem("read book", "todo");
        int size = storeList.getSize();
        // Assert that the task was added correctly
        assertEquals("[T][ ] read book", storeList.get(size - 1).print());
    }

    @Test
    public void addEventItemCheck() {
        ArrayList<Task> items = new ArrayList<>();
        StoreList storeList = new StoreList(items);

        // Create a new event task and add it to the StoreList
        storeList.addItem("project /from 16:00 /to 18:00 /on 2020-04-05", "event");
        int size = storeList.getSize();
        // Assert that the task was added correctly
        assertEquals("[E][ ] project (from: 4:00 pm to: 6:00 pm on: Apr 05 2020)", storeList.get(size - 1).print());
    }

    @Test
    public void addDeadlineItemCheck() {
        ArrayList<Task> items = new ArrayList<>();
        StoreList storeList = new StoreList(items);

        // Create a new deadline task and add it to the StoreList
        storeList.addItem("read book /by 2020-04-05 16:00", "deadline");
        int size = storeList.getSize();
        // Assert that the task was added correctly
        assertEquals("[D][ ] read book (by: Apr 05 2020, 4:00 pm)", storeList.get(size - 1).print());
    }

    @Test
    public void markItemCheck() {
        ArrayList<Task> items = new ArrayList<>();
        StoreList storeList = new StoreList(items);
        try {

            // Create a new deadline task and add it to the StoreList
            storeList.addItem("read book /by 2020-04-05 16:00", "deadline");
            int size = storeList.getSize();
            storeList.markItem(size);
            // Assert that the task was marked correctly
            assertEquals("[D][X] read book (by: Apr 05 2020, 4:00 pm)", storeList.get(size - 1).print());
        } catch (InvalidIndexException e) {
            assertEquals("Task number does not exist", e.getMessage());
        }
    }

    @Test
    public void unmarkItemCheck() {
        ArrayList<Task> items = new ArrayList<>();
        StoreList storeList = new StoreList(items);
        try {

            // Create a new deadline task and add it to the StoreList
            storeList.addItem("read book /by 2020-04-05 16:00", "deadline");
            int size = storeList.getSize();
            storeList.UnmarkItem(size);
            // Assert that the task was unmarked correctly
            assertEquals("[D][ ] read book (by: Apr 05 2020, 4:00 pm)", storeList.get(size - 1).print());
        } catch (InvalidIndexException e) {
            assertEquals("Task number does not exist", e.getMessage());
        }
    }

    @Test
    public void deleteItemCheck() {
        ArrayList<Task> items = new ArrayList<>();
        StoreList storeList = new StoreList(items);

        try {

            // Create a new deadline task and add it to the StoreList
            storeList.addItem("read book /by 2020-04-05 16:00", "deadline");
            int size = storeList.getSize();
            storeList.deleteItem(size);
            // Assert that the task was deleted correctly
            storeList.get(size).print();
        } catch (InvalidIndexException e) {
            assertEquals("Task number does not exist", e.getMessage());
        }
    }

    @Test
    public void displayItemCheck() {
        ArrayList<Task> items = new ArrayList<>();
        StoreList storeList = new StoreList(items);

        // Create a new deadline task and add it to the StoreList
        storeList.addItem("read book /by 2020-04-05 16:00", "deadline");

        // Create a new event task and add it to the StoreList
        storeList.addItem("project /from 16:00 /to 18:00 /on 2020-04-05", "event");

        // Create a new ToDo task and add it to the StoreList
        storeList.addItem("read book", "todo");


        // Capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Save the original System.out
        System.setOut(new PrintStream(outContent)); // Redirect System.out to outContent

        // Call the method that prints to the console
        storeList.displayItems();


        assertEquals("    Here are the tasks in your list:\r\n" +
                "    1.[D][ ] read book (by: Apr 05 2020, 4:00 pm)\r\n" +
                "    2.[E][ ] project (from: 4:00 pm to: 6:00 pm on: Apr 05 2020)\r\n" +
                "    3.[T][ ] read book\r\n", outContent.toString());

        // Restore original System.out
        System.setOut(originalOut);
    }

    @Test
    public void dueCheck() {
        ArrayList<Task> items = new ArrayList<>();
        StoreList storeList = new StoreList(items);

        // Create a new deadline task and add it to the StoreList
        storeList.addItem("read book /by 2020-04-05 16:00", "deadline");

        // Create a new event task and add it to the StoreList
        storeList.addItem("project /from 16:00 /to 18:00 /on 2020-04-05", "event");

        // Create a new ToDo task and add it to the StoreList
        storeList.addItem("read book", "todo");


        // Capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Save the original System.out
        System.setOut(new PrintStream(outContent)); // Redirect System.out to outContent

        // Call the method that prints to the console
        storeList.dueOnDate("2020-04-05");


        assertEquals("    Here are the tasks due on 2020-04-05:\r\n" +
                "    1.[D][ ] read book (by: Apr 05 2020, 4:00 pm)\r\n" +
                "    2.[E][ ] project (from: 4:00 pm to: 6:00 pm on: Apr 05 2020)\r\n", outContent.toString());

        // Restore original System.out
        System.setOut(originalOut);
    }

    @Test
    public void findCheck() {
        ArrayList<Task> items = new ArrayList<>();
        StoreList storeList = new StoreList(items);

        // Create a new deadline task and add it to the StoreList
        storeList.addItem("read book /by 2020-04-05 16:00", "deadline");

        // Create a new event task and add it to the StoreList
        storeList.addItem("project /from 16:00 /to 18:00 /on 2020-04-05", "event");

        // Create a new ToDo task and add it to the StoreList
        storeList.addItem("read book", "todo");


        // Capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Save the original System.out
        System.setOut(new PrintStream(outContent)); // Redirect System.out to outContent

        // Call the method that prints to the console
        storeList.displayItemsWithWord("book");


        assertEquals("    Here are the tasks in your list that match book:\r\n" +
                "    1.[D][ ] read book (by: Apr 05 2020, 4:00 pm)\r\n" +
                "    3.[T][ ] read book\r\n", outContent.toString());

        // Restore original System.out
        System.setOut(originalOut);
    }
}