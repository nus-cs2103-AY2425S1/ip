package fred;

import fred.Tasks.Deadline;
import fred.Tasks.Event;
import fred.Tasks.Task;
import fred.Tasks.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles the creation, reading, updating, and deletion of data
 * from a persistent storage file. It manages tasks stored in a file and allows
 * the application to load and save tasks between sessions.
 */
public class Storage {
    private File dataFile;

    /**
     * Ensures that the data file and its directory exist. If not, they are created.
     */
    public void getDataFile() {
        File dataDirectory = new File("./data");
        if (!dataDirectory.exists() || !dataDirectory.isDirectory()) {
            dataDirectory.mkdir();
        }
        dataFile = new File("./data/fred.txt");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retrieves tasks from the data file and returns them as an ArrayList of Task objects.
     *
     * @return An ArrayList containing the tasks read from the data file.
     */
    public ArrayList<Task> getTasksFromDataFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(dataFile)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] lineParts = line.split(" \\| ");
                Task task = createTaskFromLine(lineParts, formatter);
                if (task != null) {
                    setTaskStatus(task, lineParts[1]);
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private Task createTaskFromLine(String[] lineParts, DateTimeFormatter formatter) {
        switch (lineParts[0]) {
            case "T":
                return new Todo(lineParts[2]);
            case "D":
                return new Deadline(lineParts[2], LocalDateTime.parse(lineParts[3], formatter));
            case "E":
                return new Event(lineParts[2],
                        LocalDateTime.parse(lineParts[3], formatter),
                        LocalDateTime.parse(lineParts[4], formatter));
            default:
                return null;
        }
    }

    private void setTaskStatus(Task task, String status) {
        if ("1".equals(status)) {
            task.markAsDone();
        } else if ("0".equals(status)) {
            task.markAsNotDone();
        }
    }


    /**
     * Deletes a specific task from the data file by rewriting the file without the specified task.
     *
     * @param taskNumber The index of the task to be deleted from the data file.
     */
    public void deleteFromDataFile(int taskNumber) {
        File tempFile = new File("./data/tmp.txt");
        try {
            FileWriter writer = new FileWriter(tempFile);
            Scanner scanner = new Scanner(dataFile);
            int i = 0;
            String currentLine;
            while (scanner.hasNext()) {
                currentLine = scanner.nextLine();
                if (i == taskNumber) {
                    continue;
                }
                writer.write(currentLine + System.lineSeparator());
                i++;
            }
            writer.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tempFile.renameTo(dataFile);
    }

    /**
     * Appends a new task to the data file.
     *
     * @param task The task to be added to the data file.
     */
    public void appendToDataFile(Task task) {
        try {
            FileWriter writer = new FileWriter(dataFile, true);
            writer.write(task.getDataFormat() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
