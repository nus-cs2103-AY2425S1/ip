package sigma;
import sigma.task.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class that deals with loading and saving tasks from the file it is stored in
 *
 * @author Qiao Yi
 */
public class Storage {
    /**
     * Constructor for a Storage object
     * @param filePath The path to the file that the data is stored in
     */
    public Storage(String filePath) {
        try {
            File file = new File(filePath);
//            FileWriter writer = new FileWriter(filePath, true);

            if (file.createNewFile()) {
                System.out.println("New file created: " + file.getName());
            }
        } catch (IOException e) {
            System.err.println("error occurred creating file or reading from file: " + e.getMessage());
        }
    }

    /**
     * Reads the file and lists out their items
     * @param path to the file to be read
     * @return a String representation of the item
     * @throws IOException if there is an error reading the file
     */
    public static String getItemsFromFile(String path) throws IOException {
        // there's gotta be a better way to do this
        StringBuilder itemsFromFile = new StringBuilder();
        int i = 1;
        for (String line: Files.readAllLines(Paths.get(path))) {
            itemsFromFile.append(i).append(". ").append(line).append("\n");
            i++;
        }
        return itemsFromFile.toString();
    }

    /**
     * Reads the tasks contained in the file and adds it to the TaskList
     */
    public void readTasksFromFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("data/sigma.txt"));
            // handle empty file case
            if (lines.isEmpty()) {
                return;
            }
            for (String line : lines) {
                // parse each line
                String[] parts = line.split(" ", 2); // Split at the first space
                String status = parts[0];
                String taskName = parts[1];
                boolean isDone = status.equals("[X]");
                Task task = new Task(taskName, isDone);
//                items.add(task);
                TaskList.addToList(task);

            }
        } catch (IOException e) {
            System.err.println("error reading lines from file: " + e.getMessage());
        }
    }
}
