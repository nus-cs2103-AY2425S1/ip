package oyster.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import oyster.exceptions.ParseException;
import oyster.tasks.TaskList;

/**
 * Class that handles saving and loading.
 */
public class Storage {
    private static final String SAVE_FILE_NAME = "oyster.txt";
    private static final Path SAVE_PATH = Paths.get(".", "save-data", SAVE_FILE_NAME);

    private static boolean directoryExists() {
        return Files.exists(SAVE_PATH);
    }

    /**
     * Loads the TaskList.
     *
     * @return TaskList loaded from SAVE_PATH storage.
     * @throws ParseException If parsing goes wrong.
     */
    public static TaskList loadTaskList() throws ParseException {
        if (!directoryExists()) {
            createSave();
        }

        try {
            return Parser.parseTaskList(Files.readString(SAVE_PATH));
        } catch (Exception e) {
            throw new ParseException();
        }
    }

    private static void createSave() {
        try {
            Files.createDirectories(SAVE_PATH.getParent());
            Files.write(SAVE_PATH, "".getBytes());
        } catch (Exception e) {
            // TODO fatal error
        }
    }

    private static void writeSave(String text) {
        if (!directoryExists()) {
            createSave();
        }

        try {
            Files.write(SAVE_PATH, text.getBytes());
        } catch (Exception e) {
            // TODO saving error
        }
    }

    /**
     * Saves the TaskList.
     *
     * @param taskList The TaskList to be saved into SAVE_PATH Storage.
     */
    public static void saveTaskList(TaskList taskList) {
        try {
            writeSave(Parser.composeTaskList(taskList));
        } catch (Exception e) {
            // TODO saving error
        }
    }
}
