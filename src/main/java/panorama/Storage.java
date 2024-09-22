package panorama;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import panorama.exception.InvalidDateException;
import panorama.task.Deadline;
import panorama.task.Event;
import panorama.task.Task;
import panorama.task.Todo;

/**
 * Handles loading and saving of tasks from/to a file.
 */
public class Storage {
    private static final String SAVE_FILE_NAME = "./data.txt";

    /**
     * Loads the task list from the file specified by {@code SAVE_FILE_NAME}.
     * If the file does not exist, it returns an empty {@code List<Task>}.
     *
     * @return A {@code List<Task>} object containing tasks loaded from the file.
     * @throws FileNotFoundException If the file is not found.
     */
    List<Task> loadTasks() throws FileNotFoundException {
        File file = new File(SAVE_FILE_NAME);

        if (!file.exists()) {
            // If file does not exist, return empty list
            return new ArrayList<>();
        }

        assert(file.exists());
        Scanner scanner = new Scanner(file);

        List<Task> tasks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // Note that | is a special char in regex
            String[] tokens = line.split("\\|");

            if (tokens[0].equals("T")) {
                tasks.add(new Todo(tokens[2]));
            } else if (tokens[0].equals("D")) {
                LocalDate date;
                try {
                    date = DateParser.parse(tokens[3]);
                } catch (InvalidDateException e) {
                    // Skip this entry if date is invalid
                    continue;
                }

                tasks.add(new Deadline(tokens[2], date));
            } else if (tokens[0].equals("E")) {
                LocalDate from;
                LocalDate to;
                try {
                    from = DateParser.parse(tokens[3]);
                    to = DateParser.parse(tokens[4]);
                } catch (InvalidDateException e) {
                    // Skip this entry if date is invalid
                    continue;
                }

                tasks.add(new Event(tokens[2], from, to));
            } else {
                // Incorrect input, skip
                continue;
            }

            // Set marked/unmarked correctly
            // Default is unmarked
            boolean isDone = tokens[1].equals("1");
            tasks.get(tasks.size() - 1).setDone(isDone);
        }

        scanner.close();

        return tasks;
    }

    /**
     * Saves the current list of tasks to the file specified by {@code SAVE_FILE_NAME}.
     *
     * @param tasks The {@code List<Task>} to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    void saveTasks(List<Task> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE_NAME));
        for (int i = 0; i < tasks.size(); i++) {
            writer.write(tasks.get(i).toFileString() + "\n");
        }
        writer.close();
    }
}

