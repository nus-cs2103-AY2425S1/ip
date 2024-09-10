package talker;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import talker.task.Task;
import talker.task.TaskList;

/**
 * Represents an object to read inputs and write outputs to file
 */
public class Storage {

    // filepath for file to read/write into
    private Path filePath;
    //directory path for directory file read/write into is in
    private Path directoryPath;


    /**
     * Constructs a Storage object using a directory path and file path
     *
     * @param directoryPath directory path file to read/write into is located
     * @param filePath file path of file to read/write into is located
     */
    public Storage(String directoryPath, String filePath) {
        this.directoryPath = Paths.get(directoryPath);
        this.filePath = Paths.get(filePath);
    }

    /**
     * Writes a list of tasks into the file
     *
     * @param list list of tasks to write
     * @throws TalkerException if unable to write to file
     */
    public void writeFile(TaskList list) throws TalkerException {
        try {
            if (!Files.exists(directoryPath) || !Files.isDirectory(directoryPath)) {
                Files.createDirectory(directoryPath);
            }
            if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
                Files.createFile(filePath);
            }
            FileWriter fileWriter = new FileWriter(filePath.toString());
            list.writeToFile(fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            throw new TalkerException("Unable to write to file. Error occurred: " + e.getMessage());
        }
    }


    /**
     * Reads file in directory path and file path specified into a TaskList
     *
     * @return TaskList object representing all tasks in input file
     * @throws TalkerException if unable to read file
     */
    public TaskList readFile() throws TalkerException {
        ArrayList<Task> tempList = new ArrayList<>();
        try {
            if (Files.exists(directoryPath)
                    && Files.isDirectory(directoryPath)
                    && Files.exists(filePath)
                    && Files.isRegularFile(filePath)) {
                Scanner scanner = new Scanner(filePath);
                while (scanner.hasNext()) {
                    String taskString = scanner.nextLine();
                    tempList.add(Parser.parseTaskFromFile(taskString));
                }
            }
        } catch (IOException e) {
            throw new TalkerException("Unable to read file. Error occurred: " + e.getMessage());
        }
        TaskList outputList = new TaskList();
        outputList.setTasks(tempList);
        return outputList;
    }
}
