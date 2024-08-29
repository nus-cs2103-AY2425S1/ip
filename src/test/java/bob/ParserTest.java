package bob;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void testParseValidNewCommand() {
        String command = "todo test command";
        TaskList taskList = new TaskList();
        Ui ui = new Ui();

        try {
            Parser.handleInput(command, taskList, ui);
            assertEquals(1, taskList.size());
            assertEquals("test command", taskList.getTask(0).getDescription());
        } catch (ChatBotException e) {
            ui.showError(e.getMessage());
        }
    }

    @Test
    public void testParseReadFromFile() {
        String fileString = "T | 1 | read from file test";
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList();
        Ui ui = new Ui();

        try {
            tasks.add(Parser.parseTaskFromFile(fileString));
            taskList.setTasks(tasks);
            assertEquals(1, taskList.size());
            assertEquals("read from file test", taskList.getTask(0).getDescription());
            assertTrue(taskList.getTask(0).isDone());
        } catch (ChatBotException e) {
            ui.showError(e.getMessage());
        }
    }
}
