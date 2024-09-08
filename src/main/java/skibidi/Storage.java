package skibidi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import skibidi.TaskList.TaskNotFoundException;
import skibidi.task.AbstractTask;
import skibidi.task.AbstractTask.TaskDeserializationException;

/**
 * Handles writing tasks to disk and loading tasks from file.
 */
public final class Storage {
    private String dataPath;

    public Storage(String dataPath) {
        this.dataPath = dataPath;
    }

    /**
     * Save given task list to storage data path. Overrides any existing data on the file.
     */
    public void saveTasksToDisk(TaskList taskList) {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(dataPath);
        try {
            if (file.exists() || file.createNewFile()) {
                FileWriter writer = new FileWriter(file, false);
                for (int i = 1; i <= taskList.size(); i++) {
                    AbstractTask task = taskList.getTask(i);
                    writer.write(task.serialize() + '\n');
                }
                writer.close();
            } else {
                System.out.println("Failed to save changes to file.");
            }
        } catch (TaskNotFoundException | IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Loads list of tasks from file in data path.
     */
    public List<AbstractTask> loadTasksFromDisk() {
        File file = new File(dataPath);
        List<AbstractTask> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                tasks.add(AbstractTask.deserialize(line));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No data file found, task list is empty!");
        } catch (TaskDeserializationException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

}
