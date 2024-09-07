package cookie.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import cookie.exception.CookieException;
import cookie.task.Deadline;
import cookie.task.Event;
import cookie.task.Task;
import cookie.task.ToDo;

/**
 * Handles the storage and retrieval of tasks from a file.
 */
public class Storage {

    /**
     * Returns a {@code File} object representing the file where tasks are saved.
     * If the file or its parent directory does not exist, they are created.
     *
     * @return the {@code File} object representing the saved tasks file
     */
    public File fetchFile() {
        File file = new File("./data/cookie.txt");
        File directory = new File(file.getParent());

        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
                return file;
            } else {
                loadFile(file);
                return file;
            }
        } catch (IOException | CookieException e) {
            System.out.println(e.getMessage());
        }
        return file;
    }

    /**
     * Loads tasks from the specified file and converts them into an {@code ArrayList} of {@code Task}.
     *
     * @param file the {@code File} to be loaded
     * @return an {@code ArrayList} of {@code Task} objects parsed from the file
     * @throws FileNotFoundException if the specified file is not found
     * @throws CookieException if the file contains commands or formats that are not implemented
     */
    public ArrayList<Task> loadFile(File file) throws FileNotFoundException, CookieException {
        Scanner fileScanner = new Scanner(file);
        ArrayList<Task> taskArrayList = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();;
            taskArrayList.add(parseFileContent(line));
        }

        return taskArrayList;
    }

    /**
     * Parses a string representing a task from the file and converts it into a {@code Task} object.
     *
     * @param string the string to be parsed, representing a task in the file
     * @return a {@code Task} object, which could be a {@code ToDo}, {@code Deadline}, or {@code Event}
     * @throws CookieException if the string contains an unknown command or an unsupported format
     */
    public Task parseFileContent(String string) throws CookieException {
        String[] parts = string.split(" \\| ");
        String command = parts[0];
        boolean isDone = parts[1].equals("1");
        String task = parts[2];
        String details = parts.length > 3 ? parts[3] : "";

        switch (command) {
        case "T":
            return new ToDo(isDone, task);
        case "D":
            return new Deadline(isDone, task, details);
        case "E":
            String[] eventDetails = details.split("-");
            return new Event(isDone, task, eventDetails[0], eventDetails[1]);
        default:
            throw new CookieException("File contains unknown command");
        }
    }

    /**
     * Saves the provided list of tasks to a file in a specific format.
     *
     * @param taskArrayList the {@code ArrayList} of {@code Task} objects to be saved
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void saveFile(ArrayList<Task> taskArrayList) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./data/cookie.txt"))) {
            for (Task task : taskArrayList) {
                StringBuilder saveIntoFile = new StringBuilder();

                if (task instanceof ToDo) {
                    saveIntoFile.append("T | ");
                } else if (task instanceof Deadline) {
                    saveIntoFile.append("D | ");
                } else if (task instanceof Event) {
                    saveIntoFile.append("E | ");
                }

                saveIntoFile.append(task.getStatus().equals("X") ? "1 | " : "0 | ");
                saveIntoFile.append(task.getDescription());

                if (task instanceof Deadline) {
                    saveIntoFile.append(" | ").append(((Deadline) task).getBy());
                } else if (task instanceof Event) {
                    saveIntoFile.append(" | ").append(((Event) task).getFrom())
                            .append("-").append(((Event) task).getTo());
                }

                writer.write(saveIntoFile.toString());
                writer.write(System.lineSeparator());
            }
        }
    }
}
