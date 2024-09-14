package reo.tasks;

import org.junit.jupiter.api.Test;
import reo.storage.TaskStorage;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskParserTest {
    @Test
    public void parseTodoCommand_oneWord() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        TaskStorage storage = new TaskStorage("./data/reo.txt");
        TaskParser parser = new TaskParser("todo Lab", taskList, storage);
        String response = parser.parse();

        String expected = "I've added this todo:\n" +
                " [T] [ ] Lab\n";
        assertEquals(response, expected);
    }

    @Test
    public void parseTodoCommand_manyWords() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Test Task 1", false));
        tasks.add(new Todo("Test Task 2", true));
        tasks.add(new Deadline("Test Deadline 1", false, "2024-09-14"));

        TaskList taskList = new TaskList(tasks);
        TaskStorage storage = new TaskStorage("./data/reo.txt");
        TaskParser parser = new TaskParser("todo Lab 1, 2, 3, 4", taskList, storage);

        String response = parser.parse();
        String expected = "I've added this todo:\n" +
                " [T] [ ] Lab 1, 2, 3, 4\n";
        assertEquals(response, expected);
    }

    @Test
    public void parseTodoCommand_invalid() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Test Task 1", false));
        tasks.add(new Todo("Test Task 2", true));
        tasks.add(new Deadline("Test Deadline 1", false, "2024-09-14"));

        TaskList taskList = new TaskList(tasks);
        TaskStorage storage = new TaskStorage("./data/reo.txt");
        TaskParser parser = new TaskParser("todo", taskList, storage);

        String response = parser.parse();
        String expected = "Please enter a valid task name.\n";

        assertEquals(response, expected);
    }
}
