package victor.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import victor.commands.Command;
import victor.commands.DeadlineCommand;
import victor.commands.EventCommand;
import victor.commands.MarkCommand;
import victor.commands.ToDoCommand;
import victor.tasklist.TaskList;

/**
 * Storage class that handles reading from the file where task data is stored. Has the
 * relative file path where data is stored and the task list of Task objects.
 */
public class Storage {
    private Path filePath;
    private TaskList taskList;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks and creates, if necessary, necessary directories and files to save and load
     * tasks from. Creates a TaskList object to use for the entire program's tasks.
     * @return A TaskList object that is either blank, if no previous file existed or
     *     there were no saved tasks, or is populated with data loaded from the save file.
     */
    public TaskList load() {
        Path dataPath = filePath.getParent();
        // Check if directory for txt file exists
        if (Files.exists(dataPath)) {
            // Check if file exists
            if (Files.exists(filePath)) {
                // Load from storage
                this.taskList = new TaskList(filePath);
                this.taskList = readFileContents(filePath);
            } else {
                try {
                    createFileAndTaskList();
                } catch (IOException makeFileException) {
                    makeFileException.printStackTrace();
                }
            }
        } else {
            try {
                createDirectoryAndTaskList(dataPath);
            } catch (IOException makeFileException) {
                makeFileException.printStackTrace();
            }
        }
        return taskList;
    }

    /**
     * Creates a new file and a task list for keeping track of data, used if no file exists
     * @throws IOException if problem creating the new file
     */
    private void createFileAndTaskList() throws IOException {
        // Create new file
        File data = new File(String.valueOf(filePath));
        data.createNewFile();
        this.taskList = new TaskList(filePath);
    }

    private void createDirectoryAndTaskList(Path dataPath) throws IOException {
        // Create data directory
        Files.createDirectories(dataPath);
        // Create new file
        File data = new File(String.valueOf(filePath));
        data.createNewFile();
        this.taskList = new TaskList(filePath);
    }

    /**
     * Decodes the text from the save file and re-formats it into a String array that matches
     * format required by Command and Task constructors.
     * @param taskLine An array of strings from splitting each line of the save file by spaces.
     * @return A Command object with the details of the task.
     */
    public Command decodeTaskFromFile(String[] taskLine) {
        String[] commandInput;
        try {
            switch (taskLine[0]) {
            case "T" -> {
                commandInput = new String[]{"todo", taskLine[2]};
                return new ToDoCommand(commandInput);
            }
            case "D" -> {
                commandInput = new String[]{"deadline", taskLine[2], "/", taskLine[3]};
                return new DeadlineCommand(commandInput);
            }
            case "E" -> {
                commandInput = new String[]{"event", taskLine[2], "/", taskLine[3], "/", taskLine[4]};
                return new EventCommand(commandInput);
            }
            default -> {
                // non-standard command type
                return new Command(new String[]{});
            }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // some error in input
            return new Command(new String[] {});
        }
    }

    /**
     * Reads an individual line from the input file, splits it, and calls the decodeTaskFromFile
     * function to create a Command object from the String input. Constructs the task list used
     * by the program from the lines saved in the data save file. Checks if each of the tasks saved
     * are completed and marks as completed if so.
     * @param taskFileLine A string read from the data save file.
     * @param taskList The task list object used to keep track of objects for the whole program.
     * @param taskNumber The task number of the line being read, as decided by its order in the data save file.
     */
    public void readTask(String taskFileLine, TaskList taskList, int taskNumber) {
        String[] lineContent = taskFileLine.split(" \\| ");
        Command command = decodeTaskFromFile(lineContent);
        command.setData(taskList);
        // Do not need to show return messages from executing these tasks
        command.execute();

        // Minimum valid command length in encoding is 3 for To Do task
        if (lineContent.length < 3) {
            return;
        }
        // Check if task was marked done
        if (Integer.parseInt(lineContent[1]) == 1) {
            Command markCommand = new MarkCommand(taskNumber);
            markCommand.setData(taskList);
            markCommand.execute();
        }
    }

    /**
     * Reads all the task lines with the data save file as input and calls the readTask function
     * to process and construct the TaskList for the entire program.
     * @param filePath The relative file path (relative to project root directory) where data is saved.
     * @return The populated TaskList preloaded with tasks that were saved at the given file path.
     */
    public TaskList readFileContents(Path filePath) {
        try {
            int count = 1;
            Scanner fileScanner = new Scanner(filePath);
            while (fileScanner.hasNextLine()) {
                readTask(fileScanner.nextLine(), taskList, count);
                count += 1;
            }
            fileScanner.close();
            // Write processed task list back to file - overwrites any lines with errors
            taskList.writeToFile(filePath, true);
            return taskList;
        } catch (IOException scannerException) {
            // In case of read error, return blank taskList
            return new TaskList(filePath);
        }
    }
}
