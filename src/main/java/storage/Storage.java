package storage;

import exceptions.BuddyException;
import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving of tasks from and to a file.
 */
public class Storage {
    private final String FILE_PATH;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks are stored and loaded from.
     */
    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Formats a date string into a LocalDateTime object.
     *
     * @param date The date string in the format "d/M/yyyy HHmm".
     * @return The formatted LocalDateTime object.
     * @throws BuddyException If the date string is not in the correct format.
     */
    private static LocalDateTime formatDate(String date) throws BuddyException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new BuddyException("you need to state the date in the format 'd/M/yyyy HHmm'");
        }
    }

    /**
     * Formats a LocalDateTime object into a string in the format "d/M/yyyy HHmm".
     *
     * @param date The LocalDateTime object to be formatted.
     * @return The formatted date string.
     */
    private static String localDateTimeString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return date.format(formatter);
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return An ArrayList containing the tasks loaded from the file.
     * @throws BuddyException If the file is not found or if there is an error parsing the file contents.
     */
    public ArrayList<Task> load() throws BuddyException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            throw new BuddyException("  No tasks found, starting from scratch");
        }

        // Reading previously stored tasks from the file
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split("\\|");
                String taskType = details[0].trim();
                String description = details[2].trim();
                Task task;

                switch (taskType) {
                    case "T":
                        task = new ToDos(description);
                        break;
                    case "D":
                        String deadline = details[3].trim();
                        LocalDateTime formattedDeadline = formatDate(deadline);
                        task = new Deadlines(description, formattedDeadline);
                        break;
                    case "E":
                        String startDate = details[3].trim();
                        String endDate = details[4].trim();
                        LocalDateTime formattedStartDate = formatDate(startDate);
                        LocalDateTime formattedEndDate = formatDate(endDate);
                        task = new Events(description, formattedStartDate, formattedEndDate);
                        break;
                    default:
                        throw new BuddyException("Unknown task type found in file.");
                }

                if (details[1].trim().equals("1")) {
                    task.markAsDone();
                }

                list.add(task);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("    No previous task list found, starting fresh!");
        } catch (BuddyException e) {
            System.out.println("    OOPS!!! The task list file seems to be corrupted... ");
        }

        return list;
    }

    /**
     * Saves the list of tasks to the file specified by the file path.
     *
     * @param list The list of tasks to be saved.
     * @throws BuddyException If an error occurs while saving the tasks to the file.
     */
    public void save(ArrayList<Task> list) throws BuddyException {
        try {
            File file = new File(this.FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : list) {
                String line = "";
                if (task.getTaskType().equals("T")) {
                    line = task.toFileString();
                } else if (task.getTaskType().equals("D")) {
                    Deadlines deadline = (Deadlines) task;
                    line = deadline.toFileString();
                } else if (task.getTaskType().equals("E")) {
                    Events event = (Events) task;
                    int index = task.description.indexOf("(from:");
                    line = String.format("E | %d | %s | %s | %s", task.isDone ? 1 : 0, event.description.substring(0, index).trim(), localDateTimeString(event.start), localDateTimeString(event.end));
                }
                writer.write(line + System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            throw new BuddyException("An error occurred when saving tasks");
        }
    }
}