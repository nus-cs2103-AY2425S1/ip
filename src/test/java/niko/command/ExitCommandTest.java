package niko.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import niko.main.Storage;
import niko.main.Ui;
import niko.task.TaskList;


class ExitCommandTest {

    @Test
    void execute_showsGoodbyeMessage() {
        Ui ui = new Ui();
        Storage storage = new Storage("test_storage.txt");
        TaskList taskList = new TaskList();

        ExitCommand exitCommand = new ExitCommand();
        exitCommand.execute(taskList, ui, storage);


    }

    @Test
    void isExit_returnsTrue() {
        ExitCommand exitCommand = new ExitCommand();
        assertTrue(exitCommand.isExit());
    }
}
