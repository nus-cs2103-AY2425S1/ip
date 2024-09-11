package sage.storage;

import sage.exception.SageException;
import sage.task.*;


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage class handles the loading and saving of tasks to a file
 */
public class Storage {
    private String filePath;

    /**
     * Constructor to initialise the storage with the given file path
      */
    public Storage(String filepath) {
        this.filePath = filepath;
    }

    /**
     * Loads tasks from the file at the specified file path
     *
     * @return Lists of tasks loaded from the file
     * @throws SageException If there is an error loading the tasks
     */
    public ArrayList<Task> load() throws SageException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);

        try {
            if (!file.exists()) {
                return tasks;
            }
            assert file.isFile();

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tasks.add(parseTask(line));
            }
        } catch (IOException e) {
            throw new SageException("Error loading tasks");
        }
        return tasks;
    }

    /**
     * Save the lists of tasks to the file at the specified file path
     *
     * @param tasks TaskList containing the tasks to be saved
     * @throws SageException If there is an error saving the tasks
     */
    public void saveTasks(ArrayList<Task> tasks) throws SageException{
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Task task : tasks) {
                bufferedWriter.write(task.toFileFormat());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new SageException("Error saving tasks");
        }
    }

    /**
     * Parses a line of text and converts it into a task object
     *
     * @param line The line of text representing the task
     * @return The Task object represented by the input line
     * @throws SageException If the task type is invalid or if the line format is incorrect
     */
    private Task parseTask(String line) throws SageException {
        String parts[] = line.split(" \\| ");
        assert parts.length >= 3;

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
                throw new SageException("Invalid task type");
        }
        if (parts[1].equals("1")) {
            task.markAsDone();
        }

        return task;
    }
}
