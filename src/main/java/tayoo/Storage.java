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
import java.util.Scanner;

import tayoo.exception.AddTxtException;
import tayoo.exception.DeleteTxtException;
import tayoo.exception.ParserException;
import tayoo.exception.TayooException;
import tayoo.exception.UpdateTxtException;
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

    /**
     * Returns a new storage instance
     */
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
     * @throws UpdateTxtException if the tasklist.txt file cannot be found, read, or updated successfully.
     */
    public void updateTxt(int taskNumber, boolean isCompleted) throws UpdateTxtException {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(TASKLIST_FILEPATH));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new UpdateTxtException("Cannot find tasklist.txt");
        } catch (IOException e) {
            throw new UpdateTxtException("An error occurred while reading the file");
        }

        String line = lines.get(taskNumber);
        String[] parts = line.split(" \\| ");
        parts[1] = Boolean.toString(isCompleted);
        lines.set(taskNumber, String.join(" | ", parts));

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(TASKLIST_FILEPATH));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new UpdateTxtException("An error occurred while updating the task");
        }
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
    public List<Task> readTxt() throws TayooException {
        File f = new File(TASKLIST_FILEPATH);
        List<Task> taskArray = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                try {
                    //read file and add task to taskArray
                    String taskStr = s.nextLine();
                    Task taskToAdd = Parser.parseTask(taskStr);
                    taskArray.add(taskToAdd);
                } catch (ParserException e) {
                    return null;
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new TayooException("Could not find .txt file during init");
        }
        return taskArray;
    }

    /**
     * Deletes the plaintext representation of a task from the tasklist.txt file. The task to be deleted is identified
     * by its zero-based index in the tasklist.txt. The method reads the entire file into memory, deletes the relevant
     * line, then writes the updated content back into the file.
     *
     * @param taskNumber the zero-based index of the task that is to be deleted within the tasklist.txt.
     * @throws DeleteTxtException if tasklist.txt file could not be found, read or updated successfully.
     */
    public void deleteTxt(int taskNumber) throws DeleteTxtException {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(TASKLIST_FILEPATH));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new DeleteTxtException("Cannot find tasklist.txt");
        } catch (IOException e) {
            throw new DeleteTxtException("An error occurred while reading the file");
        }

        lines.remove(taskNumber);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(TASKLIST_FILEPATH));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new DeleteTxtException("An error occurred while deleting the task");
        }
    }

    /**
     * Adds the string representation of a task in a plaintext format to the tasklist.txt file. The task to be added is
     * appended to the end of the tasklist.txt. The method reads the entire file into memory, appends the task to be
     * added, then writes the updated content back into the file.
     *
     * @param taskToAdd the Task that is to be added to the tasklist.txt file
     * @throws AddTxtException if the tasklist.txt file could not be found, read or updated successfully
     */
    public void addToTxt(Task taskToAdd) throws AddTxtException {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(TASKLIST_FILEPATH));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new AddTxtException("Cannot find tasklist.txt");
        } catch (IOException e) {
            throw new AddTxtException("An error occurred while reading the file");
        }
        try {
            lines.add(taskToAdd.toTxt());
        } catch (TayooException e) {
            throw new AddTxtException(e.getMessage());
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(TASKLIST_FILEPATH));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new AddTxtException("An error occurred while deleting the task");
        }
    }

    public boolean createTxt() throws TayooException {
        File f = new File(TASKLIST_FILEPATH);
        try {
            return f.createNewFile();
        } catch (IOException e) {
            throw new TayooException("An IOexception occurred while creating .txt file");
        }
    }

}
