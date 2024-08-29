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

public class Storage {
    private Path filePath;
    private TaskList taskList;

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
                } catch (IOException makeFileException) {
                    throw new RuntimeException(makeFileException);
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
            } catch (IOException makeFileException) {
                throw new RuntimeException(makeFileException);
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
        } catch (IOException scannerException) {
            throw new RuntimeException(scannerException);
        }
    }
}
