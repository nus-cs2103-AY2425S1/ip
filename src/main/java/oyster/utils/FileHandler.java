package oyster.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import oyster.commands.Command;
import oyster.tasks.Task;
import oyster.tasks.TaskList;

public class FileHandler {
    private static final String SAVE_FILE_NAME = "oyster.txt";
    private static final Path SAVE_PATH = Paths.get(".","save-data", SAVE_FILE_NAME);

    private static boolean directoryExists() {
        return Files.exists(SAVE_PATH);
    }

    public static TaskList loadTaskList() {
        if (!directoryExists()) createSave();

        try {
            return Parser.parseTaskList(Files.readString(SAVE_PATH));
        } catch (IOException e) {
            // TODO corrupted
            return new TaskList();
        }
    }

    public static void createSave() {
        try {
            Files.createDirectories(SAVE_PATH.getParent());
            Files.write(SAVE_PATH, "".getBytes());
        } catch (IOException e) {
            // TODO fatal error
        }
    }

    private static void writeSave(String text) throws IOException {
        if (!directoryExists()) createSave();

        try {
            Files.write(SAVE_PATH, text.getBytes());
        } catch (IOException e) {
            // TODO saving error
        }
    }

    public static void saveTaskList(TaskList taskList) {
        try {
            writeSave(Parser.composeTaskList(taskList));
        } catch (IOException e) {
            // TODO saving error
        }
    }
}
