package duke;

import java.io.*;
import java.util.ArrayList;

/**
 * Handles saving and loading tasks to and from a file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to a file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an I/O error occurs during saving.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(tasks);
        }
    }

    /**
     * Loads the list of tasks from a file.
     *
     * @return The list of tasks loaded from the file. If the file doesn't exist, returns empty list.
     * @throws IOException If an I/O error occurs during loading.
     * @throws ClassNotFoundException If the class cannot be found.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> loadTasks() throws IOException, ClassNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                tasks = (ArrayList<Task>) ois.readObject();
            }
        }
        return tasks;
    }
}
