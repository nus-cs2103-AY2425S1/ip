package ekud.components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
        boolean isNonEmptyPath = (filePath != null && !filePath.isEmpty());
        assert isNonEmptyPath : "filePath must be non empty string";

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
        assert ui != null : "ui should not be null";

        try {
            if (!directory.exists()) {
                boolean createdDirectory = directory.mkdir();
                assert (createdDirectory) : "directory should have been created";
            }
            boolean createdFile = dataFile.createNewFile();

            assert (createdFile) : "save file should have been created";
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
        assert tasks != null : "tasks should not be null";
        assert ui != null : "ui should not be null";

        File copy = new File(dataFile.getParent() + "/temp.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(copy, true));
            String currLine = reader.readLine();

            // read tasks and add to list
            while (currLine != null) {
                String taskSave = currLine.trim();
                Task task = Task.getTaskFromSave(taskSave, ui);
                if (task != null) {
                    // preserve tasks
                    writer.write(taskSave);
                    writer.newLine();

                    // add valid ekud.task to list
                    tasks.addTask(task);
                }

                currLine = reader.readLine();
            }
            reader.close();
            writer.close();

            boolean deletedOriginal = dataFile.delete();
            boolean renamed = copy.renameTo(dataFile);
            assert (deletedOriginal && renamed) : "original file should be replaced";
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
        assert task != null : "task should not be null";
        assert ui != null : "ui should not be null";

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
        assert task != null : "task should not be null";
        assert ui != null : "ui should not be null";

        String saveString = task.getSaveTaskString();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            StringBuilder sb = new StringBuilder();
            String currLine = reader.readLine();
            while (currLine != null) {
                // keep non deleted lines
                if (!saveString.equals(currLine)) {
                    sb.append(currLine).append("\n");
                }
                currLine = reader.readLine();
            }
            reader.close();

            // rewrite file
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
        assert task != null : "task should not be null";
        assert ui != null : "ui should not be null";
        boolean isNonEmptyState = (previousState != null && !previousState.isEmpty());
        assert isNonEmptyState : "previousState should be non-empty";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            StringBuilder sb = new StringBuilder();
            String currLine = reader.readLine();

            while (currLine != null) {
                if (previousState.equals(currLine)) { // replace old state with new state
                    sb.append(task.getSaveTaskString()).append("\n");
                } else {
                    sb.append(currLine).append("\n");
                }
                currLine = reader.readLine();
            }

            // rewrite data to file
            reader.close();
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
