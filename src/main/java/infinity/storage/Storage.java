package infinity.storage;

import infinity.infinityexception.InfinityException;
import infinity.task.Deadline;
import infinity.task.Event;
import infinity.task.Task;
import infinity.task.ToDos;
import infinity.tasklist.TaskList;
import infinity.ui.Ui;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class handles the storage of the tasks in a file.
 */
public class Storage {
    
    /** Filepath Directory for Savefile */
    public static final String FILEDIRPATH = "../../../../data";
    /** Filename for Savefile */
    public static final String FILENAME = "save-file.txt";
    /** Filepath for Savefile */
    public static final String FILEPATH = FILEDIRPATH + "/" + FILENAME;
    /** Delimiter for Savefile */
    public static final String DELIMITER = "\0";

    private final Ui botUI;

    /**
     * Combines the description of the remainder of the text, if the text includes the delimiter.
     * 
     * @param description The array of words in the text, already split with delimiter.
     * @param startIndex The index to start combining the rest of the description, inclusive.
     * @return The combined description as a String.
     */
    private String combineDescription(String[] description, int startIndex) {
        StringBuilder combinedDescription = new StringBuilder();

        for (int i = startIndex; i < description.length; i++) {
            combinedDescription.append(description[i]);
            if (i != description.length - 1) {
                combinedDescription.append(DELIMITER);
            }
        }

        return combinedDescription.toString();
    }

    /**
     * Reads the file and returns the tasks in the file.
     * 
     * @return The tasks in the file as an ArrayList of Task.
     */
    public ArrayList<Task> readFile() {
        @SuppressWarnings("unchecked")
        ArrayList<Task> tasks = new ArrayList<>(TaskList.MAXSIZE);

        if (doesFileExist()) {
            try {
                Scanner file = new Scanner(new File(FILEPATH));
                while (file.hasNextLine()) {

                    String currentLine = file.nextLine();

                    String[] words = currentLine.split(DELIMITER);
                    String taskType = words[0];
                    boolean isDone = words[1].equals("1");
                    Task task = null;

                    switch (taskType) {
                    case "T":
                        task = new ToDos(isDone, 
                                combineDescription(words, 2));
                        break;
                    case "D":
                        task = new Deadline(isDone, 
                                combineDescription(words, 3), words[2]);
                        break;
                    case "E":
                        task = new Event(isDone, 
                                combineDescription(words, 4), words[2], words[3]);
                        break;
                    default:
                        break;
                    }

                    if (task != null) {
                        tasks.add(task);
                    }

                }
                file.close();
            } catch (IOException e) {
                botUI.botSays(
                        "I'm sorry, I'm a noob at this, I can't read the file, can you help me debug? " 
                        + e.getMessage());
            } catch (InfinityException e) {
                botUI.botSays(
                        "I'm sorry, I'm a noob at this, I can't read the file, can you help me debug? " 
                        + e.getMessage());
            } catch (NoSuchElementException e) {
                // Do nothing, likely means end of file
            }
        }
        return tasks;
    }

    /**
     * Saves the tasks into the file.
     * 
     * @param tasks The tasks to be saved in an ArrayList of Task.
     * @throws IOException If there is an error writing to the file.
     */
    public void saveFile(ArrayList<Task> tasks) throws IOException {
        createFileIfNotExists();

        FileWriter file = new FileWriter(FILEPATH);

        for (Task task : tasks) {
            file.write(task.saveFileFormat(DELIMITER));
            file.write("\n");
        }

        file.close();

        botUI.botSays("Save Successful, Woohoo!");
    }

    /**
     * Checks if the file and directory exists.
     * 
     * @return True if exists, false otherwise.
     */
    private boolean doesFileExist() {
        File file = new File(FILEPATH);
        return file.exists() && !file.isDirectory();
    }

    /**
     * Creates the file and directory if they do not exist.
     */
    private void createFileIfNotExists() {
        try {
            if (!doesFileExist()) {
                Files.createDirectories(Paths.get(FILEDIRPATH));
                Files.createFile(Paths.get(FILEPATH));
            }
        } catch (IOException e) {
            botUI.botSays(
                    "I'm sorry, I'm a noob at this, I can't create the file, can you help me debug? " 
                    + e.getMessage());
        }
    }

    /**
     * Constructor for the Storage class.
     * 
     * @param botUI The Ui object to interact with the user.
     */
    public Storage(Ui botUI) {
        this.botUI = botUI;
    }
}