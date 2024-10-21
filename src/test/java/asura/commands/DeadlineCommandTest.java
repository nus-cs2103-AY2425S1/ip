package asura.commands;

import asura.data.exception.AsuraException;
import asura.data.tasks.Deadline;
import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeadlineCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        try {
            storage = new Storage("data/asura.txt");
        } catch (AsuraException e) {
            ui.showError(e.getMessage());
        }
    }

    @Test
    public void testExecute() throws AsuraException {
        String deadlineString = "deadline go study /by 2012-12-11 12:12:12";
        List<String> splitCommand = Arrays.asList(deadlineString.split(" "));
        int byIndex = splitCommand.indexOf("/by");
        List<String> descriptionArray = splitCommand.subList(1, byIndex);
        List<String> dateArray = splitCommand.subList(byIndex + 1, splitCommand.size());
        DeadlineCommand command = new DeadlineCommand(descriptionArray, dateArray);
        command.execute(taskList, ui, storage);
        Deadline deadline = (Deadline)taskList.get(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateStr = "2012-12-11 12:12:12";
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
        assertEquals(1, taskList.size());
        assertEquals("go study", deadline.getDescription());
        assertEquals(dateTime, deadline.getBy());
    }

    @Test
    public void testIsExit() {
        String deadlineString = "deadline go study /by 2012-12-11 12:12:12";
        List<String> splitCommand = Arrays.asList(deadlineString.split(" "));
        int byIndex = splitCommand.indexOf("/by");
        List<String> descriptionArray = splitCommand.subList(1, byIndex);
        List<String> dateArray = splitCommand.subList(byIndex + 1, splitCommand.size());
        DeadlineCommand command = new DeadlineCommand(descriptionArray, dateArray);
        assertFalse(command.isExit());
    }
}
