package botty.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import botty.exceptions.BottyException;
import botty.tasks.TaskManager;

public class DeadlineCommandTest {
    @Test
    public void runDeadlineCommand_validInputs_success() throws BottyException {
        TaskManager taskManager = new TaskManager();
        Command command = new DeadlineCommand();
        ParsedInput parsedInput = ParsedInput.parse("deadline do homework /by 2024-08-31");
        command.execute(taskManager, parsedInput);
        assertEquals(1, taskManager.size());
    }
    @Test
    public void runDeadlineCommand_noInput_exceptionThrown() {
        try {
            TaskManager taskManager = new TaskManager();
            Command command = new DeadlineCommand();
            ParsedInput parsedInput = ParsedInput.parse("deadline do homework");
            command.execute(taskManager, parsedInput);
        } catch (BottyException e) {
            assertEquals("I am unable to add that deadline! Please provide details in the following format: "
                    + "[description] /by [deadline]", e.getMessage());
        }
    }
    @Test
    public void runDeadlineCommand_emptyInput_exceptionThrown() {
        try {
            TaskManager taskManager = new TaskManager();
            Command command = new DeadlineCommand();
            ParsedInput parsedInput = ParsedInput.parse("deadline do homework /by");
            command.execute(taskManager, parsedInput);
        } catch (BottyException e) {
            assertEquals("I am unable to add that deadline! Please provide details in the following format: "
                    + "[description] /by [deadline]", e.getMessage());
        }
    }
}
