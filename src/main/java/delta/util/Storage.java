package delta.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import delta.exception.DeltaException;
import delta.task.Deadline;
import delta.task.Event;
import delta.task.Task;
import delta.task.Todo;

/**
 * Deals with loading tasks from save file and saving tasks into save file.
 */
public class Storage {
    // Stores relative file path of save file.
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads all tasks from save file into list.
     *
     * @return ArrayList containing all tasks obtained from save file.
     * @throws DeltaException If save file has been corrupted or save file not found.
     */
    public ArrayList<Task> load() throws DeltaException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File("./" + filePath);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] details = sc.nextLine().replace("\n", "").split(" \\| ");
                Task task;
                // Todo Task
                if (details[0].equals("T") && details.length == 3) {
                    task = new Todo(details[2]);
                // Deadline Task
                } else if (details[0].equals("D") && details.length == 4) {
                    task = new Deadline(details[2], details[3]);
                // Event Task
                } else if (details[0].equals("E") && details.length == 5) {
                    task = new Event(details[2], details[3], details[4]);
                // Format for Task stored is wrong (i.e. save file corrupted)
                } else {
                    throw new DeltaException("OOPS!!! Save File has been corrupted!");
                }

                // Mark task if needed
                if (details[1].equals("1")) {
                    task.markAsDone();
                // Mark/Unmark task corrupted
                } else if (!details[1].equals("0")) {
                    throw new DeltaException("OOPS!!! Save File has been corrupted!");
                }

                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DeltaException("OOPS!!! Save File not found!");
        }
        return tasks;
    }

    /**
     * Saves all tasks from list into save file.
     *
     * @param taskList TaskList containing all current tasks.
     * @throws DeltaException If save directory or save file cannot be created, or file cannot be saved.
     */
    public void save(TaskList taskList) throws DeltaException {
        File file = new File("./" + filePath);
        File directory = file.getParentFile();

        // Create save directory
        if (!directory.exists()) {
            boolean isCreatedSuccessfully = directory.mkdir();
            if (!isCreatedSuccessfully) {
                throw new DeltaException("OOPS!!! Save Directory unable to be created!");
            }
        }

        // Create save file
        if (!file.exists()) {
            try {
                boolean isCreatedSuccessfully = file.createNewFile();
                if (!isCreatedSuccessfully) {
                    throw new DeltaException("OOPS!!! Save File unable to be created!");
                }
            } catch (IOException e) {
                throw new DeltaException("OOPS!!! Save File unable to be created!");
            }
        }

        // Convert all tasks into save format
        ArrayList<Task> tasks = taskList.getTasks();
        String fileContents = "";
        for (Task task : tasks) {
            fileContents += task.saveDetails() + "\n";
        }

        // Save all tasks into save file
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(fileContents);
            fw.close();
        } catch (IOException e) {
            throw new DeltaException("OOPS!!! List unable to save!");
        }
    }
}
