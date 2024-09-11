package screwllum.utils;

import screwllum.exception.IllegalFileFormatException;
import screwllum.tasks.Deadline;
import screwllum.tasks.Event;
import screwllum.tasks.Task;
import screwllum.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents an object that handles the reading and writing of tasks to and from a file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the list of tasks to a file. Each task is written in a format suitable for reloading.
     *
     * @param taskList The list of tasks to be saved to the file.
     */
    public void writeToFile(List<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
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
        File file = new File(filePath);
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
            FileWriter fw = new FileWriter(filePath, true);
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
