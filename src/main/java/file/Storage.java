package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import errorhandling.ReginaException;
import tasks.DeadlinesTask;
import tasks.EventsTask;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDosTask;

/**
 * The FileSaver class manages the saving and loading of tasks to and from a persistent storage file.
 * It provides methods to read saved tasks and save the current task list in a specified format.
 */
public class Storage {
    /**
     * The file path where tasks are saved.
     */
    private static final String FILE_PATH = "./data/savedData.txt";

    /**
     * Reads the saved task data from the specified file and creates a TaskList populated with those tasks.
     *
     * @return A TaskList containing the tasks read from the saved data file.
     * @throws FileNotFoundException If the specified file does not exist.
     * @throws ReginaException If there is a format issue while reading the tasks from the file.
     */
    public static TaskList readSavedData() throws FileNotFoundException, ReginaException {
        TaskList listOfTasks = new TaskList();
        File f = new File(FILE_PATH); // Create a File for the given file path

        try (Scanner s = new Scanner(f)) {
            // Create a Scanner using the File as the source
            while (s.hasNextLine()) {
                listOfTasks.add(readLine(s.nextLine())); // Add the tasks into the list
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating a new file: " + FILE_PATH);
            try {
                f.createNewFile(); // Create a new file
            } catch (IOException ioe) {
                System.out.println("Unable to create file. Please check permissions and restart me.");
            }
        }

        return listOfTasks;
    }

    /**
     * Saves the current task list to the specified data file.
     *
     * @param latestList The string representation of the tasks to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
    public static void saveData(String latestList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(latestList);
        fw.close();
    }

    /**
     * Reads a line from the saved data and creates a corresponding Task object.
     *
     * @param line The input line representing a task in a saved format.
     * @return A Task object corresponding to the line read.
     * @throws ReginaException If the line format is invalid or unrecognized.
     */
    public static Task readLine(String line) throws ReginaException {
        Task task;
        String[] taskParts = line.split("\\s*\\|\\s*");
        String taskType = taskParts[0].trim();
        boolean isMarked = taskParts.length > 1 && taskParts[1].equals("X");
        String taskDescription = taskParts.length > 2 ? taskParts[2].trim() : "";

        switch (taskType) {
        case "T" -> task = new ToDosTask(taskDescription);
        case "D" -> {
            String taskDeadline = taskParts[3];
            task = new DeadlinesTask(taskDescription, taskDeadline);
        }
        case "E" -> {
            String taskStartTime = taskParts[3];
            String taskEndTime = taskParts[4];
            task = new EventsTask(taskDescription, taskStartTime, taskEndTime);
        }
        default -> throw new ReginaException("Invalid task type detected.");
        }

        if (isMarked) {
            task.checkTask(); // ensures that tasks which are checked are marked correctly
        }
        return task;
    }
}
