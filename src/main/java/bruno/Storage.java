package bruno;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import bruno.exceptions.BrunoException;
import bruno.exceptions.FileLoadingException;
import bruno.task.Deadline;
import bruno.task.Event;
import bruno.task.Task;
import bruno.task.ToDo;

/**
 * The Storage class handles the reading and writing of task data
 * to and from a file. It ensures the directory and file for task
 * data storage exist and provides methods to load tasks from a file
 * and update the file with the current task list.
 */
public class Storage {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    private String directoryPath;
    private String filePath;

    /**
     * Constructor for the Storage class.
     *
     * @param directoryPath The directory where the data file is stored.
     * @param filePath The file path for storing task data.
     */
    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    /**
     * Loads the list of tasks from the file specified by filePath.
     * This method reads the file line by line, parses each line to create
     * the appropriate task, and returns an ArrayList of tasks.
     * If there is an issue with the file reading process, it throws
     * a BrunoException.
     *
     * @return An ArrayList containing the tasks loaded from the file.
     * @throws BrunoException If an error occurs while loading the file.
     */
    public ArrayList<Task> loadFromFile() throws BrunoException {
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            ArrayList<Task> taskList = new ArrayList<>();
            while (s.hasNext()) {
                String line = s.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] lineParts = line.split("\\|");
                String type = lineParts[0].trim();
                boolean isDone = lineParts[1].trim().charAt(1) == 'X';
                String description = lineParts[2].trim();

                if (type.equals("T")) {
                    taskList.add(new ToDo(description, isDone));
                } else if (type.equals("D")) {
                    String byString = lineParts[3].substring(lineParts[3].indexOf("by:") + 3).trim();
                    LocalDateTime by = LocalDateTime.parse(byString, formatter);
                    taskList.add(new Deadline(description, by, isDone));
                } else if (type.equals("E")) {
                    String fromString = lineParts[3].substring(6, lineParts[3].indexOf("to")).trim();
                    String toString = lineParts[3].substring(lineParts[3].indexOf("to") + 3).trim();
                    LocalDateTime from = LocalDateTime.parse(fromString, formatter);
                    LocalDateTime to = LocalDateTime.parse(toString, formatter);
                    taskList.add(new Event(description, from, to, isDone));
                } else {
                    System.out.println("There was a problem when loading some task");
                }
            }
            return taskList;
        } catch (IOException e) {
            throw new FileLoadingException();
        }
    }

    /**
     * Updates the task data file with the current list of tasks.
     * This method writes each task in the provided task list to the file,
     * replacing the previous file contents.
     *
     * @param taskList The ArrayList of tasks to be written to the file.
     */
    public void updateFile(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            String listAsString = "";
            for (Task task : taskList) {
                listAsString += task.toString() + "\n";
            }
            fw.write(listAsString);
            fw.close();
        } catch (IOException e) {
            Ui.printErrorMessage(e);
        }
    }

    /**
     * Ensures that the directory for storing the data file exists.
     * If the directory does not exist, this method will create it.
     */
    public void ensureDirectoryExists() {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created to store data file");
            }
        }
    }

    /**
     * Ensures that the task data file exists.
     * If the file does not exist, this method will create a new empty file.
     */
    public void ensureFileExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Data file created");
                }
            } catch (IOException e) {
                Ui.printErrorMessage(e);
            }
        }
    }
}
