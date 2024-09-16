package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Deadlines;
import tasks.Event;
import tasks.Task;
import tasks.ToDos;

/**
 * Handles storage of user task information.
 */
public class Storage {
    private File file;

    public Storage(String path) {
        this.file = new File(path);
    }

    /**
     * Reads from file and returns the resulting TaskList.
     */
    public ArrayList<Task> loadTaskListFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(this.file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                // use "," as delimiters
                String[] splits = line.split(",");
                switch (splits[0]) {
                case "T":
                    Task t = new ToDos(splits[2]);
                    t.setTag(splits[3].strip());
                    tasks.add(t);
                    break;
                case "D":
                    Task t2 = new Deadlines(splits[2], splits[3].strip());
                    t2.setTag(splits[4].strip());
                    tasks.add(t2);
                    break;
                case "E":
                    Task t3 = new Event(splits[2], splits[3].strip(), splits[4].strip());
                    t3.setTag(splits[5].strip());
                    tasks.add(t3);
                    break;
                default:
                    break;
                }
                if (splits[1].strip().equals("0")) {
                    tasks.get(tasks.size() - 1).markAsNotDone();
                } else {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            // Generate initial file
            try {
                this.file.createNewFile();
            } catch (IOException ex) {
                // Should never be caught in actual execution.
                System.out.println(Ui.updateUserOnError(ex));
            }
        }

        return tasks;
    }

    /**
     * Updates status for specific task in the file.
     *
     * @param index Index of task to be updated.
     * @param status Status of isDone to determine update information.
     * @return String message with failure details only on failure.
     */
    public String updateTaskStatus(int index, boolean status) {
        String message = "";
        ArrayList<String> fileContents = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(this.file);
            while (fileScanner.hasNextLine()) {
                fileContents.add(fileScanner.nextLine());
            }
            fileScanner.close();

            String s = fileContents.get(index);
            String replacement = status ? "1" : "0";
            fileContents.set(index, s.replaceFirst("0|1", replacement));
            FileWriter fw = new FileWriter(this.file);
            for (String line : fileContents) {
                fw.write(line + "\n");
            }
            fw.close();
        } catch (IOException e) {
            message += Ui.updateUserOnError(e);
        }

        return message;
    }

    /**
     * Updates tag for specific task in the file.
     *
     * @param index Index of task to be updated.
     * @param tag Tag to be updated.
     * @return String message with failure details only on failure.
     */
    public String updateTaskTag(int index, String tag) {
        String message = "";
        ArrayList<String> fileContents = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(this.file);
            while (fileScanner.hasNextLine()) {
                fileContents.add(fileScanner.nextLine());
            }
            fileScanner.close();

            String s = fileContents.get(index);
            // Overwrite Previous Tag
            String writeString = s.substring(0, s.lastIndexOf(",") + 1) + " " + tag;
            fileContents.set(index, writeString);
            FileWriter fw = new FileWriter(this.file);
            for (String line : fileContents) {
                fw.write(line + "\n");
            }
            fw.close();
        } catch (IOException e) {
            message += Ui.updateUserOnError(e);
        }

        return message;
    }

    /**
     * Removes specific task from the file.
     *
     * @param index Index of task to be removed.
     * @return String message with failure details only on failure.
     */
    public String removeFileTask(int index) {
        String message = "";
        ArrayList<String> fileContents = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(this.file);
            while (fileScanner.hasNextLine()) {
                fileContents.add(fileScanner.nextLine());
            }
            fileScanner.close();

            fileContents.remove(index);
            FileWriter fw = new FileWriter(this.file);
            for (String line : fileContents) {
                fw.write(line + "\n");
            }
            fw.close();
        } catch (IOException e) {
            message += Ui.updateUserOnError(e);
        }

        return message;
    }

    /**
     * Adds the newly created task into the file.
     *
     * @param details String containing details for the new task.
     * @return String message with failure details only on failure.
     */
    public String updateFileTasks(String details) {
        String message = "";

        try {
            FileWriter fw = new FileWriter(this.file, true);
            fw.write(details + " \n"); // for readibility
            fw.close();
        } catch (IOException e) {
            message += Ui.updateUserOnError(e);
        }

        return message;
    }
}
