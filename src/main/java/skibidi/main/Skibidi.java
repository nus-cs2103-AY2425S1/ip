package skibidi.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import skibidi.command.Command;
import skibidi.exceptions.SkibidiException;
import skibidi.task.Task;
import skibidi.utils.Parser;
import skibidi.utils.Storage;
import skibidi.utils.TaskList;

/**
 * Represents the main class of the Skibidi program.
 */
public class Skibidi {

    private static TaskList tasks = new TaskList();
    private Storage storage;

    /**
     * Creates a Skibidi program.
     *
     * @param filePath File path of the storage file
     */
    public Skibidi(String filePath) {
        storage = new Storage(filePath);
    }

    /**
     * Loads the tasks from the storage file.
     *
     * @return The tasks loaded from the storage file.
     */
    public String loadTasks() {
        try {
            StringBuilder sb = new StringBuilder();
            tasks = storage.load();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = Parser.parseTask(tasks.getTask(i).writeToFile());
                sb.append(task).append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            return "File not found: " + e.getMessage();
        } catch (SkibidiException e) {
            return "Error loading file: " + e.getMessage();
        }
    }
    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(storage, tasks);
        } catch (DateTimeParseException e) {
            return "Error: Invalid date format. Please enter date in yyyy-mm-dd format.";
        } catch (IOException e) {
            return "Error writing to file: " + e.getMessage();
        } catch (NumberFormatException e) {
            return "Error: Task number must be an integer.";
        } catch (SkibidiException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        //do nothing
    }
}
