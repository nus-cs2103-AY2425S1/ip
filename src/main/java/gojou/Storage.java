package gojou;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import gojou.task.Priority;
import gojou.task.Task;

/**
 * Handles loading tasks from a file and saving tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored or retrieved.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path should not be null";
        assert !filePath.isEmpty() : "File path should not be empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return An ArrayList of tasks read from the file.
     * @throws FileNotFoundException If the file specified by the file path does not exist.
     * @throws GojouException If there is an issue converting text in the file into a Task object,
     *                       likely due to incorrect formatting in the file.
     */
    public ArrayList<Task> load() throws GojouException, java.io.IOException {
        List<String> lines = readFileIntoList();
        ArrayList<Task> list = new ArrayList<>();

        // Creates an ArrayList of Tasks based on the stored list of tasks such that user can retrieve
        // previously stored task information
        for (int i = 0; i < lines.size(); i++) {
            Task task = createTask(lines.get(i));
            list.add(task);
        }

        return list;
    }

    private List<String> readFileIntoList() throws java.io.IOException {
        Path path = Paths.get(filePath);
        Path parentDir = path.getParent();

        //check if parent directory exists and create it if it doesn't
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        //check if file exists and create it if it doesn't
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        return Files.readAllLines(path);
    }

    private Task createTask(String lineOfWords) throws GojouException {
        assert !lineOfWords.isEmpty() : "Empty line in Gojou.txt";
        Scanner lineScanner = new Scanner(lineOfWords);
        String category = lineScanner.next();
        Task task = new Task("", false, Priority.NONE);

        ArrayList<String> arrOfStr = new ArrayList<>();
        switch (category) {
        case "[T][":
            lineScanner.next();
            task = Parser.makeTodoTask(lineScanner, arrOfStr, false);
            break;
        case "[T][X]":
            task = Parser.makeTodoTask(lineScanner, arrOfStr, true);
            break;
        case "[D][":
            lineScanner.next();
            task = Parser.makeDeadlineTask(lineScanner, arrOfStr, false);
            break;
        case "[D][X]":
            task = Parser.makeDeadlineTask(lineScanner, arrOfStr, true);
            break;
        case "[E][":
            lineScanner.next();
            task = Parser.makeEventTask(lineScanner, arrOfStr, false);
            break;
        case "[E][X]":
            Parser.makeEventTask(lineScanner, arrOfStr, true);
            break;
        default:
            //do nothing
        }
        return task;
    }

    /**
     * Saves tasks to the file specified by the file path.
     *
     * @param list An ArrayList of Tasks to be written to the file.
     * @throws IOException If there are issues writing text to the file.
     */
    public void save(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < list.size() - 1; i++) {
            fw.write(list.get(i).toStorageString() + System.lineSeparator());
        }
        if (!list.isEmpty()) {
            fw.write(list.get(list.size() - 1).toStorageString());
        }
        fw.close();
    }
}

