package edith;

import edith.task.Task;
import edith.task.ToDo;
import edith.task.Deadline;
import edith.task.Event;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles the reading and writing of tasks to and from a file.
 * It provides methods to save a list of tasks to a file and load tasks from a file,
 * ensuring persistence of task data between sessions of the chatbot application.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file where tasks are saved and loaded.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to the specified file.
     *
     * @param listOfTasks The ArrayList of Task objects to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(ArrayList<Task> listOfTasks) throws IOException {
        File file = new File(this.filePath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (Task task : listOfTasks) {
            String typeOfTaskString = task.typeOfTaskString();
            String statusString = task.statusString();
            String taskString = task.savedTaskString();

            writer.write(typeOfTaskString + "| " + statusString + "| " + taskString);
            writer.newLine();
        }
        writer.close();
    }

    /**
     * Loads the list of tasks from the specified file.
     * If the file does not exist, it creates a new file and returns an empty task list.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws IOException If an I/O error occurs while reading from the file.
     * @throws EdithException If the file contains corrupted data or the data format is invalid.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        File file = new File(this.filePath);
        File directory = new File(file.getParent());

        if (!directory.exists()) {
            directory.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
            return listOfTasks;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] taskData = line.split("\\|");
            String typeOfTaskString = taskData[0].trim();
            String statusString = taskData[1].trim();
            String taskString = taskData[2].trim();
            Task task;

            task = loadHelper(typeOfTaskString, taskString);

            if (statusString.equals("[X]")) {
                task.markTaskDone();
            }

            listOfTasks.add(task);
        }
        return listOfTasks;
    }

    /**
     * A helper method that reconstructs a Task object based on the task type and task string.
     * This method handles different task types such as ToDo, Deadline, and Event.
     *
     * @param typeOfTaskString The string representing the type of task (e.g., "[T]", "[D]", "[E]").
     * @param taskString The string containing task details specific to the task type.
     * @return A Task object reconstructed from the task string.
     * @throws EdithException If the task type is unrecognized or the task data is corrupted.
     */
    public Task loadHelper(String typeOfTaskString, String taskString) throws EdithException {
        Task task;

        switch (typeOfTaskString) {
        case "[T]":
            task = new ToDo(taskString);
            break;
        case "[D]":
            String[] deadlineParts = taskString.split(" /by ");
            String deadlineTask = deadlineParts[0].trim();
            String dueDate = deadlineParts[1].trim();
            task = new Deadline(deadlineTask, dueDate);
            break;
        case "[E]":
            String[] eventParts = taskString.split(" /from | /to ");
            String eventTask = eventParts[0].trim();
            String startTime = eventParts[1].trim();
            String endTime = eventParts[2].trim();
            task = new Event(eventTask, startTime, endTime);
            break;
        default:
            throw new EdithException("An error occurred while parsing the Edith.task list. Data might be corrupted.", 1);
        }

        return task;
    }
}
