package duck.storage;

import duck.command.InvalidCommandException;
import duck.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * duck.storage.Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private final String FILE_PATH;

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    public ArrayList<Task> load() {
        File file = new File(FILE_PATH);
        ArrayList<Task> taskList = new ArrayList<>();

        if (!file.exists()) {
            return taskList;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");
                Task task = switch (parts[0]) {
                    case "T" -> new ToDo(parts[2]);
                    case "D" -> new Deadline(parts[2], LocalDate.parse(parts[3]));
                    case "E" -> new Event(parts[2], parts[3], parts[4]);
                    default -> throw new InvalidCommandException("Invalid task type in file");
                };
                if (parts[1].equals("1")) {
                    task.mark();
                }
                taskList.add(task);
            }
        } catch (IOException | InvalidCommandException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return taskList;
    }

    public void saveTasks(TaskList list) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : list.getList()) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
