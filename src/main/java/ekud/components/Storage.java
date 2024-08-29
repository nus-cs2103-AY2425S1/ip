package ekud.components;

import ekud.task.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Storage {
    private final File directory;
    private final File dataFile;

    public Storage(String filePath) {
        dataFile = new File(filePath);
        directory = dataFile.getParentFile();
    }

    public boolean hasExistingPath() {
        return (directory.exists() && dataFile.isFile());
    }

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
            ui.printOutput(error);
        }
    }

    public void loadTasks(TaskList tasks, Ui ui) {
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
            boolean deleted = copy.delete();
            assert(deletedOriginal && renamed && deleted);
        } catch (IOException e) {
            String error = String.format("""
                    Something went wrong when trying to load your save!
                      ERROR: %s""",
                    e);
            ui.printOutput(error);
        }
    }

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
            ui.printOutput(error);
        }
    }

    public void deleteTask(Task task, Ui ui) {
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
            ui.printOutput(error);
        }
    }

    public void updateTaskState(Task task, String previousState, Ui ui) {
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
                    e) ;
            ui.printOutput(error);
        }
    }
}
