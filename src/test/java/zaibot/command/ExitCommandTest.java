package zaibot.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import zaibot.utils.Storage;
import zaibot.utils.TaskList;

public class ExitCommandTest {


    @Test
    public void execute_success() {
        TaskList tasks = new TaskList();
        Storage storage = new Storage(tasks);
        ExitCommand exitCommand = new ExitCommand();
        String endString;
        try {
            endString = exitCommand.execute(tasks, storage);
        } catch (Exception e) {
            endString = e.getMessage();
        }
        Assertions.assertEquals(endString, "See you whenever.");
    }

}
