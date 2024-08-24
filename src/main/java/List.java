import java.io.*;
import java.util.ArrayList;

public class List {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor for the List class.
     */
    public List() {
        loadTasks();
    }

    /**
     * Saves tasks to the file.
     * If the file doesn't exist, it is created.
     */
    public void saveTasks() {
        // Create the directory if it doesn't exist
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();  // Create the directory including any necessary parent directories
        }

        // Save the tasks to the file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/tasks.ser"))) {
            oos.writeObject(tasks);
            System.out.println("Tasks have been saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the file.
     * If the file doesn't exist, an empty task list is initialized.
     * If loading fails, an empty task list is initialized.
     * If loading is successful, the tasks are loaded into the task list.
     */
    @SuppressWarnings("unchecked")
    public void loadTasks() {
        // Create the directory if it doesn't exist
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();  // Create the directory including any necessary parent directories
        }

        // Check if the file exists
        File taskFile = new File(dataDir, "tasks.ser");
        if (!taskFile.exists()) {
            System.out.println("No saved tasks found.");
            tasks = new ArrayList<>();  // Initialize an empty task list if the file doesn't exist
            return;
        }

        // Load tasks from the file if it exists
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(taskFile))) {
            tasks = (ArrayList<Task>) ois.readObject();
            System.out.println("Tasks have been loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            tasks = new ArrayList<>();  // Initialize an empty task list if loading fails
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task object that is to be added to the array list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     *  Returns the String representation of the list.
     *
     *  @return String representation of the list.
     */
    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        if (tasks.isEmpty()) {
            return "There are no tasks in your list.";
        }

        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return list.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of the task to be marked as done.
     * @return True if the task is successfully marked as done, false otherwise.
     */
    public boolean markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
            return true;
        } else {
            return false;
        }
    }

    /** Marks a task as not done.
     *
     * @param index Index of the task to be marked as not done.
     * @return True if the task is successfully marked as not done, false otherwise.
     */
    public boolean markTaskAsNotDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of task at the specified index.
     * This is to ensure that the task is not modified unintentionally
     *
     * @param index Index of the task to be returned.
     * @return Task at the specified index.
     */
    public String get(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Returns the size of the list.
     *
     * @return Size of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Deletes a task from the list.
     *
     * @param index Index of the task to be deleted.
     * @return True if the task is successfully deleted, false otherwise.
     */
    public boolean delete(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            return true;
        } else {
            return false;
        }
    }
}
