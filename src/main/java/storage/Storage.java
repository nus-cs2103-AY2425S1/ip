package storage;

import exceptions.AtlasException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import utils.DateTime;

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

    public Storage(String filepath) {
        this.filepath = filepath;
        this.tasks = new ArrayList<>();
    }

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

    public void save() throws AtlasException {
        try {
            FileWriter fw = new FileWriter(this.filepath);
            fw.write(this.formatTasks());
            fw.close();
        } catch (IOException e) {
            throw new AtlasException("Failed to save. " + e.getMessage());
        }
    }

    public String formatTasks() {
        StringBuilder output = new StringBuilder();
        for (Task task : this.tasks) {
            output.append(task.toFileString()).append('\n');
        }

        return output.toString();
    }
}
