package duke.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.additionalparsers.DataParser;
import duke.ui.Notgpt;
/**
 * The {@code Storage} class manages the storage, retrieval, and manipulation of tasks.
 * <p>
 * Tasks are stored in a text file, and this class provides methods for creating,
 * modifying, and deleting tasks. The tasks are loaded from a file on initialization
 * and saved back to the file whenever they are modified.
 * </p>
 */
public class Storage {
    private ArrayList<Task> taskList = new ArrayList<>();
    private Path filePath = Paths.get(".", "data", "data.txt");

    /**
     * Constructs a {@code Storage} object and initializes it by loading tasks from the specified file.
     * <p>
     * If the file exists, tasks are read from it. If not, a new file is created. The tasks are stored in an
     * {@link ArrayList} and can be manipulated through various methods provided by this class.
     * </p>
     */
    public Storage() {
        try {
            Files.createDirectories(filePath.getParent());
            if (Files.exists(filePath)) {
                this.taskList = DataParser.readTasksFromFile(filePath);
                System.out.println("Data found and loaded from: " + filePath.toAbsolutePath());
                System.out.println("*the first word will always be read as the command*");
                Notgpt.lnDiv();
            } else {
                Files.createFile(filePath);
                System.out.println("New Data file successfully created at: " + filePath.toAbsolutePath());
                System.out.println("*the first word will always be read as the command*");
                Notgpt.lnDiv();
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Writes the current list of tasks to the storage file.
     * <p>
     * This method clears the file's contents before writing the updated list of tasks.
     * </p>
     */
    private void writeToFile() {
        try {
            Files.write(filePath, new byte[0]);
            Files.writeString(filePath, this.toString());
        } catch (IOException e) {
            e.printStackTrace();
            try (FileWriter fw = new FileWriter("error.log", true);
                 PrintWriter pw = new PrintWriter(fw)) {
                e.printStackTrace(pw);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            System.out.println("An error occurred. Press Enter to exit.");
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Adds a new {@code Todo} task to the list and writes the updated list to the file.
     *
     * @param s the description of the todo task
     */
    public void todo(String s) {
        taskList.add(new Todo(s));
        this.writeToFile();
    }

    /**
     * Adds a new {@code Event} task to the list and writes the updated list to the file.
     *
     * @param s the description of the event task
     */
    public void event(String s) {
        taskList.add(new Event(s));
        this.writeToFile();
    }

    /**
     * Adds a new {@code Deadline} task to the list and writes the updated list to the file.
     *
     * @param s the description of the deadline task
     */
    public void deadline(String s) {
        taskList.add(new Deadline(s));
        this.writeToFile();
    }

    /**
     * Returns a string representation of all the tasks in the list.
     * <p>
     * Each task is represented by its index in the list, followed by its string representation.
     * </p>
     *
     * @return a string representation of all tasks
     */
    public String toString() {
        String thing = "";
        int j = 1;
        for (int i = 0; i < taskList.size(); i++) {
            thing += j + ". " + taskList.get(i).toString();
            if (i != taskList.size() - 1) {
                thing += "\n";
            }
            j++;
        }
        return thing;
    }

    /**
     * Marks the task at the specified index as completed and writes the updated list to the file.
     *
     * @param i the index of the task to mark as completed
     */
    public void mark(int i) {
        taskList.get(i - 1).complete();
        this.writeToFile();
    }

    /**
     * Unmarks the task at the specified index as not completed and writes the updated list to the file.
     *
     * @param i the index of the task to unmark as not completed
     */
    public void unmark(int i) {
        taskList.get(i - 1).uncomplete();
        this.writeToFile();
    }

    /**
     * Deletes the task at the specified index from the list and writes the updated list to the file.
     *
     * @param i the index of the task to delete
     */
    public void delete(int i) {
        taskList.remove(i - 1);
        this.writeToFile();
    }
    /**
     * Checks if the task is a duplicate of an already pre-existing task inside the arraylist
     *
     * @param Task the new Task being check against the current arraylist of tasks
     */
    private boolean isDuplicate(Task task) {
        return taskList.stream()
                .anyMatch(existingTask -> existingTask.getTask().equals(task.getTask()));
    }

    /**
     * Finds tasks in the list that contain the specified keyword in their description.
     * <p>
     * Returns a string of all matching tasks, each prefixed by its index in the list.
     * </p>
     *
     * @param s the keyword to search for in the task descriptions
     * @return a string of matching tasks, each on a new line
     */
    public String find(String s) {
        String thing = "";
        int j = 1;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTask().contains(s)) {
                thing += j + ". " + taskList.get(i).toString() + "\n";
                j++;
            }
        }
        return thing.trim();
    }
    /**
     * Clears out the entire Storage Arraylist and cleans up any remaining data from the data.txt file as well
     */
    public void clear() {
        taskList.clear();
        this.writeToFile();
    }

    /**
     * Returns the number of tasks currently stored.
     *
     * @return the size of the task list
     */
    public int size() {
        return taskList.size();
    }
}
