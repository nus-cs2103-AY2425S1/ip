package tayoo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tayoo.exception.TayooException;
import tayoo.tasks.Task;

/**
 * The storage class handles the tasklist.txt file in which the Tasks are stored in plaintext format. This class
 * contains all methods deal with loading, deleting, saving and updating tasks to this tasklist.txt file
 */
public class Storage {

    /**
     * The path in which to store the tasklist.txt file. If planning to update this relative location, we must make sure
     * that that folder which contains the tasklist.txt exists.
     */
    private static final String TASKLIST_FILEPATH = "./tasklist.txt";

    /** Returns a new storage instance */
    public Storage() {
    }

    /**
     * Updates the plaintext representation of a task within the tasklist.txt file to reflect the boolean
     * {@code isCompleted}. This boolean should be true if the task is completed, and false if not yet completed. The
     * task in the tasklist is accessed by the zero-based index of the task within the tasklist.
     *
     * <p>The method reads the file into memory, updates the relevant lines then writes the updated content back into
     * the file.</p>
     *
     * @param taskNumber The zero-based index of the task in the TASKLIST whose completion is to be updated
     * @param isCompleted {@code true} if the task is completed, {@code false} if the task is not yet completed.
     * @throws TayooException if the tasklist.txt file cannot be found, read, or updated successfully.
     */
    public void updateTxt(int taskNumber, boolean isCompleted) throws TayooException {
        List<String> lines = readFromTaskList();;

        String line = lines.get(taskNumber);
        String[] parts = line.split(" \\| ");
        parts[1] = Boolean.toString(isCompleted);
        lines.set(taskNumber, String.join(" | ", parts));

        writeToTaskList(lines);
    }

    /**
     * Reads from the existing tasklist.txt file and returns a {@code List<Task>} which can be passed into the
     * {@code Tasklist} constructor. The method accesses the tasklist.txt, iterates through each line of the file, calls
     * {@code Parser.ParseTask()} on the line, which returns a Task object, then adds this to the {@code List<Task>} to
     * be returned. If there is an error during parsing, a null is returned.
     *
     * @return List<Task> which represents all the tasks found in the tasklist.txt file. Returns {@code null} if there
     * were no tasks found in the tasklist.txt file.
     * @throws TayooException if there was no tasklist.txt file found
     */
    public List<Task> returnTaskListFromTxt() throws TayooException {
        File f = new File(TASKLIST_FILEPATH);
        List<String> lines = readFromTaskList();;
        List<Task> taskArray= new ArrayList<>();
        for (String taskString: lines) {
            taskArray.add(Parser.parseTask(taskString));
        }
        return taskArray;
    }

    /**
     * Deletes the plaintext representation of a task from the tasklist.txt file. The task to be deleted is identified
     * by its zero-based index in the tasklist.txt. The method reads the entire file into memory, deletes the relevant
     * line, then writes the updated content back into the file.
     *
     * @param taskNumber the zero-based index of the task that is to be deleted within the tasklist.txt.
     * @throws TayooException if tasklist.txt file could not be found, read or updated successfully.
     */
    public void deleteTxt(int taskNumber) throws TayooException {
        List<String> lines = readFromTaskList();
        lines.remove(taskNumber);
        writeToTaskList(lines);
    }

    /**
     * Deletes all lines in the txt. Data is not recoverable.
     */
    public void deleteWholeTxt() throws TayooException{
        List<String> lines = new ArrayList<>();
        writeToTaskList(lines);
    }

    /**
     * Adds the string representation of a task in a plaintext format to the tasklist.txt file. The task to be added is
     * appended to the end of the tasklist.txt. The method reads the entire file into memory, appends the task to be
     * added, then writes the updated content back into the file.
     *
     * @param taskToAdd the Task that is to be added to the tasklist.txt file
     * @throws TayooException if the tasklist.txt file could not be found, read or updated successfully
     */
    public void addToTxt(Task taskToAdd) throws TayooException {
        List<String> lines = readFromTaskList();
        lines.add(taskToAdd.toTxt());
        writeToTaskList(lines);
    }

    /**
     * Attempts to create a new tasklist, returns true if a new file is created and false if not.
     *
     * @return true if a new tasklist has been created, false if not
     * @throws TayooException if an IOException occurs during the creation of the file.
     */
    public boolean createTxt() throws TayooException {
        File f = new File(TASKLIST_FILEPATH);
        try {
            return f.createNewFile();
        } catch (IOException e) {
            throw new TayooException("An IOexception occurred while creating .txt file");
        }
    }

    private ArrayList<String> readFromTaskList() throws TayooException{
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(TASKLIST_FILEPATH));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            throw new TayooException("Cannot find tasklist.txt");
        } catch (IOException e) {
            throw new TayooException("Invalid input while reading the file");
        }
        return lines;
    }

    private void writeToTaskList(List<? extends String> lines) throws TayooException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(TASKLIST_FILEPATH));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new TayooException("An error occurred while writing the task");
        }
    }

}
