package cypherchatbot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cypherchatbot.task.Deadline;
import cypherchatbot.task.Event;
import cypherchatbot.task.Task;
import cypherchatbot.task.ToDo;

/**
 * The Storage class handles the reading and writing of tasks to and from a file.
 * It provides methods for loading tasks from the file, adding new tasks, editing existing
 * tasks, and deleting tasks from the file.
 */

public class Storage {
    private String filepath;

    /**
     * Creates a new Storage instance with the specified file path.
     * If the file or directory does not exist, a new file/directory will be created.
     *
     * @param filepath The path to the file where tasks are stored.
     */
    public Storage(String filepath) {
        try {
            this.filepath = filepath;
            int val = filepath.lastIndexOf('/');

            File directory = new File(this.filepath.substring(0, val));
            if (!directory.exists() && directory.mkdir()) {
                System.out.println("New directory created under the filepath: " + this.filepath.substring(0, val));
            }

            File taskFile = new File(this.filepath);
            if (!taskFile.isFile() && taskFile.createNewFile()) {
                System.out.println("New file created under the filepath: " + this.filepath);
            }

        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

    /**
     * Loads tasks from the file specified by this.filepath. The file is read line by line
     * and returned as an ArrayList of Task objects.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws FileNotFoundException If the file at the specified path does not exist.
     */

    public ArrayList<Task> load() throws FileNotFoundException {

        assert this.filepath != null : "File Path has not been initialised";
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(this.filepath);

        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            // Segmenting the tasks
            String[] taskLine = fileScanner.nextLine().split("\\|");
            if (taskLine[0].equals("T")) {
                Task task = new ToDo(taskLine[2]);
                if (taskLine[1].equals("1")) {
                    task.markAsComplete();
                }
                taskList.add(task);
            } else if (taskLine[0].equals("D")) {
                LocalDateTime by = LocalDateTime.parse(taskLine[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                Task task = new Deadline(taskLine[2], by);
                if (taskLine[1].equals("1")) {
                    task.markAsComplete();
                }
                taskList.add(task);
            } else if (taskLine[0].equals("E")) {
                LocalDateTime from = LocalDateTime.parse(taskLine[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                LocalDateTime to = LocalDateTime.parse(taskLine[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                Task task = new Event(taskLine[2], from, to);
                if (taskLine[1].equals("1")) {
                    task.markAsComplete();
                }
                taskList.add(task);
            }
        }
        fileScanner.close();
        return taskList;
    }

    /**
     * Adds a new task to the storage file.
     *
     * @param data The String version of the task to be added to the file.
     */
    public void addToStorage(String data) {
        try {
            assert this.filepath != null : "File Path has not been initialised";
            FileWriter writeToFile = new FileWriter(this.filepath, true);
            writeToFile.write(data + "\n");
            writeToFile.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Edits an existing task in the storage file by replacing a line with a new line.
     *
     * @param oldData The old line in the file to be replaced.
     * @param newData The new line that will replace the old line in the file.
     */
    public void editTask(String oldData, String newData) {
        try {
            // Read the file into an array
            assert this.filepath != null : "File Path has not been initialised";
            List<String> entireFile = new ArrayList<>();
            File file = new File(this.filepath);

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                entireFile.add(fileScanner.nextLine());
            }
            fileScanner.close();

            // Find the line we want to replace and change it
            for (int i = 0; i < entireFile.size(); i++) {
                if (entireFile.get(i).equals(oldData)) {
                    entireFile.set(i, newData);
                    break;
                }
            }
            // Rewrite the file line by line with new changes
            FileWriter newFile = new FileWriter(this.filepath);
            for (String line: entireFile) {
                newFile.write(line + "\n");
            }
            newFile.close();

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Deletes a task from the storage file.
     *
     * @param oldData The line to be deleted from the file.
     */
    public void delTaskFromStorage(String oldData) {
        try {
            // Read the file into an array
            assert this.filepath != null : "File Path has not been initialised";
            List<String> entireFile = new ArrayList<>();
            File file = new File(this.filepath);

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                entireFile.add(fileScanner.nextLine());
            }
            fileScanner.close();

            // Find the line we want to replace and change it
            for (int i = 0; i < entireFile.size(); i++) {
                if (entireFile.get(i).equals(oldData)) {
                    entireFile.remove(i);
                    break;
                }
            }
            // Rewrite the file line by line with new changes
            FileWriter newFile = new FileWriter(this.filepath);
            for (String line: entireFile) {
                newFile.write(line + "\n");
            }
            newFile.close();

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
