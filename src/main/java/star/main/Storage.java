package star.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import star.exception.StarException;
import star.task.Deadline;
import star.task.Event;
import star.task.Task;
import star.task.Todo;

/**
 * Deals with loading tasks from a file and saving tasks to a file.
 * Allows tasks in the taskList to be saved to a file and retrieved when the application is restarted.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage object with the specified filePath.
     * @param filePath which is the path to where the file is stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the designated file to the taskList.
     * @return a taskList, which is an ArrayList of Task objects loaded from the file.
     * @throws StarException if an I/O exception occurs while reading the file.
     */
    public ArrayList<Task> load() throws StarException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String input;
            while ((input = reader.readLine()) != null) {
                String[] inputs = input.split(" \\| ");

                Task task;
                switch (inputs[0]) {
                case "T":
                    task = new Todo(inputs[2]);
                    break;
                case "D":
                    task = new Deadline(inputs[2], inputs[3]);
                    break;
                case "E":
                    task = new Event(inputs[2], inputs[3]);
                    break;
                default:
                    throw new StarException("uh oh! there was an error during parsing: an unexpected task"
                            + "type was found.");
                }

                if (Integer.parseInt(inputs[1]) == 1) {
                    task.markDone();
                }
                tasks.add(task);
            }

        } catch (FileNotFoundException e) {
            throw new StarException("uh oh! the file of tasks couldn't be found :(");
        } catch (IOException e) {
            throw new StarException("uh oh! an IO exception occurred :(");
        }
        return tasks;
    }

    /**
     * Saves tasks from the taskList to the designated file.
     * @param taskList which is the ArrayList of Task objects to save.
     * @throws StarException if an I/O exception occurs while writing to the file.
     */
    public void save(ArrayList<Task> taskList) throws StarException {
        File fileDir = new File("./data");
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList) {
                writer.write(task.getOutput() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("something went wrong while saving :0 " + e.getMessage());
        }
    }
}
