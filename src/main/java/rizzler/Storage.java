package rizzler;

import rizzler.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.format.DateTimeParseException;

import java.util.Scanner;

/**
 * Handles reading and writing between TaskLogs and files.
 * Default directory is <code>taskStorage/taskLog.tsv</code>, relative to where <code>Rizzler</code> is run.
 */
public class Storage {
    private static final Path STORAGE_PATH = Paths.get( "taskStorage", "taskLog.tsv");
    private File file;

    protected Storage() {
        File file = new File(STORAGE_PATH.toString());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.isFile()) {
            createFile(file);
        }
        this.file = file;
    }

    /**
     * Reads the tab-separated values file and parses it into a <code>TaskLog</code>.
     * @return <code>TaskLog</code> with all valid tasks read from the file
     */
    public TaskLog getTasks() {
        Scanner scanner;
        TaskLog taskLog = new TaskLog();
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            return taskLog;
        }
        while (scanner.hasNextLine()) {
            String taskTsv = scanner.nextLine();
            try {
                Task newTask = tsvToTask(taskTsv);
                taskLog.addTask(newTask);
            } catch (DateTimeParseException e) {
                // continue on to the next loop
            }
        }
        scanner.close();
        return taskLog;
    }

    /**
     * Writes a <code>TaskLog</code> to a tab-separated values file for storage.
     * @param taskLog Input <code>TaskLog</code> to be written to the file.
     */
    public void storeTasks(TaskLog taskLog) {
        try {
            FileWriter fw = new FileWriter(STORAGE_PATH.toString());
            for (Task task : taskLog.getLog()) {
                fw.write(task.toTsv());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to storage file");
            System.out.println(e.getMessage());
        }

    }

    private static void createFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            // don't create it
            System.out.println("Cannot create storage file");
            System.out.println(e.getMessage());
        }
    }

    private Task tsvToTask(String tsv) throws DateTimeParseException {
        String[] tsvFields = tsv.split("\t");
        Task newTask;
        switch (tsvFields[0]) {
        case "ToDo":
            boolean todoIsDone = Boolean.parseBoolean(tsvFields[1]);
            String todoDesc = tsvFields[2];
            newTask = new ToDo(todoDesc, todoIsDone);
            break;
        case "Deadline":
            boolean deadlineIsDone = Boolean.parseBoolean(tsvFields[1]);
            String deadlineDesc = tsvFields[2];
            String deadlineTime = tsvFields[3];
            newTask = new Deadline(deadlineDesc, deadlineTime, deadlineIsDone);
            break;
        case "Event":
            boolean eventIsDone = Boolean.parseBoolean(tsvFields[1]);
            String eventDesc = tsvFields[2];
            String eventStart = tsvFields[3];
            String eventEnd = tsvFields[4];
            newTask = new Event(eventDesc, eventStart, eventEnd, eventIsDone);
            break;
        default:
            newTask = new ToDo("nullTask");
        }
        return newTask;
    }
}
