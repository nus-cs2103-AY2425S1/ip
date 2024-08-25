package chatkaki;

import chatkaki.tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Represents the storage of the task list.
 */
public class Storage {

    /**
     * Saves the tasks to the file.
     */
    public static void saveTasksToFile() {
        try {
            File file = new File("data/tasks.txt");
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create the directory if it does not exist
                file.createNewFile(); // Create the file if it does not exist
            }
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : TaskList.getTasks()) {
                fileWriter.write(task.fileFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            Ui.printMessage(e.getMessage());
        }
    }

    /**
     * Loads the tasks from the file.
     */
    public static void loadTasksFromFile() {
        try {
            File file = new File("data/tasks.txt");
            if (!file.exists()) {
                Ui.printMessage("Starting a new task list");
                return;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                String[] taskParts = task.split(",");
                TaskType taskType = TaskType.valueOf(taskParts[0]);
                switch (taskType) {
                    case TODO:
                        TaskList.addTask(new Todo(Boolean.parseBoolean(taskParts[1]), taskParts[2]), true);
                        break;
                    case DEADLINE:
                        LocalDateTime dateTime = DateTimeHelper.parseDate(taskParts[3]);
                        TaskList.addTask(new Deadline(Boolean.parseBoolean(taskParts[1]), taskParts[2], dateTime), true);
                        break;
                    case EVENT:
                        LocalDateTime start = DateTimeHelper.parseDate(taskParts[3]);
                        LocalDateTime end = DateTimeHelper.parseDate(taskParts[4]);
                        TaskList.addTask(new Event(Boolean.parseBoolean(taskParts[1]), taskParts[2], start, end), true);
                        break;
                }
            }
        } catch (Exception e) {
            Ui.printMessage(e.getMessage());
        }
    }
}
