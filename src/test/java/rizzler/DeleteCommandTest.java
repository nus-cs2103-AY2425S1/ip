package rizzler;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void createDeleteCommand_noSecondArg() {
        try {
            String[] fullCommand = {"delete"};
            new DeleteCommand(fullCommand);
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(),
                    "Stop capping and key in the number of the task to delete\n"
                            + "Format:\n"
                            + "delete [number]");
        }
    }

    @Test
    public void createDeleteCommand_secondArgNotNumber() {
        try {
            String[] fullCommand = {"delete", "no"};
            new DeleteCommand(fullCommand);
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(),
                    "Stop capping and ensure that the argument after delete is a number\n"
                            + "Format:\n"
                            + "delete [number]");
        }
    }

    @Test
    public void executeDelete_success() throws Exception {
        ArrayList<Task> testArrayList = new ArrayList<Task>();
        testArrayList.add(new Todo("test 1"));
        TaskList tasks = new TaskList(testArrayList);
        FileStorage fileStorage = new FileStorage("data/test.txt");
        Ui ui = new Ui();
        String[] fullCommand = {"delete", "1"};
        String testOutput = new DeleteCommand(fullCommand).execute(tasks, ui, fileStorage);
        assertEquals(testOutput,
                "I have fanum taxed this task for you:\n"
                        + "[T][ ] test 1\n"
                        + "Now you've gyat 0 tasks in the list.\n");
    }
}
