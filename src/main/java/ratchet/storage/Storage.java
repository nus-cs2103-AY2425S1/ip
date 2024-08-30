package ratchet.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import ratchet.task.DeadlineTask;
import ratchet.task.EventTask;
import ratchet.task.Task;
import ratchet.task.TaskList;
import ratchet.task.TodoTask;

/**
 * Class to handle the storage of the bot.
 */
public class Storage {
    private static final String PATH_TO_DIRECTORY = "data";
    private static final String PATH_TO_TASK_FILE = "data/ratchet.txt";

    /**
     * Loads tasks into task list from specified destination.
     *
     * @param tasks The task list to load the tasks to.
     */
    public void loadTasks(TaskList tasks) {
        File file = new File(PATH_TO_TASK_FILE);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] info = scanner.nextLine().split("\\|");
                String type = info[0];
                switch (type) {
                case "T":
                    tasks.addTask(new TodoTask(info[1], Boolean.parseBoolean(info[2])));
                    break;
                case "D":
                    tasks.addTask(new DeadlineTask(info[1], Boolean.parseBoolean(info[2]),
                            LocalDate.parse(info[3])));
                    break;
                case "E":
                    tasks.addTask(new EventTask(info[1], Boolean.parseBoolean(info[2]),
                            LocalDate.parse(info[3]),
                            LocalDate.parse(info[4])));
                    break;
                default:
                    System.out.println("Unexpected type");
                }
            }
        } catch (FileNotFoundException e) {
            initFile();
        }
    }

    /**
     * Save tasks into specified destination from task list.
     *
     * @param tasks The task list to save the tasks from.
     */
    public void saveTasks(TaskList tasks) {
        try (FileWriter fw = new FileWriter("data/ratchet.txt")) {
            for (Task task : tasks) {
                fw.write(task.toSave());
                fw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Unable to save data!");
        }
    }

    private void initFile() {
        try {
            new File(PATH_TO_DIRECTORY).mkdir();
            new File(PATH_TO_TASK_FILE).createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}
