package cstwooneohthree.glados.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import cstwooneohthree.glados.tasks.Deadline;
import cstwooneohthree.glados.tasks.Event;
import cstwooneohthree.glados.tasks.Task;
import cstwooneohthree.glados.tasks.Todo;

/**
 * Storage class to load and store data in hard disk
 *
 * @author jayjay19630
 */
public class Storage {

    /* File path to store task list data */
    private static final String FILE_PATH = "./data/tasks.txt";

    /**
     * Saves tasks in a separate data folder.
     *
     * @param taskList Task list to be saved in data.
     */
    public static void saveTasks(ArrayList<Task> taskList) {
        File file = new File(FILE_PATH);
        File directory = file.getParentFile();

        if (!directory.exists() && directory != null) {
            directory.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : taskList) {
                writer.write(task.getTaskTypeIcon() + " | "
                        + (task.getIsDone() ? "1" : "0") + " | "
                        + task.getSaveFormat() + "\n");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Loads tasks from existing data file.
     * If data does not exists, returns empty array list.
     *
     * @return Array list of tasks.
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] inputs = line.split(" \\| ");
                String taskType = inputs[0];
                boolean isDone = inputs[1].equals("1");
                Task task = null;
                switch (taskType) {
                case "[T]":
                    task = new Todo(inputs[2]);
                    break;
                case "[D]":
                    task = new Deadline(inputs[2], LocalDate.parse(inputs[3]));
                    break;
                case "[E]":
                    task = new Event(inputs[2], LocalDate.parse(inputs[3]), LocalDate.parse(inputs[4]));
                    break;
                default:
                }

                if (task != null) {
                    if (isDone) {
                        task.mark();
                    }
                    taskList.add(task);
                }
            }
            return taskList;
        } catch (DateTimeParseException e) {
            Ui.initialise("File corrupted. Starting fresh...");
            return new ArrayList<>();
        } catch (FileNotFoundException e) {
            Ui.initialise("No saved tasks found. Starting fresh...");
            return new ArrayList<>();
        } catch (IOException e) {
            Ui.initialise("Unable to access data. Starting fresh...");
            return new ArrayList<>();
        }
    }
}
