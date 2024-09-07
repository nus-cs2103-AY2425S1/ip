package infinity.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import infinity.infinityexception.InfinityException;
import infinity.task.Deadline;
import infinity.task.Event;
import infinity.task.Task;
import infinity.task.ToDos;
import infinity.tasklist.TaskList;
import infinity.ui.Ui;

/**
 * This class handles the storage of the tasks in a file.
 */
public class Storage {

    /** Filepath Directory for Save file */
    private static final String FILE_DIR_PATH = "../../../../data";
    /** Filename for Save file */
    private static final String FILE_NAME = "save-file.txt";
    /** Filepath for Save file */
    private static final String FILE_PATH = FILE_DIR_PATH + "/" + FILE_NAME;
    /** Delimiter for Save file */
    private static final String DELIMITER = "\0";
    /** Bot reply for issue encountered. */
    private static final String BOT_ERROR_IO =
            "I'm sorry, I'm a noob at this, I can't read the file, can you help me debug? ";
    /** Bot reply for successful save */
    private static final String BOT_SUCCESSFUL_SAVE = "Save Successful, Woohoo!";
    /** Marker for Save file if done */
    public static final String DONE_MARKER = "1";
    /** Marker for Save file if not done */
    public static final String UNDONE_MARKER = "0";

    /**
     * Combines the description of the remainder of the text, if the text includes the delimiter.
     *
     * @param description The array of words in the text, already split with delimiter.
     * @param startIndex The index to start combining the rest of the description, inclusive.
     * @return The combined description as a String.
     */
    private static String combineDescription(String[] description, int startIndex) {
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
     * @throws InfinityException If there are issues reading the file.
     */
    public static ArrayList<Task> readFile() throws InfinityException {
        ArrayList<Task> tasks = new ArrayList<>(TaskList.MAX_SIZE);

        if (!doesFileExist()) {
            return tasks;
        }

        try {
            Scanner file = new Scanner(new File(FILE_PATH));
            while (file.hasNextLine()) {
                String currentLine = file.nextLine();

                String[] words = currentLine.split(DELIMITER);
                Task.TaskTypes taskType = Task.TaskTypes.valueOf(words[0]);
                boolean isDone = words[1].equals(DONE_MARKER);

                switch (taskType) {
                case T:
                    tasks.add(new ToDos(isDone, combineDescription(words, 2)));
                    break;
                case D:
                    tasks.add(new Deadline(isDone, combineDescription(words, 3), words[2]));
                    break;
                case E:
                    tasks.add(new Event(isDone, combineDescription(words, 4), words[2], words[3]));
                    break;
                default:
                    break;
                }
            }
            file.close();
        } catch (IOException | InfinityException e) {
            throw new InfinityException(BOT_ERROR_IO + e.getMessage());
        } catch (NoSuchElementException e) {
            // Do nothing, likely means end of file
        }
        
        assert doesFileExist() : "Save file should exist";
        return tasks;
    }

    /**
     * Creates the save file before reading instead of just reading.
     *
     * @return The tasks in the file as an ArrayList of Task.
     * @throws InfinityException If there are issues reading the file.
     */
    public static ArrayList<Task> checkAndReadFile() throws InfinityException {
        try {
            createFileIfNotExists();
            return readFile();
        } catch (IOException | InfinityException e) {
            throw new InfinityException(Ui.botSays(
                    "I'm sorry, I'm a noob at this, I can't save the file, can you help me debug? "
                            + e.getMessage()));
        }
    }

    /**
     * Saves the tasks into the file.
     *
     * @param tasks The tasks to be saved in an ArrayList of Task.
     * @return The bot output.
     * @throws InfinityException If there is an error writing to the file.
     */
    public static String saveFile(ArrayList<Task> tasks) throws InfinityException {

        try {
            createFileIfNotExists();

            FileWriter file = new FileWriter(FILE_PATH);

            for (Task task : tasks) {
                file.write(task.saveFileFormat(DELIMITER));
                file.write("\n");
            }

            file.close();

            return Ui.botSays(BOT_SUCCESSFUL_SAVE);
        } catch (IOException e) {
            throw new InfinityException(Ui.botSays(BOT_ERROR_IO + e.getMessage()));
        }
    }

    /**
     * Checks if the file and directory exists.
     *
     * @return True if exists, False otherwise.
     */
    private static boolean doesFileExist() {
        File file = new File(FILE_PATH);
        return file.exists() && !file.isDirectory();
    }

    /**
     * Creates the file and directory if they do not exist.
     */
    private static void createFileIfNotExists() throws IOException {
        if (!doesFileExist()) {
            Files.createDirectories(Paths.get(FILE_DIR_PATH));
            Files.createFile(Paths.get(FILE_PATH));
        }
        assert doesFileExist() : "Save file should exist";
    }
}
