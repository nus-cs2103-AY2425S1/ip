package sigma;
import sigma.task.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// deals with loading tasks from the file and saving tasks in the file
public class Storage {
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

//    public static String getItemsFromFile(String path) throws IOException {
//        // there's gotta be a better way to do this
//        StringBuilder itemsFromFile = new StringBuilder();
//        int i = 1;
//        for (String line: Files.readAllLines(Paths.get(path))) {
//            itemsFromFile.append(i).append(". ").append(line).append("\n");
//            i++;
//        }
//        return itemsFromFile.toString();
//    }

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
