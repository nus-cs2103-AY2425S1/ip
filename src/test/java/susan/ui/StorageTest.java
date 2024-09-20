package susan.ui;

import org.junit.jupiter.api.Test;
import susan.command.AddCommand;
import susan.task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private TaskList taskList = new TaskList();

    @BeforeEach
    public void createFile() throws IOException, SusanException {
        String[] todoParts = {"todo", "read book"};
        new AddCommand(todoParts).execute(taskList, ui, storage);
        String[] deadlineParts = {"deadline", "return book /by 2024-09-30"};
        new AddCommand(deadlineParts).execute(taskList, ui, storage);
        String[] eventParts = {"event", "book club /from 2024-09-01 1400 /to 2024-09-01 1600"};
        new AddCommand(eventParts).execute(taskList, ui, storage);
    }

    @Test
    public void testReadFile() throws FileNotFoundException, SusanException {
        assertEquals(storage.readTasksFromFile().saveList(), taskList.saveList());
        assertEquals(storage.readTasksFromFile().printList(), taskList.printList());
    }
}
