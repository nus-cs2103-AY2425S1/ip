package phenex.storage;

import phenex.task.Task;
import phenex.task.TaskList;
import phenex.ui.Ui;
import phenex.util.Parser;

import java.io.File;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Storage {
    /** Encapsulates the filePath in storage */
    protected final Path filePath;
    /** Encapsulates the File in storage */
    protected File file;

    public Storage(Path filePath) {
        this.filePath = filePath;

        if (!Files.exists(this.filePath)) {
            // create file if it doesn't exist
            try {
                Files.createFile(this.filePath);
                Ui.printMemoryInitialisedMessage();
            } catch (IOException e) {
                Ui.printMemoryInitialisingFailureMessage();
                Ui.printExceptionMessage(e);
            }
        }

        this.file = new File(this.filePath.toString());
    }

    public File getFile() {
        return this.file;
    }

    /**
     * Stores tasks from a task list
     *
     * @param taskList, the task list to store tasks from
     */
    public void storeTasksToMemory(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath.toString());
            for (Task task : taskList.getTasks()) {
                String line = Parser.parseTaskInfo(task);
                fileWriter.write(line);
            }
            fileWriter.close();
        } catch (IOException e) {
            Ui.printExceptionMessage(e);
        }
    }
}
