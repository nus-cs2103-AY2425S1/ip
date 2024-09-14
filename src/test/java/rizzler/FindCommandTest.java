package rizzler;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

class FindCommandTest {
    @Test
    public void createFindCommand_noSecondArg() {
        try {
            String[] fullCommand = {"find"};
            new FindCommand(fullCommand);
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(),
                    "Stop yapping and enter the string to find");
        }
    }

    @Test
    public void executeFindCommand_success() throws Exception {
        ArrayList<Task> testArrayList = new ArrayList<>();
        testArrayList.add(new Todo("test 1"));
        TaskList tasks = new TaskList(testArrayList);
        Ui ui = new Ui();
        FileStorage fileStorage = new FileStorage("data/test.txt");
        String[] fullCommand = {"find", "test"};
        String output = new FindCommand(fullCommand).execute(tasks, ui, fileStorage);
        assertEquals(output,
                "I found these tasks matching your keyword\n"
                        + "1. [T][ ] test 1\n");
    }
}