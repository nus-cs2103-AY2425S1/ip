package susan.ui;

import org.junit.jupiter.api.Test;
import susan.command.AddCommand;
import susan.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private TaskList taskList = new TaskList();
    private final String FILE_PATH = "./src/data/SusanToDoList.txt";

    @BeforeEach
    public void createFile() throws IOException, SusanException {
        String[] commandParts = {"event", "book club /from 2024-09-01 1400 /to 2024-09-01 1600"};
        new AddCommand(commandParts).execute(taskList, ui, storage);
    }

    @Test
    public void testGetFile() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner s = new Scanner(file);
        String fileList = "";
        fileList += s.nextLine() + "\n" + s.nextLine();
        assertEquals(fileList, taskList.printList());
    }
}
