package killjoy.processing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import killjoy.main.UserInterface;
import killjoy.task.Task;

/**
 * Represents the Storage class of the KillJoy application.
 * Contains methods to load and save tasks.
 */
public class Storage {

    private ProcessTasks processTasks;

    public Storage(ProcessTasks processTasks) {
        this.processTasks = processTasks;
    }

    /**
     * Loads tasks from the file.
     */
    public void loadTasks(String fileName) {
        File file = new File(fileName);
        try {
            readTasksFromFile(file);
        } catch (FileNotFoundException e) {
            try {
                createNewFile();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    private static void createNewFile() throws IOException {
        FileWriter fw = new FileWriter("KillJoy.txt");
        fw.write("");
        fw.close();
    }

    private void readTasksFromFile(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String data = scan.nextLine();
            processTasks.createTasks(data);
        }
        scan.close();
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            writeTasksOnFile(tasks, "KillJoy.txt");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static void writeTasksOnFile(ArrayList<Task> tasks, String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        String currentTasks = "";
        for (Task task : tasks) {
            currentTasks += task.getTaskInfo();
        }
        fw.write(currentTasks);
        fw.close();
    }

    /**
     * Saves a task to the file.
     *
     * @param tasks The task to be saved.
     * @return The list of tasks after saving the task.
     */
    public ArrayList<Task> archiveTasks(ArrayList<Task> tasks) throws RuntimeException {
        try {
            createArchiveFolder("archive");
            String uniqueFileName = generateUniqueFileName("Archive", "archive");
            writeTasksOnFile(tasks, uniqueFileName);
            createNewFile();
            return new ArrayList<>();
        } catch (IOException i) {
            i.printStackTrace();
            throw new RuntimeException("Error in archiving tasks");
        }
    }

    private String generateUniqueFileName(String fileName, String folderName) {
        return folderName + "/" + fileName + (System.currentTimeMillis() / 1000) + ".txt";
    }

    private void createArchiveFolder(String folderName) {
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * Loads tasks from the archive file.
     *
     * @param input The name of the archive file.
     * @return The message to be displayed.
     */
    public String loadTasksFromArchiveFile(String input) {
        String[] inputAsList = input.split(" ");
        if (inputAsList.length == 1) {
            return UserInterface.displayInvalidCommandFormatMessage();
        }
        if (inputAsList.length > 2) {
            return UserInterface.displayInvalidCommandFormatMessage();
        }
        String fileName = inputAsList[1];
        File file = new File("archive/" + fileName);
        try {
            readTasksFromFile(file);
            return UserInterface.displayTaskLoadedMessage();
        } catch (FileNotFoundException e) {
            return UserInterface.displayFileNotFoundMessage();
        }
    }

}
