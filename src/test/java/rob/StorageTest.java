package rob;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StorageTest {
    @Test
    public void testSaveTasks() {
        List<Task> taskList= new ArrayList<>();
        taskList.add(new Todo("buy apples"));
        taskList.add(new Deadline("buy bananas", "Tuesday"));
        taskList.add(new Event("peel coconuts", "3pm", "4pm"));
        TaskList tasks = new TaskList(taskList);
        Storage storage = new Storage("./data/testSave.txt");

        storage.saveTasks(tasks);
        File file = new File("./data/testSave.txt");
        Assertions.assertTrue(file.exists(), "File should exist after saving tasks");

        List<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            String line;
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                lines.add(line);
            }
        } catch (IOException e) {
            Assertions.fail("An error occurred while reading the saved file: " + e.getMessage());
        }

        // check that the number of lines
        Assertions.assertEquals(tasks.getTasks().size(), lines.size(),
                "Number of lines in the file should match the number of tasks");

        // check each line with expected task string
        for (int i = 0; i < lines.size(); i++) {
            Assertions.assertEquals(tasks.getTasks().get(i).toSaveString(), lines.get(i),
                    "Task line should match the expected save string");
        }
    }

}
