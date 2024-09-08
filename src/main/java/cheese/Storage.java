package cheese;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import cheese.exception.CheeseException;
import cheese.task.Deadline;
import cheese.task.Event;
import cheese.task.Task;
import cheese.task.ToDo;



/**
 * Manipulate file that store tasks information
 */
public class Storage {
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
        assert !this.filePath.isEmpty();
    }

    /**
     * Return file that stores task list. Create one if does not exist
     * @return File
     * @throws IOException In case of IOException
     */
    private File getListFile() throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        return f;
    }

    /**
     * Load tasks from file into an ArrayList
     * @return tasks from file
     * @throws CheeseException I/O exception or Incorrect data format
     */
    public ArrayList<Task> loadTasks() throws CheeseException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = getListFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String data = s.nextLine();
                tasks.add(loadTask(data));
            }
        } catch (IOException | CheeseException e) {
            throw new CheeseException("Load tasks failed." + e.getMessage());
        }
        return tasks;
    }
    private Task loadTask(String s) throws CheeseException {
        String[] data = s.split(",");
        return switch (data[0]) {
        case "T" -> new ToDo(data);
        case "D" -> new Deadline(data);
        case "E" -> new Event(data);
        default -> throw new CheeseException("Incorrect data format");
        };
    }

    /**
     * Add and save new task
     * @param t task to be added
     */
    public void addTask(Task t) throws CheeseException {
        try (FileWriter fw = new FileWriter(getListFile(), true)) {
            fw.write(t.dataString() + System.lineSeparator());
        } catch (IOException e) {
            throw new CheeseException(e.getMessage());
        }
    }

    /**
     * Update task and save to file
     * @param idx idx of task
     * @param tasks tasklist
     * @param delete if delete task
     * @throws CheeseException Missing/Incorrect input
     */
    public void updateTask(int idx, TaskList tasks, boolean delete) throws CheeseException {
        File original;
        File tmp = new File("tmp.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp, true))) {
            original = getListFile();
            for (int i = 0; i < tasks.size(); i++) {
                if (i == idx && delete) {
                    continue;
                }
                bw.append(tasks.get(i).dataString()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new CheeseException(e.getMessage());
        }
        boolean deleted = original.delete();
        boolean renamed = tmp.renameTo(original);
        assert deleted && renamed;
    }
}
