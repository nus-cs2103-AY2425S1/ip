package chatbot.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import chatbot.task.Task;

/**
 * Represents the storage functionality of the chatbot
 * Contains methods that either read from or write to the file
 */
public class Storage {
    /** String representing the directory path of the file */
    private String dirPath;
    /** String representing the file name of the file */
    private String fileName;

    /**
     * Constructor for the Storage object, takes in 2 arguments
     *
     * @param dirPath String representing the desired directory path for the file to be in
     * @param fileName String representing the file name of the file
     */
    public Storage(String dirPath, String fileName) {
        this.dirPath = dirPath;
        this.fileName = fileName;
    }

    /**
     * Processes the file into a suitable ArrayList of tasks
     *
     * @return an ArrayList of tasks that represents what the file stores
     */
    public ArrayList<Task> load() {
        ArrayList<Task> existingTasks = new ArrayList<>();
        try {
            Scanner fileScanner = this.getFile();
            while (fileScanner.hasNextLine()) {
                Task newTask = Parser.parseFileLine(fileScanner.nextLine());
                existingTasks.add(newTask);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("File format corrupted");
        }
        return existingTasks;
    }

    /**
     * Searches for the file as specified by the dirPath and fileName
     * If the file or directory does not exist, the method creates one
     *
     * @return a Scanner object that contains the file contents
     * @throws FileNotFoundException Exception thrown if the file source is somehow not found (should not happen)
     */
    private Scanner getFile() throws FileNotFoundException {
        assert this.dirPath != null : "dirPath should not be null";
        assert this.fileName != null : "fileName should not be null";

        File directory = new File(this.dirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File taskListFile = new File(this.dirPath + this.fileName);
        try {
            taskListFile.createNewFile();
        } catch (IOException e) {
            System.out.println("IO Error in creating new file: " + e.getMessage());
        }

        return new Scanner(taskListFile);
    }

    /**
     * Processes and encodes the given TaskList object and writes it to the file
     * Overrides all existing content in the file
     *
     * @param taskList TaskList object that represents the current state of the list of tasks
     */
    public void writeToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.dirPath + this.fileName);
            for (Task task : taskList.getList()) {
                fw.write(task.encode() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }
    }
}
