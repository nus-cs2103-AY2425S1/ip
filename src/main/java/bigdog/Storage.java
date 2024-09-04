package bigdog;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Storage {

    /** The file path where the tasks are stored and loaded from. */
    private final String filePath;

    /**
     * Constructs a Storage object.
     * Initializes the file path where the tasks will be stored.
     *
     * @param filePath the file path to save and load tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the current list of tasks to the file.
     * Each task is converted into string format and written to the file.
     *
     * @param taskList the list of tasks to be saved.
     * @throws BigdogException if there is an error during the saving process.
     */
    public void save(ArrayList<Task> taskList) throws BigdogException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            for (Task task: taskList) {
                String line = getString(task);
                writer.write(line + "\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Storage Error: You do not have a .txt file to store my memory! File Path: " + this.filePath);
        } catch (IOException e) {
            System.out.println("Storage Error: IO Error " + e);
        }
    }

    /**
     * Converts a Task object into its string representation.
     *
     * @param task the task to be converted to string format.
     * @return the string representation of the task.
     * @throws BigdogException if the task type is not recognized.
     */
    private static String getString(Task task) throws BigdogException {
        String line = "";
        if (task.isMarked()) {
            line += "X | ";
        } else {
            line += "O | ";
        }
        if (task instanceof Todo) {
            line += "T | ";
            line += task.getDescription();
        } else if (task instanceof Deadline) {
            line += "D | ";
            line += task.getDescription();
        } else if (task instanceof Event){
            line += "E | ";
            line += task.getDescription();
        } else {
            throw new BigdogException("Storage Error: Oops no task detected!");
        }
        return line;
    }

    /**
     * Loads the list of tasks from the file.
     * Each line in the file is converted into a Task object and added to the list.
     *
     * @return an ArrayList of tasks loaded from the file.
     * @throws BigdogException if there is an error during the loading process, such as file corruption or file not found.
     */
    public ArrayList<Task> load() throws BigdogException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.charAt(0) == 'X') {
                    list.add(Task.of(line.substring(4), true));
                } else if (line.charAt(0) == 'O') {
                    list.add(Task.of(line.substring(4), false));
                } else {
                    throw new BigdogException("Storage Error: data file is corrupted!");
                }
            }
            reader.close();
            return list;
        } catch (FileNotFoundException e) {
            throw new BigdogException("Storage Error: You do not have a .txt file to read! File Path: " + this.filePath);
        } catch (IOException e) {
            throw new BigdogException("Storage Error: IO Error: " + e.getMessage());
        }
    }
    
}
