package juno.manager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import juno.manager.exception.TaskManagerException;
import juno.task.Task;
import juno.ui.DialogBox;

/**
 * A class that handles the reading and writing to the file, task.json, which stores tasks in JSON format.
 */
public class FileManager {

    private static final String FILE_CREATED_STRING = "File created! Unfortunately, it looks like your previous tasks "
            + "have vanished into the digital abyss. But hey, fresh start, right? 🌟";
    private static final String CANNOT_DELETE_FILE_STRING = "Seems like we can't delete the file either, maybe try "
            + "deleting it manually and run me again!";
    private static final String DATA_STORING_ERROR_STRING = "Looks like we ran into some errors while trying to"
            + " store your data!";

    private static final String ERROR_WRITING_STRING = "Looks like we ran into some errors while saving the tasks!";
    private static final String INCORRECT_CONTENT_FORMAT = "Looks like the file content is not in the correct format!\n"
            + "Proceeding to delete the file and create a new one...";
    private static final String ERROR_RETRIEVING_DATA_STRING = "Looks like we ran into some errors while retrieving "
            + "data regarding your tasks!";

    private static final String FILE_PATH = "task.json";

    private Gson gsonInstance;
    private VBox dialogContainer;
    private Image junoImage;

    /**
     * Constructs a FileManager instance which when initialised, will initialise a custom Gson instance as well.
     * The Gson instance will handle Task objects and exclude fields without @Expose annotation.
     */
    public FileManager(VBox dialogContainer, Image junoImage) {
        this.gsonInstance = new GsonBuilder()
                 .registerTypeAdapter(Task.class, new TaskAdapter())
                 .setPrettyPrinting()
                 .excludeFieldsWithoutExposeAnnotation()
                 .create();
        this.dialogContainer = dialogContainer;
        this.junoImage = junoImage;
    }

    /**
     * Writes the list of tasks to the file task.json in JSON format.
     *
     * @param tasks The tasks to be written to the file.
     */
    public void writeTasksToFile(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            this.gsonInstance.toJson(tasks, writer);
        } catch (IOException e) {
            dialogContainer.getChildren().addAll(DialogBox.getJunoDialog(ERROR_WRITING_STRING + e.getMessage(),
                    junoImage));
        }
    }

    /**
     * Reads the list of tasks from the file task.json.
     *
     * @return The tasks read from the file, in ArrayList.
     */
    public ArrayList<Task> readTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type taskListType = new TypeToken<ArrayList<Task>>() {}.getType();
            tasks = this.gsonInstance.fromJson(reader, taskListType);
        } catch (JsonSyntaxException e) {
            dialogContainer.getChildren().addAll(DialogBox.getJunoDialog(INCORRECT_CONTENT_FORMAT, junoImage));
            this.handleCorruptedFile();
        } catch (IOException e) {
            dialogContainer.getChildren().addAll(DialogBox.getJunoDialog(
                    ERROR_RETRIEVING_DATA_STRING + e.getMessage(),
                    junoImage));
        }
        return tasks;
    }

    /**
     * Ensures that the file exists. If the file does not exist, create a new one.
     * @throws TaskManagerException If we ran into some issues with storing the data.
     */
    public void ensureFileExist() throws TaskManagerException {
        File f = new File(FILE_PATH);
        if (!f.isFile()) {
            try {
                boolean isCreatedFile = f.createNewFile();
            } catch (IOException e) {
                throw new TaskManagerException(DATA_STORING_ERROR_STRING + e.getMessage(),
                        TaskManagerException.ErrorType.FILE_NOT_EXIST);
            }
        }
    }

    /**
     * Handles the issue when file is corrupted by deleting the corrupted file and creating a new one.
     */
    public void handleCorruptedFile() {
        File f = new File(FILE_PATH);
        if (f.delete()) {
            try {
                this.ensureFileExist();
            } catch (TaskManagerException e) {
                dialogContainer.getChildren().addAll(DialogBox.getJunoDialog(e.getMessage(), junoImage));
            }
            dialogContainer.getChildren().addAll(DialogBox.getJunoDialog(FILE_CREATED_STRING, junoImage));
        } else {
            dialogContainer.getChildren().addAll(DialogBox.getJunoDialog(CANNOT_DELETE_FILE_STRING, junoImage));
        }
    }
}
