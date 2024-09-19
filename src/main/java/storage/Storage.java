package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Handles the reading and writing of tasks to and from a file on the disk.
 * The Storage class is responsible for persisting the state of the task list
 * and restoring it upon initialization.
 */
public class Storage {
    private final String path;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param path the file path where tasks will be stored
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Initializes the task list by reading from the file.
     * If the file does not exist, a new task list is created.
     *
     * @return an ArrayList of tasks read from the file
     */
    public ArrayList<Task> initList() {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("No task file found. Creating new file at " + path);
            return new ArrayList<>();
        }
        System.out.println("Reading task list from " + path);
        return parseList();
    }

    /**
     * Parses the task list from the file.
     * This method reads each line from the file and parses it into a Task object.
     *
     * @return an ArrayList of tasks parsed from the file
     */
    public ArrayList<Task> parseList() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    break;
                }
                list.add(parseTask(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading file " + e.getMessage());
        }
        return list;
    }

    /**
     * Parses a single line from the file into a Task object.
     * The format of the line determines the type of Task created.
     * "td" for todo, "d" for deadline, "e" for event
     *
     * @param line the line from the file representing a task
     * @return the Task object created from the line
     * @throws IOException if the line format is incorrect
     */
    public Task parseTask(String line) throws IOException {
        String[] values = line.split(",");
        Task task;
        switch (values[0]) {
        case "td":
            if (values.length != 3) {
                throw new IOException("Unexpected number of parameters");
            }
            task = new Todo(values[1]);
            if (values[2].equals("y")) {
                task.setDone();
            }
            return task;
        case "d":
            if (values.length != 4) {
                throw new IOException("Unexpected number of parameters");
            }
            task = new Deadline(values[1], LocalDate.parse(values[3]));
            if (values[2].equals("y")) {
                task.setDone();
            }
            return task;
        case "e":
            if (values.length != 5) {
                throw new IOException("Unexpected number of parameters");
            }
            task = new Event(values[1], LocalDate.parse(values[3]), LocalDate.parse(values[4]));
            if (values[2].equals("y")) {
                task.setDone();
            }
            return task;
        default:
            throw new IOException("Unexpected parameter");
        }
    }

    /**
     * Saves the task list to the file.
     * This method writes each task in the list to the file in a specific format.
     *
     * @param list the list of tasks to be saved to the file
     */
    public void saveList(ArrayList<Task> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.path))) {
            for (Task task : list) {
                String str = "";
                str += task.toSaveFormat();
                bw.write(str);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file " + e.getMessage());
        }
    }
}
