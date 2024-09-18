package seedu.maxine.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.maxine.parser.FileParser;
import seedu.maxine.task.MaxineList;
import seedu.maxine.task.Task;

/**
 * Manages the storage of tasks in a file.
 * <p>
 * The Storage class provides functionality to load tasks from a file,
 * refresh the file with updated task lists, and query tasks based on a search keyword.
 * </p>
 */
public class Storage implements MaxineStorage {
    private String filePath;
    private FileParser fileParser;
    /**
     * Constructs new instance of Storage class
     * @param filePath The path to the file where the data is stored or will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.fileParser = new FileParser();
    }
    /**
     * Returns the most updated collection of tasks.
     * The method reads the txt file and parses it to convert it into
     * its corresponding task. The tasks are then added to an ArrayList
     * @return ArrayList of collection of current tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                Task task = fileParser.parse(line);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oh no! I can't seem to find the file :(");
        }
        return tasks;
    }
    /**
     * Adds new lines to the txt file, based on updated tasks.
     * @param tasks updated TaskList
     */
    public void refreshStorage(MaxineList tasks) {

        File file = new File(filePath);

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(file, false))) {
            for (Task item : tasks) {
                writer.write(item.writeToFile());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.print("Error in File Writing java\n");
        }
    }
}
