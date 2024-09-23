package jeriel.command;

import jeriel.task.*;
import jeriel.util.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FindCommandTest {

    @Test
    public void execute_findTasks_success() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("Read book"));
        tasks.addTask(new Todo("Write report"));

        FindCommand findCommand = new FindCommand("book");
        String result = findCommand.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
        assertTrue(result.contains("Read book"));
    }

    @Test
    public void execute_noMatchingTasks_returnsNoMatch() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("Read book"));
        tasks.addTask(new Todo("Write report"));

        FindCommand findCommand = new FindCommand("nonexistent");
        String result = findCommand.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
        assertEquals("No tasks match your search keyword.", result);
    }

    @Test
    public void execute_emptyList_returnsNoMatch() {
        TaskList tasks = new TaskList();  // Empty task list
        FindCommand findCommand = new FindCommand("book");
        String result = findCommand.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
        assertEquals("No tasks match your search keyword.", result);
    }
}
