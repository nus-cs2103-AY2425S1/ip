package totoro.storage;

import totoro.exception.TotoroException;
import totoro.exception.TotoroFileException;
import totoro.exception.TotoroFileFormatException;
import totoro.task.*;


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Storage} class handles the loading and saving of tasks to and from a file
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path for loading and saving tasks
     *
     * @param filepath The path to the file where tasks are stored
      */
    public Storage(String filepath) {
        this.filePath = filepath;
    }

    /**
     * Loads tasks from the file specified by the {@code filePath}
     *
     * @return Lists of tasks loaded from the file
     * @throws TotoroException If there is an error loading the tasks
     */
    public ArrayList<Task> load() throws TotoroException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);

        try {
            if (!file.exists()) {
                return tasks;
            }
            if (!file.isFile()) {
                throw new TotoroFileFormatException();
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tasks.add(parseTask(line));
            }
        } catch (IOException e) {
            throw new TotoroFileException();
        }
        return tasks;
    }

    /**
     * Save the lists of tasks to the file at the specified file path
     *
     * @param tasks TaskList containing the tasks to be saved
     * @throws TotoroFileException If there is an error saving the tasks
     */
    public void saveTasks(ArrayList<Task> tasks) throws TotoroFileException {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (parentDir != null) {
                parentDir.mkdirs();
            }
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Task task : tasks) {
                bufferedWriter.write(task.toFileFormat());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new TotoroFileException();
        }
    }

    /**
     * Parses a line from the Storage file and converts it into a {@code Task} object
     *
     * @param line The line of text representing the task
     * @return The {@code Task} object represented by the input line
     * @throws TotoroFileFormatException If the task type is invalid or if the line format is incorrect
     */
    private Task parseTask(String line) throws TotoroFileFormatException {
        String parts[] = line.split(" \\| ");
        if (parts.length < 3) {
            throw new TotoroFileFormatException();
        }

        String taskType = parts[0];
        String description = parts[2];
        Task task;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

        switch(taskType) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                LocalDateTime by = LocalDateTime.parse(parts[3], formatter);
                task = new Deadline(description, by);
                break;
            case "E":
                LocalDateTime from = LocalDateTime.parse(parts[3], formatter);
                LocalDateTime to = LocalDateTime.parse(parts[4], formatter);
                task = new Event(description, from, to);
                break;
            default:
                throw new TotoroFileFormatException();
        }
        if (parts[1].equals("1")) {
            task.markAsDone();
        }

        return task;
    }
}
