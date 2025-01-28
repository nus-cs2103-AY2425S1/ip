package espresso.storage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import espresso.task.Task;
import espresso.task.TodoTask;
import espresso.task.DeadlineTask;
import espresso.task.EventTask;
import espresso.command.InvalidCommandException;

/**
 * This class is responsible for Handling the loading and saving of tasks to a file
 * It also Manages the interaction between task data and the file system by creating
 * objects of the correct Task Type.
 */

public class Storage {
    private File file;

    /**
     * Constructor for Storage.
     * Initializes a new file with the given file path.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads the tasks from the file into an ArrayList.
     * It creates a new file in case it doesn't already exist.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException              If there is an error accessing the file.
     * @throws ParseException           If the file content is in an invalid format.
     * @throws InvalidCommandException  If an invalid task type is encountered in the file.
     */
    //Solution below inspired by https://github.com/nus-cs2103-AY2425S1/ip/pull/557 with permission
    public ArrayList<Task> load() throws IOException, ParseException, InvalidCommandException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] split = line.split(" \\| ");

            switch (split[0]) {
                case "T":
                    tasks.add(new TodoTask(split[2]));
                    break;
                case "D":
                    tasks.add(new DeadlineTask(split[2], split[3]));
                    break;
                case "E":
                    tasks.add(new EventTask(split[2], split[3], split[4]));
                    break;
                default:
                    throw new InvalidCommandException("Invalid task type: " + split[0]);
            }
        }
        fileScanner.close();
        return tasks;
    }

    /**
     * Saves the tasks from an ArrayList to the file.
     *
     * @param tasks The ArrayList of tasks to be saved to the file.
     * @throws IOException If there is an error writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        PrintWriter printWriter = new PrintWriter(file);
        for (Task task : tasks) {
            printWriter.println(task.toText());
        }
        printWriter.close();
    }
}