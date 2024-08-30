package atlas.storage;

import atlas.exceptions.AtlasException;
import atlas.tasks.Deadline;
import atlas.tasks.Event;
import atlas.tasks.Task;
import atlas.tasks.Todo;
import atlas.utils.DateTime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    private final String filepath;
    private final ArrayList<Task> tasks;

    /**
     * Initialises Storage.
     *
     * @param filepath The filepath of the file where tasks will be loaded from and saved to.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.tasks = new ArrayList<>();
    }

    /**
     * Loads tasks from a previously saved list of tasks.
     *
     * @return ArrayList<Task> The tasks loaded from a previously saved list of tasks or an empty ArrayList.
     * @throws AtlasException The exception to be thrown in the event of any error.
     */
    public ArrayList<Task> load() throws AtlasException {
        try {
            File file = new File(this.filepath);
            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdir();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String regex = Pattern.quote(" | ");
                String[] sections = scanner.nextLine().split(regex);
                this.addTask(sections);
            }

        } catch (FileNotFoundException e) {
            throw new AtlasException("No saved file.");
        } catch (IOException e) {
            throw new AtlasException(e.getMessage());
        }

        return this.tasks;
    }

    /**
     * Adds a task read from the file into the tasks list.
     *
     * @param sections The array containing information about the task saved in the file.
     */
    public void addTask(String[] sections) {
        Task task;
        if (sections[0].equals("T")) {
            task = new Todo(sections[2]);
        } else if (sections[0].equals("D")) {
            task = new Deadline(sections[2], DateTime.formatDate(sections[3]));
        } else {
            String[] dateTimes = sections[3].split(" to ");
            task = new Event(sections[2], DateTime.formatDate(dateTimes[0]), DateTime.formatDate(dateTimes[1]));
        }

        if (sections[1].equals("1")) {
            task.setIsDone();
        }

        this.tasks.add(task);
    }

    /**
     * Saves tasks to file.
     *
     * @throws AtlasException The exception to be thrown in the event of any error.
     */
    public void save() throws AtlasException {
        try {
            FileWriter fw = new FileWriter(this.filepath, false);
            fw.write(this.formatTasks());
            fw.close();
        } catch (IOException e) {
            throw new AtlasException("Failed to save. " + e.getMessage());
        }
    }

    /**
     * Formats and combines all tasks into an output that can be saved in a file.
     *
     * @return String The output that can be saved in a file.
     */
    public String formatTasks() {
        StringBuilder output = new StringBuilder();
        for (Task task : this.tasks) {
            output.append(task.toFileString()).append('\n');
        }

        return output.toString();
    }
}
