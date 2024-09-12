package yap.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import yap.task.Task;

public class Storage {

    private String fileName;
    private String directoryPath;
    private String filePath;

    /**
     * Constructor for Storage, creates instance of storage with filepath.
     *
     * @param fileName the name of the file: e.g. data.
     * @param directoryPath the relative directory path ending with a /.
     */
    public Storage(String fileName, String directoryPath) {
        // Currently no code.
        this.fileName = fileName;
        this.directoryPath = directoryPath;
        this.filePath = directoryPath + fileName;
    };

    /**
     * Writes a list of tasks into the data file.
     *
     * @param tasks the list of tasks to write to the file.
     */
    public void writeTasksToFile(ArrayList<Task> tasks) throws IOException {
        makeFolderFileIfNotExists();
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task task : tasks) {
            fileWriter.write(task.convertToFileString());
        }
        fileWriter.close();
    }

    /**
     * Reads the tasks from the saved file
     *
     * @return an ArrayList of tasks based on what was read from the data file.
     * @throws FileNotFoundException if the data file does not exist.
     * @throws BadDataFormatException if the data file is corrupted and has an incorrect string format.
     */
    public ArrayList<Task> readTasksFromFile() throws FileNotFoundException, BadDataFormatException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file); // create a Scanner using the File as the source
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNext()) {
            String taskString = scanner.nextLine();
            Task task = Task.taskStringToTask(taskString);
            tasks.add(task);
        }
        return tasks;
    }

    private void makeFolderFileIfNotExists() {
        String folderPath = directoryPath;
        String filePath = this.filePath;

        File folder = new File(folderPath);

        if (!folder.exists()) {
            if (!folder.mkdirs()){
                System.out.println("The data file does not exist, and the directory /data was not successfully created!");
            }
        }

        File file = new File(filePath);

        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    System.out.println("The file did not exist and there was a problem creating it.");
                }
            } catch (IOException exception) {
                System.out.println("The file did not exist and there was a problem creating it!");
            }
        }
    }
}
