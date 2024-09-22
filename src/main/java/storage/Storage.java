package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import exception.StorageException;
import task.Converter;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import tasklist.TaskList;

/**
 * Handles loading tasks from and storing tasks to from file
*/
public class Storage {
    private File file;
    /**
     * Constructor of the storage
     * @param fileName The to load and store tasks
     */
    public Storage(String fileName) {
        this.file = new File(fileName);
    }

    /**
     * Loads the task list from file
     * @return The task list loaded from the file
     * @throws StorageException If the storage file is not found
     */
    public TaskList load() throws StorageException {
        TaskList tasks = new TaskList();
        Scanner scanner;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new StorageException(e.getMessage());
        }

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] args = line.split(" tags: ");
            assert args.length == 1 || args.length == 2 : "The task should be splitted into non-tag part and optionally tag part";
            String withoutTags = args[0];
            String tags = args.length == 2 ? args[1] : "";
            args = withoutTags.split("\\[|\\]\\[|\\] ");
            if (args[1].equals("T")) {
                assert args.length == 4 : "ToDo task should have 4 arguments after split";
                assert args[0].isEmpty() : "The first argument after splitting ToDo should be empty";
                String description = args[3];
                Task task = new ToDo(description);
                if (args[2].equals("X")) {
                    task.mark();
                }
                for (String tag : tags.split(" ")) {
                    task.addTag(tag);
                }
                tasks.add(task);
            } else if (args[1].equals("D")) {
                try {
                    String regex = "\\[|\\]\\[|\\] | \\(by: |\\)";
                    args = withoutTags.split(regex);
                    assert args.length == 5 : "Deadline task should have 5 arguments after split";
                    assert args[0].isEmpty() : "The first argument after splitting Deadline should be empty";
                    String description = args[3];
                    LocalDateTime deadline = Converter.outputToDateTime(args[4]);
                    Task task = new Deadline(description, deadline);
                    if (args[2].equals("X")) {
                        task.mark();
                    }
                    for (String tag : tags.split(" ")) {
                        task.addTag(tag);
                    }
                    tasks.add(task);
                } catch (DateTimeParseException e) {
                    assert false : "The arguments deadline for Deadline should be well-formatted";
                }
            } else {
                assert args[1].equals("E") : "If a task is not ToDo or Deadline, it should be Event";
                try {
                    String regex = "\\[|\\]\\[|\\] | \\(from: | to: |\\)";
                    args = withoutTags.split(regex);
                    String description = args[3];
                    assert args.length == 6 : "A event task should have 6 arguments after splitting";
                    assert args[0].isEmpty() : "The first argument after splitting Event should be empty";
                    LocalDateTime start = Converter.outputToDateTime(args[4]);
                    LocalDateTime end = Converter.outputToDateTime(args[5]);
                    Task task = new Event(description, start, end);
                    if (args[2].equals("X")) {
                        task.mark();
                    }
                    for (String tag : tags.split(" ")) {
                        task.addTag(tag);
                    }
                    tasks.add(task);
                } catch (DateTimeParseException e) {
                    assert false : "The arguments start and end for Event should be well-formatted";
                }
            }
        }
        scanner.close();
        return tasks;
    }
    /**
     * Stores the task into file
     * @param tasks The task list to be stored
     * @throws StorageException When encounters problem when trying writing to file
     */
    public void store(TaskList tasks) throws StorageException {
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            for (int i = 0; i < tasks.size(); ++i) {
                writer.write(tasks.get(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }
    }
}
