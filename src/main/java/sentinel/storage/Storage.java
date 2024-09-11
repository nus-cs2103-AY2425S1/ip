package sentinel.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import sentinel.parser.Parser;
import sentinel.task.Task;
import sentinel.task.TaskList;

/**
 * Represents a storage for storing Sentinel's tasks.
 */
public class Storage {
    /**
     * Loads file that was requested.
     *
     * @param fileName Name of the file to load.
     * @return File that was loaded.
     */
    private static File loadFile(String fileName) throws IOException {
        File f = null;

        // Check if directory exists
        Path path = Path.of("src/main/data");

        // Check if file already exists
        if (Files.exists(path)) {
            f = new File("src/main/data/" + fileName);
            return f;
        }

        boolean h = new File("src/main/data").mkdirs();
        if (h) {
            File newFile = new File("src/main/data/" + fileName);
            newFile.createNewFile();

            f = newFile;
        }
        return f;
    }

    /**
     * Loads the task list that was saved.
     *
     * @return List of tasks.
     * @throws IOException if file loading was not successful.
     */
    public static TaskList loadTaskList() throws IOException {
        File f = loadFile("data.txt");
        assert(f != null) : "File does not exist";
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        TaskList listOfTasks = new TaskList();

        while (s.hasNextLine()) {
            Task newTask = Parser.parseStringToTask(s.nextLine());

            if (newTask == null) {
                continue;
            }

            listOfTasks.addTask(newTask);
        }

        return listOfTasks;
    }

    /**
     * Saves the current task list to a file.
     *
     * @param content Content to be saved to a file.
     * @throws IOException if task list could not be saved.
     */
    public static void saveTaskList(String content) throws IOException {
        FileWriter fw = new FileWriter("src/main/data/data.txt");
        fw.write(content);
        fw.close();
    }
}
