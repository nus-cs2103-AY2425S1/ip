package nuffle;

import nuffle.command.Command;
import nuffle.command.DeadlineCommand;
import nuffle.exception.NuffleException;
import nuffle.parser.Parser;
import nuffle.storage.Storage;
import nuffle.task.Deadline;
import nuffle.task.Task;
import nuffle.task.TaskList;
import nuffle.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ParserTest {

    @Test
    void parseReturnCorrectCommand() throws NuffleException {
        // Setup initial conditions
        String userInput = "deadline submit homework /by 2024-09-15 2359";
        TaskList tasks = new TaskList(new ArrayList<>());  // Empty TaskList
        Storage storage = new Storage("testPath");  // Mocked or dummy Storage
        Ui ui = new Ui();  // Dummy Ui

        // Parse the command
        Command command = Parser.parseCommand(userInput);

        // Ensure that the command is of the expected type
        assertTrue(command instanceof DeadlineCommand);
        String result = command.execute(tasks, storage, ui);
        assertFalse(tasks.getInputList().isEmpty());

        Task addedTask = tasks.getTask(0);
        assertTrue(addedTask instanceof Deadline);
        Deadline deadlineTask = (Deadline) addedTask;

        // Verify that the description and deadline are correct
        assertEquals("submit homework", deadlineTask.getDescription());
        assertEquals("Sep 15 2024, 11:59 pm", deadlineTask.getFormattedDeadline());  // Adjust this based on the actual format returned by getDeadline()
    }
}
