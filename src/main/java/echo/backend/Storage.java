package echo.backend;

import echo.EchoException;
import echo.task.TaskList;
import echo.task.TaskType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
/**
 * The Storage class handles the loading and saving of tasks from and to a file.
 * It manages file creation, reading tasks from a file, and writing tasks back to the file.
 */
public class Storage {
    private String filePath;
    private TaskList taskList;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Storage(String filePath) {
        assert !filePath.isEmpty() : "File path should not be an empty string";
        this.filePath = filePath;
        this.taskList = new TaskList();
    }

    /**
     * Loads the tasks from the file specified by filePath.
     * If the file does not exist, it attempts to create a new file.
     *
     * @return the TaskList object containing the tasks loaded from the file
     * @throws EchoException if there is an error loading or creating the file
     */
    public TaskList load() throws EchoException {
        File savedTasks  = new File(filePath);
        Boolean hasSavedTasksFile = savedTasks.exists();

        if (!hasSavedTasksFile) {
            handleNoSavedTasksFile(savedTasks);
        }

        Scanner fileScanner;
        try {
            fileScanner = new Scanner(savedTasks);
        } catch (FileNotFoundException e) {
            System.out.println("'savedTasks.txt' file not found.");
            throw new EchoException();
        }

        readSavedTasks(fileScanner);
        return this.taskList;
    }

    private void handleNoSavedTasksFile(File savedTasks) throws EchoException {
        String parentDirectoryString = savedTasks.getParent();
        Boolean hasParentDirectory = parentDirectoryString != null;
        Boolean hasParentDirectoryFolder = false;
        File parentDirectoryFolder = null;

        if (hasParentDirectory) {
            parentDirectoryFolder = new File(parentDirectoryString);
            hasParentDirectoryFolder = parentDirectoryFolder.exists();
        }

        if (hasParentDirectory && !hasParentDirectoryFolder) {
            parentDirectoryFolder.mkdirs();
        }

        try {
            savedTasks.createNewFile();
        } catch (IOException ex) {
            System.out.println("Cannot create new 'savedTasks.txt' file.");
            ex.printStackTrace();
            throw new EchoException();
        }
    }

    private void readSavedTasks(Scanner fileScanner) {
        String nextLine;
        String[] splitLines;

        while (fileScanner.hasNext()) {
            // Parse file lines
            nextLine = fileScanner.nextLine();
            splitLines = nextLine.split("\\|");
            assert splitLines.length >= 3: "Tasks format in file is incorrect";
            assert !splitLines[0].isEmpty(): "Task type cannot be empty";
            assert !splitLines[1].isEmpty(): "Task status cannot be empty";
            assert !splitLines[2].isEmpty(): "Task description cannot be empty";

            // Assign task fields
            String taskType  = splitLines[0].trim();
            String taskDescription = splitLines[2].trim();

            // Add tasks to taskList
            switch(taskType) {
            case "T":
                taskList.addTask(
                        taskDescription,
                        TaskType.TODO,
                        "");
                break;
            case "D":
                assert !splitLines[3].isEmpty(): "Deadline cannot be empty";
                String deadline = splitLines[3].trim();
                taskList.addDeadline(
                        taskDescription,
                        LocalDate.parse(deadline));
                break;
            case "E":
                assert !splitLines[3].isEmpty(): "Event start/end cannot be empty";
                String startEnd= splitLines[3].trim();
                taskList.addTask(
                        taskDescription,
                        TaskType.EVENT,
                        startEnd);
                break;
            }

            // Assign mark task arguments
            String taskStatus = splitLines[1].trim();
            int taskListIndexToMark = taskList.getNumTasks();

            if (Integer.valueOf(taskStatus) == 1) {
                taskList.markTask(taskListIndexToMark);
            }
        }
    }

    /**
     * Saves the current TaskList to the file specified by filePath.
     * If there is an error writing to the file, it prints an error message.
     */
    public void saveToFile() {
        FileWriter fileWriter =  null;
        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write(taskList.getTasksToSave());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to write to file");
        }
    }
}
