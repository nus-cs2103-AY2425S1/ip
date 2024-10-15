package king;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import king.commands.FindCommand;
import king.task.Todo;
import king.ui.Ui;

public class FindCommandTest {

    @Test
    public void testFindTaskByKeyword() throws KingException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        // Add tasks to taskList
        taskList.add(new Todo("Complete project"));
        taskList.add(new Todo("Submit report"));

        // Test for finding 'project'
        FindCommand findProject = new FindCommand("project");
        String result = findProject.execute(taskList, ui, storage);
        assertTrue(result.contains("Complete project"), "The task 'Complete project' should be found.");

        // Test for finding a task that doesn't exist
        FindCommand findNonExisting = new FindCommand("nonexisting");
        String noResult = findNonExisting.execute(taskList, ui, storage);
        assertTrue(noResult.contains("No tasks found containing the keyword"), "No task should be found for 'nonexisting'.");
    }
}

