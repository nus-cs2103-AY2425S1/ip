package neuro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import neuro.task.Deadline;
import neuro.task.Event;
import neuro.task.Task;
import neuro.task.TaskList;
import neuro.task.Todo;

/**
 * The {@code Storage} class handles the storage logic for the Neuro Chatbot.
 * It provides methods to load Neuro's save-file data and to update it.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the save file where data will be stored, retrieved from or saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the save file data for Neuro.
     *
     * @return a {@code TaskList} object containing the tasks from the save file data
     * @throws FileNotFoundException If file cannot be created nor found
     */
    public TaskList load() throws FileNotFoundException {
        File f = loadOrCreateSaveFile();

        Scanner s = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();

        while (s.hasNext()) {
            String nextLine = s.nextLine();
            String[] taskComponents = nextLine.split(" \\| ");
            String taskType = taskComponents[0];
            String taskIsDone = taskComponents[1];
            Task taskToAdd = getTaskToAdd(taskType, taskComponents, taskIsDone);

            if (taskToAdd != null) {
                taskList.add(taskToAdd);
            }
        }

        return new TaskList(taskList);
    }

    private File loadOrCreateSaveFile() {
        File f = new File(filePath);
        try {
            if (!f.exists()) {
                // Make folder directory if necessary
                f.getParentFile().mkdirs();

                // Create save file if it doesn't exist
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error encountered: " + e);
        }
        return f;
    }

    private Task getTaskToAdd(String taskType, String[] taskComponents, String taskIsDone) {
        Task taskToAdd = null;

        try {
            switch (taskType) {
            case ("T"):
                taskToAdd = new Todo(taskComponents[2]);
                break;
            case ("D"):
                taskToAdd = new Deadline(taskComponents[2], LocalDateTime.parse(taskComponents[3]));
                break;
            case ("E"):
                taskToAdd = new Event(taskComponents[2], taskComponents[3], taskComponents[4]);
                break;
            default:
                break;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
            // Corrupted line in save file
        }

        if (taskIsDone.equals("1") && taskToAdd != null) {
            taskToAdd.markDone();
        }
        return taskToAdd;
    }

    /**
     * Updates the save file data with the tasks given.
     *
     * @param tasks the updated TaskList to be saved
     */
    public void updateTaskFile(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            for (Task task : tasks) {
                fileWriter.write(task.toSaveData() + System.lineSeparator());
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error encountered: " + e);
        }

    }
}
