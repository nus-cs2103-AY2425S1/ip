package sora;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import sora.Tasks.Deadline;
import sora.Tasks.Event;
import sora.Tasks.Task;
import sora.Tasks.TaskList;
import sora.Tasks.ToDo;

/**
 * Storage stores TaskList.
 * It deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    /** File path of the saved file */
    private final String filePath;

    /**
     * Constructs a new Storage.
     *
     * @param filePath File path of the saved file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns String of all Task Details divided by " | ".
     * It is a Helper Function for saveTaskList(TaskList)
     *
     * @param taskList TaskList.
     * @return String of all Task Details divided by " | ".
     */
    private String obtainTaskString(TaskList taskList) {
        return taskList.getTaskList().stream()
                .map(Task::getTaskDetails)
                .map(x -> String.join(" | ", x) + "\n")
                .reduce("", (x, y) -> x + y);
    }

    /**
     * Saves taskList Details in this Storage instance's filePath.
     *
     * @param taskList TaskList.
     */
    public void saveTaskList(TaskList taskList) throws SoraException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            fileWriter.write(obtainTaskString(taskList));
            fileWriter.close();
        } catch (IOException e) {
            throw new SoraException("Sora is unable to write to file\n");
        }
    }

    /**
     * Loads taskList Details from this Storage instance's filePath.
     *
     * @param taskList TaskList.
     * @throws SoraException If line in this Storage instance's filePath is wrongly formatted.
     */
    public void loadTaskList(TaskList taskList) throws SoraException {
        File file = new File(this.filePath);
        try {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    throw new SoraException("Sora is unable to create new file\n");
                }
            }
            Scanner fileScanner = new Scanner(file);
            int lineNumber = 1;
            while (fileScanner.hasNext()) {
                try {
                    loadTaskListHelper(lineNumber, fileScanner.nextLine(), taskList);
                } catch (Exception e) {
                    throw new SoraException("Sora is unable to load from file\n");
                } finally {
                    lineNumber++;
                }
            }
        } catch (FileNotFoundException e) {
            throw new SoraException("Sora is unable to find file\n");
        }
    }

    /**
     * Loads taskList Details from this Storage instance's filePath according to type of Task.
     * It is a Helper Function for loadTaskList(TaskList).
     *
     * @param lineNumber Line number in this Storage instance's filePath.
     * @param fileTaskDetails Formatted task details in this Storage instance's filePath.
     * @param taskList TaskList.
     * @throws SoraException If line in this Storage instance's filePath is wrongly formatted.
     */
    private void loadTaskListHelper(int lineNumber, String fileTaskDetails, TaskList taskList) throws SoraException {
        String[] parsedFileTaskDetails = fileTaskDetails.split(" \\| ", 5);
        switch (parsedFileTaskDetails[0]) {
        case "T":
            if (parsedFileTaskDetails.length != 3) {
                throw new SoraException("Sora is unable to read file from line " + lineNumber);
            }
            taskList.getTaskList().add(new ToDo(parsedFileTaskDetails[2]));
            break;
        case "D":
            if (parsedFileTaskDetails.length != 4) {
                throw new SoraException("Sora is unable to read file from line " + lineNumber);
            }
            taskList.getTaskList().add(new Deadline(parsedFileTaskDetails[2],
                    parsedFileTaskDetails[3]));
            break;
        case "E":
            if (parsedFileTaskDetails.length != 5) {
                throw new SoraException("Sora is unable to read file from line " + lineNumber);
            }
            taskList.getTaskList().add(new Event(parsedFileTaskDetails[2],
                    parsedFileTaskDetails[3],
                    parsedFileTaskDetails[4]));
            break;
        default:
            throw new SoraException("Sora is unable to understand file from line " + lineNumber);
        }
        if (parsedFileTaskDetails[1].equalsIgnoreCase("X")) {
            taskList.getTaskList().get(taskList.getSize() - 1).markAsDone();
        }
    }
}
