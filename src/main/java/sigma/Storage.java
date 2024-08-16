package sigma;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import sigma.exception.SigmaException;
import sigma.exception.SigmaFileException;
import sigma.exception.SigmaFileFormatException;
import sigma.task.Event;
import sigma.task.Deadline;
import sigma.task.Task;
import sigma.task.Todo;

/**
 * Handles loading tasks from and saving tasks to a file.
 */
public class Storage {
    private String filepath;

    /**
     * Creates a Storage instance with the specified file path.
     *
     * @param filepath the path to the file
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return a list of tasks
     * @throws SigmaException if an error occurs while reading the file
     */
    public ArrayList<Task> load() throws SigmaException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(this.filepath);

        try {
            if (!file.exists()) {
                return tasks;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tasks.add(parseTask(line));
            }
        } catch (IOException e) {
            throw new SigmaFileException();
        }

        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks the list of tasks to save
     * @throws SigmaFileException if an error occurs while writing to the file
     */
    public void save(ArrayList<Task> tasks) throws SigmaFileException {
        try {
            File file = new File(filepath);
            file.getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Task task : tasks) {
                bufferedWriter.write(task.stringify());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new SigmaFileException();
        }
    }

    /**
     * Parses a task from a line of text.
     *
     * @param line the line of text
     * @return the parsed task
     * @throws SigmaFileFormatException if the task type is unknown
     */
    public Task parseTask(String line) throws SigmaFileFormatException {
        String[] parts = line.split(" \\| ");
        Task task;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

        switch (parts[0]) {
        case "T":
            task = new Todo(parts[2]);
            break;
        case "D":
            task = new Deadline(parts[2], LocalDateTime.parse(parts[3], formatter));
            break;
        case "E":
            task = new Event(parts[2],
                    LocalDateTime.parse(parts[3], formatter), LocalDateTime.parse(parts[4], formatter));
            break;
        default:
            throw new SigmaFileFormatException();
        }

        if (parts[1].equals("1")) {
            task.markAsDone();
        }

        return task;
    }
}
