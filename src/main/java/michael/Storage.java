package michael;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.tasks = new ArrayList<>();
        this.filePath = filePath;
    }

    /**
     * Creates the corresponding task given a line of text that represents that task.
     *
     * @param line Line of text formatted in a way that represents a task.
     * @return The task corresponding to the text given.
     */
    public Task load(String line) {
        String[] parts = line.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            String s = parts[i];
            parts[i] = s.strip();
        }

        if (parts[0].equals("T")) {
            Task t = new ToDo(parts[2]);
            if (Integer.valueOf(parts[1]) == 1) {
                t.doTask();
            }
            return t;
        } else if (parts[0].equals("D")) {
            Task t = new Deadline(parts[2], parts[3]);
            if (Integer.valueOf(parts[1]) == 1) {
                t.doTask();
            }
            return t;
        } else {
            Task t = new Event(parts[2], parts[3], parts[4]);
            if (Integer.valueOf(parts[1]) == 1) {
                t.doTask();
            }
            return t;
        }
    }

    /**
     * Loads in tasks saved locally to be stored by the ArrayList in the tasks field.
     *
     * @return The ArrayList of tasks saved in the text file at the specified path.
     * @throws IOException If an error occurs with opening the file at the specified path.
     */
    public ArrayList<Task> load() throws IOException {
        File f = new File(this.filePath);
        File parent = f.getParentFile();

        if (parent != null && !parent.exists()) { // create parent directories if they do not exist
            parent.mkdirs();
        }

        if (!f.exists()) { // create file if it does not exist
            f.createNewFile();
        }

        // Read in tasks from saved file
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            Task t = load(s.nextLine());
            tasks.add(t);
        }
        return tasks;
    }

    /**
     * Converts a task into a formatted String to be saved in the external save file.
     *
     * @param task Task to be converted.
     * @return String representing the task.
     */
    public String convertTaskToString(Task task) {
        StringBuilder s = new StringBuilder();

        // Add status of task
        if (task.isDone()) {
            s.append("1 | ");
        } else {
            s.append("0 | ");
        }

        // Add name of task
        s.append(task.getTaskName());

        if (task instanceof ToDo) {
            s.insert(0, "T | ").append("\n");
            return s.toString();
        } else if (task instanceof Deadline) {
            s.insert(0, "D | ");
            Deadline d = (Deadline) task;
            s.append(" | ").append(d.getDeadline()).append("\n");
            return s.toString();
        } else {
            s.insert(0, "E | ");
            Event e = (Event) task;
            s.append(" | ").append(e.getStart()).append(" | ").append(e.getEnd()).append("\n");
            return s.toString();
        }
    }

    /**
     * Saves tasks stored in the tasks field externally by writing to the specified file.
     *
     * @throws IOException If file at the specified path cannot be written to.
     */
    public void save() throws IOException {
        FileWriter writer = new FileWriter(this.filePath);
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String save = convertTaskToString(t);
            writer.write(save);
        }
        writer.close();
    }
}
