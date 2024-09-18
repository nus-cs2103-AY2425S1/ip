package sigma;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import sigma.task.Deadline;
import sigma.task.Event;
import sigma.task.Task;
import sigma.task.ToDo;

/**
 * Class that deals with loading and saving tasks from the file it is stored in
 *
 * @author Qiao Yi
 */
public class Storage {
    private static Path filePath = Path.of("C:\\Users\\limqi\\OneDrive\\Desktop\\uni\\CS2103T\\ip\\data\\sigma.txt");
    /**
     * Constructor for a Storage object
     * Credit: Code adapted from @seandias on GitHub
     * @param filePath The path to the file that the data is stored in
     */
    public Storage(String filePath) {
//        try {
//            File file = new File(filePath);
//            if (file.createNewFile()) {
//                System.out.println("New file created: " + file.getName());
//            }
//        } catch (IOException e) {
//            System.err.println("error occurred creating file or reading from file: " + e.getMessage());
//        }
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";
        Storage.filePath = Paths.get(filePath);
    }

//    /**
//     * Reads the file and lists out their items
//     *
//     * @param path to the file to be read
//     * @return a String representation of the item
//     * @throws IOException if there is an error reading the file
//     */
//    public static String getItemsFromFile(String path) throws IOException {
//        StringBuilder itemsFromFile = new StringBuilder();
//        int i = 1;
//        for (String line : Files.readAllLines(Paths.get(path))) {
//            itemsFromFile.append(i).append(". ").append(line).append("\n");
//            i++;
//        }
//        return itemsFromFile.toString();
//    }

    /**
     * Reads the tasks contained in the file and adds it to the TaskList
     *
     * Code adapted from @seandias on GitHub from src/main/java/skd/storage/ToStore.java
     */
    public void readTasksFromFile() {
//        try {
//            List<String> lines = Files.readAllLines(Paths.get("data/sigma.txt"));
//            // handle empty file case
//            if (lines.isEmpty()) {
//                return;
//            }
//            for (String line : lines) {
//                // parse each line
//                String[] parts = line.split(" ", 2); // Split at the first space
//                String status = parts[0];
//                String taskName = parts[1];
//                boolean isDone = status.equals("[X]");
//                Task task = new Task(taskName, isDone);
//                TaskList.addToList(task);
//
//            }
//        } catch (IOException e) {
//            System.err.println("error reading lines from file: " + e.getMessage());
//        }
        List<Task> tasks = new ArrayList<>();
        try {
            if (Files.notExists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
//                return tasks;
            }

            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                Task task = parseTask(line);
                if (task != null) {
                    TaskList.getItems().add(task);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + "Unable to load tasks from the file. Starting with an empty list.");
        }

//        return tasks;
    }

    /**
     * Parses the task read from the file and adds it to the TaskList
     * Code adapted from @seandias on GitHub from src/main/java/skd/storage/ToStore.java
     * @param line The line read from the file
     * @return A task object created from reading the file
     */
    public static Task parseTask(String line) {
        try {
            String type = line.substring(1, 2);
            boolean isDone = line.charAt(4) == 'X';
            String description;

            switch (type) {
                case "T":
                    description = line.substring(7);
                    return new ToDo(description, isDone);
                case "D":
                    description = line.substring(7, line.indexOf("(by:")).trim();
                    String by = line.substring(line.indexOf("(by:") + 5, line.indexOf(")")).trim();
                    return new Deadline(description, isDone, by);
                case "E":
                    description = line.substring(7, line.indexOf("(from:")).trim();
                    String from = line.substring(line.indexOf("(from:") + 7, line.indexOf(" to:")).trim();
                    String to = line.substring(line.indexOf("to:") + 4, line.indexOf(")")).trim();
                    return new Event(description, isDone, from, to);
                default:
                    throw new IllegalArgumentException("Invalid task type");
            }
        } catch (Exception e) {
            System.out.println("Invalid entry found and skipped: " + line);
            return null;
        }
    }

    /**
     * Writes the tasks in the TaskList to the file
     * Code Adapted from @seandias on Github from src/main/java/skd/command/Parser.java
     */
    public static void saveToFile() {
        List<String> lines = new ArrayList<>();
        for (Task task : TaskList.getItems()) {
            lines.add(task.toString());
        }
        try {
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.out.println("Unable to save tasks to the file.");
        }
    }
}
