package Storage;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import Commands.Command;
import Commands.DeadlineCommand;
import Commands.EventCommand;
import Commands.MarkCommand;
import Commands.ToDoCommand;
import TaskList.TaskList;

public class Storage {
    Path filePath;
    TaskList taskList;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

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
                    // Create new empty task list
                    this.taskList = new TaskList(filePath);
                    // Create new file
                    File data = new File(String.valueOf(filePath));
                    data.createNewFile();
                } catch (IOException makeFileIOException) {
                    throw new RuntimeException(makeFileIOException);
                }
            }
        } else {
            try {
                // Create new empty task list
                this.taskList = new TaskList(filePath);
                // Create data directory
                Files.createDirectories(dataPath);
                // Create new file
                File data = new File(String.valueOf(filePath));
                data.createNewFile();
            } catch (IOException makeFileIOException) {
                throw new RuntimeException(makeFileIOException);
            }
        }
        return taskList;
    }

    public Command decodeTaskFromFile(String[] taskLine) {
        String[] commandInput;
        if (taskLine[0].equals("T")) {
            commandInput = new String[] {"todo", taskLine[2]};
            return new ToDoCommand(commandInput);
        } else if (taskLine[0].equals("D")) {
            commandInput = new String[] {"deadline", taskLine[2], "/", taskLine[3]};
            return new DeadlineCommand(commandInput);
        } else {
            commandInput = new String[] {"event", taskLine[2], "/", taskLine[3], "/", taskLine[4]};
            return new EventCommand(commandInput);
        }
    }

    public void readTask(String taskFileLine, TaskList taskList, int taskNumber) {
        String[] lineContent = taskFileLine.split(" \\| ");
        Command command = decodeTaskFromFile(lineContent);
        command.setData(taskList);
        // Do not need to show return messages from executing these tasks
        command.execute();

        // Check if task was marked done
        if (Integer.parseInt(lineContent[1]) == 1) {
            Command markCommand = new MarkCommand(taskNumber);
            markCommand.setData(taskList);
            markCommand.execute();
        }
    }

    public TaskList readFileContents(Path filePath) {
        try {
            int count = 1;
            Scanner fileScanner = new Scanner(filePath);
            while (fileScanner.hasNextLine()) {
                readTask(fileScanner.nextLine(), taskList, count);
                count += 1;
            }
            fileScanner.close();
            return taskList;
        } catch (IOException scannerIOException) {
            throw new RuntimeException(scannerIOException);
        }
    }
}
