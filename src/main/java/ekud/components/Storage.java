package ekud.components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import ekud.task.Task;
import ekud.ui.Ui;


/**
 * The component of EKuD that manages the reading and writing of saved data.
 *
 * @author uniqly
 * @see ekud.Ekud
 */
public class Storage {
    /** The folder where data is stored */
    private final File directory;

    /** The file corresponding to stored data */
    private final File dataFile;

    /**
     * Initializes a {@link Storage} with a given data file.
     *
     * @param filePath The {@link String} representing the path of the data file.
     */
    public Storage(String filePath) {
        dataFile = new File(filePath);
        directory = dataFile.getParentFile();
    }

    /**
     * Returns {@code true} if {@link Storage#directory} is a valid folder
     * and {@link Storage#dataFile} exists.
     *
     * @return If {@link Storage#directory} and {@link Storage#dataFile} exists.
     */
    public boolean hasExistingPath() {
        return (directory.exists() && dataFile.isFile());
    }

    /**
     * Creates {@link Storage#directory} and {@link Storage#dataFile} given that they do not exist.
     *
     * @param ui The {@link Ui} to print output to.
     * @see Storage#hasExistingPath()
     */
    public void createPath(Ui ui) {
        try {
            if (!directory.exists()) {
                boolean createdDirectory = directory.mkdir();
                assert (createdDirectory);
            }
            boolean createdFile = dataFile.createNewFile();

            assert (createdFile);
        } catch (IOException e) {
            String error = String.format("Oh no!! I could not create a save file for you\n  ERROR %s", e);
            ui.addToBuffer(error);
        }
    }

    /**
     * Reads saved data as {@link Task Tasks} and store them into a given {@link TaskList}.
     * <p/>
     * If any invalid data is read, it will be removed from {@link Storage#dataFile} and no task will
     * be added to {@code tasks}.
     *
     * @param tasks The {@link TaskList} to store the tasks into.
     * @param ui The {@link Ui} to print output to.
     */
    public void loadTasks(TaskList tasks, Ui ui) {
        try {
            StringBuilder sb = new StringBuilder();

            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            reader.lines()
                    .map((taskSave) -> Task.getTaskFromSave(taskSave, ui))
                    .filter(Objects::nonNull)
                    .forEach((task) -> {
                        sb.append(task.getSaveTaskString()).append("\n");
                        tasks.addTask(task);
                    });
            reader.close();

            FileWriter writer = new FileWriter(dataFile);
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            String error = String.format("""
                    Something went wrong when trying to load your save!
                      ERROR: %s""",
                    e);
            ui.addToBuffer(error);
        }
    }

    /**
     * Appends a given task at the end of the {@link Storage#dataFile}.
     *
     * @param task The {@link Task} to save.
     * @param ui The {@link Ui} to print output to.
     */
    public void saveNewTask(Task task, Ui ui) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile, true));
            writer.append(task.getSaveTaskString());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            String error = String.format("""
                    Oh no! I've encountered an error while trying to save your task!
                      ERROR: %s""",
                    e);
            ui.addToBuffer(error);
        }
    }

    /**
     * Searches and deletes a given task from the {@link Storage#dataFile}.
     *
     * @param task The {@link Task} to delete.
     * @param ui The {@link Ui} to print output to.
     */
    public void deleteTask(Task task, Ui ui) {
        String saveString = task.getSaveTaskString();

        try {
            StringBuilder sb = new StringBuilder();

            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            reader.lines()
                    .filter((taskSave) -> !Objects.equals(saveString, taskSave))
                    .forEach((taskSave) -> sb.append(taskSave).append("\n"));
            reader.close();

            FileWriter writer = new FileWriter(dataFile);
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            String error = String.format("""
                    Oh no!! I've encountered an error while remove your task from your save file!
                      ERROR: %s""",
                    e);
            ui.addToBuffer(error);
        }
    }

    /**
     * Updates the stored state of a given task from its previous task save-string to its
     * current task save-string in the {@link Storage#dataFile}.
     *
     * @param task The {@link Task} to update.
     * @param previousState The previous save-string of {@code task}.
     * @param ui The {@link Ui} to print output to.
     */
    public void updateTaskState(Task task, String previousState, Ui ui) {
        try {
            StringBuilder sb = new StringBuilder();

            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            reader.lines()
                    .map((taskSave) -> Objects.equals(taskSave, previousState)
                                    ? task.getSaveTaskString()
                                    : taskSave)
                    .forEach((taskSave) -> sb.append(taskSave).append("\n"));
            reader.close();

            // rewrite data to file
            FileWriter writer = new FileWriter(dataFile);
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            String error = String.format("""
                    Oh no!! I've encountered an error while trying to update the task in your save file!
                      ERROR: %s""",
                    e);
            ui.addToBuffer(error);
        }
    }
}
