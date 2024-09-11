package momo;

import momo.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage handles the loading, updating and saving of user tasks from the
 * ArrayList into the Storage object file which is stored in the {@code filePath}
 */
public class Storage {
    // Should handle file creation, file loading, load into list
    private final String filePath;
    private final String archivedFilePath = "data/archive.txt";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads all tasks from the storage file returning them as a single string.
     *
     * @return A string containing all the tasks saved to the storage file
     */
    public String load() {
        StringBuilder fileString = new StringBuilder();
        try {
            File f = new File("data/momo.txt"); // Create a File for the given file path
            Scanner s = new Scanner(f); // Create a Scanner using the File as the source
            String nextLine;

            // Write input line by line into fileString
            while (s.hasNext()) {
                nextLine = s.nextLine();
                fileString.append(nextLine).append("\n");
            }

            s.close();

        } catch (FileNotFoundException e) {
            File f = new File("data/momo.txt");
            File d = new File("data");


            try {
                if (d.mkdirs() || d.exists()) {
                    if (f.createNewFile()) {
                        System.out.println("File and/or folder created successfully");
                    }
                }
            } catch (IOException ioe) {
                System.out.println("Error in creating a file and folder ");
            }

        }

        try {
            // Create archive
            File a = new File(archivedFilePath);
            a.createNewFile();
        } catch (IOException e) {
            System.out.println("Error in creating archive.txt.");
        }

        return fileString.toString();

    }


    /**
     * Appends task in file string format to the storage file, utilised when
     * todo/deadline/event tasks are added
     *
     * @param filePath  The file path to the file to be appended to
     * @param textToAdd Task in file string format to be added
     * @throws IOException thrown when there are errors writing the string into the file
     */
    public void addTaskToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    /**
     * Rewrites ArrayList of tasks to the storage file when commands are
     * used to modify/delete pre-existing tasks in the TaskList
     * like mark/unmark/Delete
     *
     * @param filePath path to file which will be rewritten to
     * @param list     ArrayList containing all task objects from the TaskList
     * @throws IOException thrown when there are errors writing the string into the file
     */
    public void RewriteTasksToFile(String filePath, ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : list) {
            fw.write(task.toFileString() + "\n");
        }
        fw.close();
    }


}
