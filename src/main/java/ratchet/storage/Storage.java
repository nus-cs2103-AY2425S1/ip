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
    private static final int DATA_FIRST = 0;
    private static final int DATA_SECOND = 1;
    private static final int DATA_THIRD = 2;
    private static final int DATA_FOURTH = 3;
    private static final int DATA_FIFTH = 4;

    /**
     * Loads tasks into task list from specified destination.
     *
     * @param tasks The task list to load the tasks to.
     */
    public void loadTasks(TaskList tasks) {
        File file = new File(PATH_TO_TASK_FILE);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                processNextLine(line, tasks);
            }
        } catch (FileNotFoundException e) {
            initFile();
        }
    }

    /**
     * Saves tasks into specified destination from task list.
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

    /**
     * Initialises data file to save if not created.
     */
    private void initFile() {
        try {
            new File(PATH_TO_DIRECTORY).mkdir();
            new File(PATH_TO_TASK_FILE).createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Processes the line read from the scanner
     *
     * @param line  The line read from the scanner.
     * @param tasks The task list to load the tasks to.
     */
    private void processNextLine(String line, TaskList tasks) {
        String[] info = line.split("\\|");
        String type = info[DATA_FIRST];
        assert !type.isEmpty() : "type should not be empty";
        switch (type) {
        case "T":
            tasks.addTask(new TodoTask(info[DATA_SECOND], Boolean.parseBoolean(info[DATA_THIRD])));
            break;
        case "D":
            tasks.addTask(new DeadlineTask(info[DATA_SECOND], Boolean.parseBoolean(info[DATA_THIRD]),
                    LocalDate.parse(info[DATA_FOURTH])));
            break;
        case "E":
            tasks.addTask(new EventTask(info[DATA_SECOND], Boolean.parseBoolean(info[DATA_THIRD]),
                    LocalDate.parse(info[DATA_FOURTH]), LocalDate.parse(info[DATA_FIFTH])));
            break;
        default:
            System.out.println("Unexpected type");
        }
    }
}
