package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

/**
 * Represents the persistent storage for tasks.
 */
public class Storage {
    /**
     * The path of the file used for persistent storage.
     */
    private String path;

    /**
     * Constructor for a storage.
     *
     * @param path The path of the file used for persistent storage.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Add a task to the storage.
     *
     * @param task The task to add.
     * @throws IOException If an I/O error occurs.
     */
    public void add(Task task) throws IOException {
        FileWriter fw = new FileWriter(this.path, true);
        fw.write(task.toString() + System.getProperty("line.separator"));
        fw.close();
    }

    /**
     * Mark a task in the storage as completed or not completed, by item number.
     *
     * @param itemNum The item number of the task to update.
     * @param isCompleted Whether to mark the task as completed or not completed.
     * @throws IOException If an I/O error occurs.
     */
    public void mark(int itemNum, boolean isCompleted) throws IOException, BobException {
        File inputFile = new File(this.path);
        File tempFile = new File("temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        int lineNum = 1;
        String line;

        while ((line = reader.readLine()) != null) {
            if (lineNum == itemNum) {
                Task task = Parser.parseStorage(line);
                task.mark(isCompleted);
                writer.write(task + System.getProperty("line.separator"));
            } else {
                writer.write(line + System.getProperty("line.separator"));
            }
            lineNum++;
        }
        writer.close();
        reader.close();
        tempFile.renameTo(inputFile);
    }

    /**
     * Delete a task in the storage by item number.
     *
     * @param itemNum The item number of the task to delete.
     * @throws IOException If an I/O error occurs.
     */
    public void delete(int itemNum) throws IOException {
        File inputFile = new File(this.path);
        File tempFile = new File("temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        int lineNum = 1;
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            if (lineNum != itemNum) {
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            lineNum++;
        }
        writer.close();
        reader.close();
        tempFile.renameTo(inputFile);
    }

    /**
     * Parses and returns the tasks from storage.
     *
     * @throws BobException If an I/O error occurred or a task cannot be parsed.
     */
    public ArrayList<Task> load() throws BobException {
        try {
            File f = new File(this.path);
            f.createNewFile();

            ArrayList<Task> tasks = new ArrayList<>();

            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                Task task = Parser.parseStorage(line);
                tasks.add(task);
            }

            return tasks;
        } catch (IOException e) {
            throw new BobException("Sorry, something went wrong when reading from your storage.");
        }
    }
}
