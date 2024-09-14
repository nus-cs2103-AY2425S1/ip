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
    File dataFile;

    /**
     * Ensures that the data file and its directory exist. If not, they are created.
     */
    void getDataFile() {
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
    ArrayList<Task> getTasksFromDataFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] lineParts = line.split(" \\| ");
            Task task = null;

            switch (lineParts[0]) {
                case "T":
                    task = new Todo(lineParts[2]);
                    break;
                case "D":
                    task = new Deadline(lineParts[2], LocalDateTime.parse(lineParts[3], formatter));
                    break;
                case "E":
                    String[] fromTo = lineParts[3].split(" - ");
                    task = new Event(lineParts[2], LocalDateTime.parse(fromTo[0], formatter), LocalDateTime.parse(fromTo[1], formatter));
                    break;
            }

            if (lineParts[1].equals("1")) {
                task.markAsDone();
            } else if (lineParts[1].equals("0")) {
                task.markAsNotDone();
            }
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Deletes a specific task from the data file by rewriting the file without the specified task.
     *
     * @param taskNumber The index of the task to be deleted from the data file.
     */
    void deleteFromDataFile(int taskNumber) {
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
    void appendToDataFile(Task task) {
        try {
            FileWriter writer = new FileWriter(dataFile, true);
            writer.write(task.getDataFormat() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
