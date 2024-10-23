package screwllum.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import screwllum.exception.IllegalFileFormatException;
import screwllum.tasks.Deadline;
import screwllum.tasks.Event;
import screwllum.tasks.Task;
import screwllum.tasks.ToDo;

/**
 * Represents an object that handles the reading and writing of tasks to and from a file.
 */
public class Storage {
    private static String saveFile = "./data/Save.txt";
    private static String archiveFile = "./data/Archive.txt";

    /**
     * Writes the list of tasks to the archival file. Utilised when the user invokes the archive command.
     * Will append to existing archival files.
     *
     * @param taskList The list of tasks to be archived.
     */
    public void archiveTasks(List<Task> taskList) {
        writeToFile(taskList, archiveFile, true);
    }

    /**
     * Writes the list of tasks to the save file. Utilised when the application is closed.
     * Will overwrite existing save files.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void saveTasks(List<Task> taskList) {
        writeToFile(taskList, saveFile, false);
    }

    /**
     * Writes the list of tasks to a file. Each task is written in a format suitable for reloading.
     *
     * @param taskList The list of tasks to be saved to the file.
     * @param filePath The file to be written to.
     * @param isAppendMode Boolean to determine if the file to be written to will be overwritten or appended.
     */
    public void writeToFile(List<Task> taskList, String filePath, boolean isAppendMode) {
        try {
            File file = new File(filePath);
            File parentDirectory = file.getParentFile();

            if (parentDirectory != null && !parentDirectory.exists()) {
                parentDirectory.mkdirs();
            }

            FileWriter fw = new FileWriter(filePath, isAppendMode);
            for (Task task : taskList) {
                fw.write(task.toSaveFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

    /**
     * Loads the tasks from the file into a list. If the file does not exist, it creates a new one.
     * Each line in the file is parsed into a Task object via a call to a helper method parseTask.
     * The tasks are then added to a list of tasks.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs during file operations.
     * @throws IllegalFileFormatException If a line in the file does not match the expected format.
     */
    public List<Task> load() throws IOException, IllegalFileFormatException {
        File file = new File(saveFile);
        List<Task> taskList = new ArrayList<Task>();
        try {
            Scanner sc = new Scanner(file);
            Task task = null;
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                task = parseTask(nextLine);
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            FileWriter fw = new FileWriter(saveFile, true);
            fw.close();
        }
        return taskList;
    }

    /**
     * Parses a line from the file and converts it into a Task object.
     * The line is expected to be in a specific format, which is specified within the implementations of the various
     * tasks. The format utilizes "_" as the delimiter between fields.
     *
     * @param nextLine The line from the file to be parsed.
     * @return A Task object parsed from the line.
     * @throws IllegalFileFormatException If the line format is incorrect or unrecognized.
     */
    public Task parseTask(String nextLine) throws IllegalFileFormatException {
        String[] taskData = nextLine.split("_");
        Task task = null;
        switch (taskData[0]) {
        case "T":
            task = new ToDo(taskData[2]);
            break;
        case "D":
            task = new Deadline(taskData[2], taskData[3]);
            break;
        case "E":
            task = new Event(taskData[2], taskData[3], taskData[4]);
            break;
        default:
            throw new IllegalFileFormatException(nextLine);
        }
        if (taskData[1].equals("1")) {
            task.toggleStatus();
        }
        return task;
    }
}
