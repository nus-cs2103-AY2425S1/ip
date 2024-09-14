package rizzler;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnmarkCommandTest {
    @Test
    public void createMarkCommand_noSecondArg() {
        try {
            String[] fullCommand = {"unmark"};
            new UnmarkCommand(fullCommand);
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(),
                    "Stop capping and key in the number of the task to unmark\n"
                            + "Format:\n"
                            + "unmark [number]");
        }
    }

    @Test
    public void createMarkCommand_secondArgNotNumber() {
        try {
            String[] fullCommand = {"mark", "no"};
            new UnmarkCommand(fullCommand);
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(),
                    "Stop capping and ensure that the argument after unmark is a number\n"
                            + "Format:\n"
                            + "unmark [number]");
        }
    }

    @Test
    public void executeMark_success() throws Exception {
        ArrayList<Task> testArrayList = new ArrayList<>();
        testArrayList.add(new Todo("test 1"));
        TaskList tasks = new TaskList(testArrayList);
        FileStorage fileStorage = new FileStorage("data/test.txt");
        Ui ui = new Ui();
        String[] fullCommand = {"mark", "1"};
        String testOutput = new UnmarkCommand(fullCommand).execute(tasks, ui, fileStorage);
        assertEquals(testOutput,
                "Womp womp. Better do it later:\n"
                        + "[T][ ] test 1\n");
    }
}
