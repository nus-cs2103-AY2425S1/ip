package noisy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * The Storage class is responsible for loading and saving tasks from and to a file.
 * It ensures that tasks are persisted on the hard disk and can be retrieved when needed.
 */
public class Storage {
    Parser parser = new Parser();
    File file = new File("./data/duke.txt");

    /**
     * Loads tasks from the storage file.
     * If the file does not exist, it will be created.
     * Tasks are read from the file and parsed into {@code Task} objects, which are added to an {@code ArrayList}.
     *
     * @return An {@code ArrayList} of tasks loaded from the file. If the file is empty or does not exist, an empty list is returned.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;

            }Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitString = line.split("\\|");
                String taskType = splitString[0].trim();
                switch (taskType) {
                    case "T":
                        System.out.println("Adding task" + new Todo(splitString[1], Boolean.parseBoolean(splitString[2])));
                        tasks.add(new Todo(splitString[1], Boolean.parseBoolean(splitString[2])));
                        break;
                    case "D":
                        System.out.println("Adding task" + new Deadline(splitString[1], Boolean.parseBoolean(splitString[2]), parser.parseDate(splitString[3].stripLeading())));
                        tasks.add(new Deadline(splitString[1], Boolean.parseBoolean(splitString[2]), parser.parseDate(splitString[3].stripLeading())));
                        break;
                    case "E":
                        tasks.add(new Event(splitString[1], Boolean.parseBoolean(splitString[2]), parser.parseDate(splitString[3]), parser.parseDate(splitString[4])));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(tasks);
        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     * The file will be created if it does not exist. Each task is written to the file in the desired format.
     *
     * @param taskList The {@code ArrayList} of tasks to be saved to the file.
     */
    public void saveTasks(ArrayList<Task> taskList) {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (Task task : taskList) {
                writer.write(task.formatTask()); // Write each task in the desired format
                writer.newLine();
                System.out.println(task.formatTask());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}