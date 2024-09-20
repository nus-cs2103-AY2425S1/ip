package casper.components;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import casper.exceptions.CorruptedSavedTasksException;
import casper.exceptions.CustomIOException;
import casper.exceptions.InvalidDateTimeException;
import casper.tasks.Deadline;
import casper.tasks.Event;
import casper.tasks.ToDo;
import casper.utils.DateTimeUtils;

/**
 * Utility class for managing tasks in saved local file.
 */
public class Storage {
    private static String[] getFileContentsAsArray(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        List<String> lines = new ArrayList<>();

        int lineCount = 0;

        while (s.hasNextLine()) {
            lines.add(s.nextLine());
            lineCount++;
        }
        s.close();
        assert lineCount == lines.size();
        return lines.toArray(new String[0]);
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(System.lineSeparator());
        fw.write(textToAdd);
        fw.close();
    }

    private static void addLineToFile(String filePath, String newLine) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(System.lineSeparator());
        fw.write(newLine);
        fw.close();
    }

    private static void editLineInFile(String filePath, String newText, int lineNumber)
            throws IOException {
        String[] lines = getFileContentsAsArray(filePath);

        lines[lineNumber - 1] = newText;

        StringBuilder content = new StringBuilder();
        for (String line : lines) {
            content.append(line).append("\n");
        }

        writeToFile(filePath, content.toString().trim());
    }
    private static void deleteLineInFile(String filePath, int lineNumber)
            throws IOException {
        String[] lines = getFileContentsAsArray(filePath);

        StringBuilder content = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            if (i != lineNumber - 1) {
                content.append(lines[i]).append("\n");
            }
        }

        writeToFile(filePath, content.toString().trim());
    }

    /**
     * Edits a specific line in a file where tasks are saved.
     *
     * @param filePath The path to the file.
     * @param newText The new text to replace the existing line.
     * @param lineNumber The line number to edit (0-based index).
     * @throws CustomIOException If an I/O error occurs during the operation.
     */
    public static void editTaskInSavedTasks(String filePath, String newText, int lineNumber) throws CustomIOException {
        try {
            editLineInFile(filePath, newText, lineNumber + 1);
        } catch (IOException e) {
            throw new CustomIOException(e.getMessage());
        }
    }

    /**
     * Removes a specific line from a file where tasks are saved.
     *
     * @param filePath The path to the file.
     * @param lineNumber The line number to remove (0-based index).
     * @throws CustomIOException If an I/O error occurs during the operation.
     */
    public static void removeTaskFromSavedTasks(String filePath, int lineNumber) throws CustomIOException {
        try {
            deleteLineInFile(filePath, lineNumber + 1);
        } catch (IOException e) {
            throw new CustomIOException(e.getMessage());
        }
    }

    /**
     * Adds a new task to the end of a file where tasks are saved.
     *
     * @param filePath The path to the file.
     * @param newText The text to add as a new task.
     * @throws CustomIOException If an I/O error occurs during the operation.
     */
    public static void addTaskToSavedTasks(String filePath, String newText, Boolean isFirst) throws CustomIOException {
        try {
            if (isFirst) {
                writeToFile(filePath, newText);
            } else {
                addLineToFile(filePath, newText);
            }
        } catch (IOException e) {
            throw new CustomIOException(e.getMessage());
        }
    }

    /**
     * Adds tasks from a saved file to a TaskList.
     *
     * @param filePath The path to the file.
     * @param taskList The TaskList to which tasks should be added.
     * @throws CustomIOException If an I/O error occurs during the operation.
     * @throws CorruptedSavedTasksException If the file contains corrupted task data.
     */
    public static void addSavedTasksToTaskList(String filePath, TaskList taskList)
            throws CustomIOException, CorruptedSavedTasksException {
        int lineNumber = 1;

        try {
            String[] lines = getFileContentsAsArray(filePath);

            if (lines.length == 0) {
                return;
            }

            for (String line : Arrays.copyOfRange(lines, 1, lines.length)) {
                String[] parts = line.split(" \\| ");
                String eventType = parts[0];
                boolean isDone = parts[1].equals("1");

                switch (eventType) {
                case "todo" -> taskList.addTask(new ToDo(parts[2], isDone));
                case "deadline" -> taskList.addTask(
                            new Deadline(parts[2], DateTimeUtils.convertStringToDateTime(parts[3]), isDone));
                case "event" -> taskList.addTask(
                            new Event(parts[2],
                                    DateTimeUtils.convertStringToDateTime(parts[3]),
                                    DateTimeUtils.convertStringToDateTime(parts[4]),
                                    isDone));
                default -> { }
                }

                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            try {
                File file = new File(filePath);
                assert !file.exists();
                file.createNewFile();
                assert file.exists();
            } catch (IOException ioException) {
                throw new CustomIOException(ioException.getMessage());
            }
        } catch (InvalidDateTimeException e) {
            throw new CorruptedSavedTasksException(lineNumber);
        }
    }

    /**
     * Replaces all existing tasks in the specified task file with the provided new text.
     * This method will overwrite the entire file content with the new task data.
     *
     * @param filePath The path to the file where task data is saved. This file will be completely overwritten.
     * @param newText The new task data to write to the file. This should include all tasks in the desired format.
     * @throws CustomIOException If an I/O error occurs during the write operation, such as issues with file access.
     */
    public static void replaceAllSavedTasks(String filePath, String newText) throws CustomIOException {
        try {
            writeToFile(filePath, "\n" + newText);
        } catch (IOException e) {
            throw new CustomIOException(e.getMessage());
        }
    }
}
