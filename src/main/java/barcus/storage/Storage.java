package barcus.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import barcus.exception.BarcusException;
import barcus.task.Deadline;
import barcus.task.Event;
import barcus.task.Todo;
import barcus.tasklist.TaskList;



/**
 * Storage class to handle loading to and from save file
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for storage
     * @param filePath path to save file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the information from the save file into a tasklist
     * @return Tasklist with loaded info
     * @throws BarcusException if error in finding the file
     */
    public TaskList load() throws BarcusException {
        File file = new File(this.filePath);
        String[] temp = this.filePath.split("/");
        File dir = new File(String.join("/",
                Arrays.copyOfRange(temp, 0, temp.length - 1)));

        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new BarcusException("error in creating new file");
            }
        }

        TaskList tasks = new TaskList();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String[] lineSplit = s.nextLine().split(" \\| ");

                if (lineSplit[0].equals("T")) {
                    tasks.addTask(new Todo(lineSplit[2], lineSplit[1].equals("1"), lineSplit[3]));
                } else if (lineSplit[0].equals("D")) {
                    tasks.addTask(new Deadline(
                            lineSplit[2],
                            lineSplit[1].equals("1"),
                            lineSplit[3],
                            lineSplit[4]
                    ));
                } else if (lineSplit[0].equals("E")) {
                    tasks.addTask(new Event(
                            lineSplit[2],
                            lineSplit[1].equals("1"),
                            lineSplit[3],
                            lineSplit[4],
                            lineSplit[5]
                    ));
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new BarcusException("error in finding file");
        }

        return tasks;
    }

    /**
     * Uploads Task list into the save file
     * @param tasks task list
     * @throws BarcusException if file is noe saved successfully
     */
    public void upload(TaskList tasks) throws BarcusException {
        try (FileWriter writer = new FileWriter(this.filePath);
             BufferedWriter bfWriter = new BufferedWriter(writer)) {
            bfWriter.write(tasks.convertToSavable());
        } catch (IOException e) {
            throw new BarcusException("error updating txt save file");
        }
    }
}
