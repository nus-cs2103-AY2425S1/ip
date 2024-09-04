package susan.command;

import org.junit.jupiter.api.Test;
import susan.task.TaskList;
import susan.ui.Storage;
import susan.ui.SusanException;
import susan.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private TaskList taskList = new TaskList();

    @Test
    public void testNoDescription() throws IOException {
        try {
            String[] emptyDescription = {"todo"};
            new AddCommand(emptyDescription).execute(taskList, ui, storage);
        } catch (SusanException e) {
            assertEquals(e.getMessage(), "The description of a task cannot be empty.");
        }
    }

    @Test
    public void testWrongDateFormat() throws IOException {
        try {
            String[] wrongDateFormat = {"deadline", "submit essay /by 11/11/11"};
            new AddCommand(wrongDateFormat).execute(taskList, ui, storage);
        } catch (SusanException e) {
            assertEquals(e.getMessage(), "Please key date in the format: yyyy-MM-dd");
        }
    }

    @Test
    public void testInvalidSeparator() throws IOException {
        try {
            String[] wrongDateFormat = {"deadline", "submit essay /d 11/11/11"};
            new AddCommand(wrongDateFormat).execute(taskList, ui, storage);
        } catch (SusanException e) {
            assertEquals(e.getMessage(), "To add a deadline: deadline + description + /by yyyy-MM-dd");
        }
    }

    @Test
    public void testAddSuccessfulEvent() throws IOException, SusanException {
        String[] commandParts = {"event", "book club /from 2024-09-01 1400 /to 2024-09-01 1600"};
        new AddCommand(commandParts).execute(taskList, ui, storage);
        String expectedList = "\n" + " 1.[E][ ] book club (from: Sept 01 2024 2:00 pm to: 4:00 pm)";
        assertEquals(taskList.printList(), expectedList);
    }
}
