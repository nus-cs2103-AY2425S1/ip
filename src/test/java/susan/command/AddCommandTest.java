package susan.command;

import org.junit.jupiter.api.Test;
import susan.task.TaskList;
import susan.ui.Storage;
import susan.ui.SusanException;
import susan.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private TaskList taskList = new TaskList();

    @Test
    public void testNoDescription() {
        // ToDo
        try {
            String[] emptyToDoDescription = {"todo"};
            new AddCommand(emptyToDoDescription).execute(taskList, ui, storage);
        } catch (SusanException e) {
            assertEquals(e.getMessage(), "The description of a task cannot be empty.");
        }
        // Test Deadline with trailing space
        try {
            String[] emptyDeadlineDescription = {"deadline "};
            new AddCommand(emptyDeadlineDescription).execute(taskList, ui, storage);
        } catch (SusanException e) {
            assertEquals(e.getMessage(), "The description of a task cannot be empty.");
        }
        // Test Event with leading and trailing spaces
        try {
            String[] emptyEventDescription = {"  event  "};
            new AddCommand(emptyEventDescription).execute(taskList, ui, storage);
        } catch (SusanException e) {
            assertEquals(e.getMessage(), "The description of a task cannot be empty.");
        }
    }

    @Test
    public void testWrongDateFormat() {
        try {
            String[] wrongDateFormat = {"deadline", "submit essay /by 11/11/11"};
            new AddCommand(wrongDateFormat).execute(taskList, ui, storage);
        } catch (SusanException e) {
            assertEquals(e.getMessage(), "Please key date in the format: yyyy-MM-dd");
        }
    }

    @Test
    public void testInvalidSeparator() {
        // Deadline
        try {
            String[] wrongDateFormatDeadline = {"deadline", "submit essay /d 11/11/11"};
            new AddCommand(wrongDateFormatDeadline).execute(taskList, ui, storage);
        } catch (SusanException e) {
            assertEquals(e.getMessage(), "To add a deadline: deadline + description + /by yyyy-MM-dd");
        }
        // Event
        try {
            String[] wrongDateFormatEvent = {"event", "project meeting /f 2024-11-11 1800 /t 2024-11-11 2000"};
            new AddCommand(wrongDateFormatEvent).execute(taskList, ui, storage);
        } catch (SusanException e) {
            assertEquals(e.getMessage(),
                "To add an event: event + description + /from yyyy-MM-dd HHmm + /to yyyy-MM-dd HHmm");
        }
    }

    @Test
    public void testAddSuccessfulEvent() throws SusanException {
        String[] commandParts = {"event", "book club /from 2024-09-01 1400 /to 2024-09-01 1600"};
        new AddCommand(commandParts).execute(taskList, ui, storage);
        String expectedList = "\n" + " 1.[E][ ] book club (from Sept 01 2024 2:00 pm to 4:00 pm)";
        assertEquals(taskList.printList(), expectedList);
    }

    @Test
    public void testLeadingTrailingSpaces() throws SusanException {
        // Todo
        String[] todoCommandParts = {"todo", " read book "};
        new AddCommand(todoCommandParts).execute(taskList, ui, storage);
        String expectedList = "\n" + " 1.[T][ ] read book";
        assertEquals(taskList.printList(), expectedList);
        // Deadline
        String[] deadlineCommandParts = {"deadline", "return book /by 2024-09-03"};
        new AddCommand(deadlineCommandParts).execute(taskList, ui, storage);
        expectedList += "\n" + " 2.[D][ ] return book (by Sept 03 2024)";
        assertEquals(taskList.printList(), expectedList);
        // Event
        String[] eventCommandParts = {"event", "book club  /from 2024-09-01 1400 /to 2024-09-01 1600"};
        new AddCommand(eventCommandParts).execute(taskList, ui, storage);
        expectedList += "\n" + " 3.[E][ ] book club (from Sept 01 2024 2:00 pm to 4:00 pm)";
        assertEquals(taskList.printList(), expectedList);
    }
}
