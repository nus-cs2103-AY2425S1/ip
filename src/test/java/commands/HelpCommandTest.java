package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.TaskList;
import utils.Storage;
import utils.Ui;

public class HelpCommandTest {

    @Test
    public void execute_helpCommand_displaysHelpMessage() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/test.txt");

        HelpCommand helpCommand = new HelpCommand();
        String resultMessage = helpCommand.execute(taskList, ui, storage);

        String expectedMessage = "Here are the available commands:\n"
                + "1. list - List all tasks\n"
                + "2. todo <description> - Add a to-do task\n"
                + "3. event <description> /from <start time> /to <end time> - Add an event task\n"
                + "4. deadline <description> /by <due date> - Add a deadline task\n"
                + "5. mark <task number> - Mark a task as done\n"
                + "6. unmark <task number> - Unmark a task as not done\n"
                + "7. delete <task number> - Delete a task\n"
                + "8. find <keyword> - Find tasks containing a keyword\n"
                + "9. remind <days> - Show tasks due within the specified number of days\n"
                + "10. help - Show this help message\n"
                + "11. bye - Exit the application";

        assertEquals(expectedMessage, resultMessage);
    }
}
