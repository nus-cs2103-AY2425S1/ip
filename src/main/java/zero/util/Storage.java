package zero.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import zero.exception.ZeroException;
import zero.task.Deadline;
import zero.task.Event;
import zero.task.Task;
import zero.task.TaskList;
import zero.task.Todo;

/**
 * The {@code Storage} class handles the loading and saving of tasks to a file.
 * It reads tasks from a specified file during initialisation and writes tasks back to the file when required.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file and returns them as an {@code ArrayList<Task>}.
     * If the file does not exist, it creates the file and any necessary directories.
     *
     * @return An {@code ArrayList<Task>} containing the loaded tasks.
     * @throws ZeroException If an error occurs while loading tasks.
     */
    public ArrayList<Task> load() throws ZeroException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File fileObj = new File(filePath);
            if (!fileObj.exists()) {
                fileObj.getParentFile().mkdirs(); // create directories if they don't exist
                fileObj.createNewFile(); // create the file if it doesn't exist
            }
            Scanner myReader = new Scanner(fileObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] items = data.split(",");
                switch (items[0]) {
                case "T":
                    Todo newTodo = new Todo(items[2]);
                    if (items[1].equals("1")) {
                        newTodo.markAsDone();
                    }
                    tasks.add(newTodo);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(items[2], LocalDateTime.parse(items[3]));
                    if (items[1].equals("1")) {
                        newDeadline.markAsDone();
                    }
                    tasks.add(newDeadline);
                    break;
                case "E":
                    Event newEvent =
                            new Event(items[2], LocalDateTime.parse(items[3]), LocalDateTime.parse(items[4]));
                    if (items[1].equals("1")) {
                        newEvent.markAsDone();
                    }
                    tasks.add(newEvent);
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            throw new ZeroException("File not found: " + filePath);
        } catch (IOException e) {
            throw new ZeroException("An error occurred while loading tasks.");
        }
        return tasks;
    }

    public void save(TaskList tasks) throws ZeroException {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                myWriter.write(tasks.getTask(i).toFormatted());
            }
            myWriter.close();
        } catch (IOException e) {
            throw new ZeroException("An error occurred while saving tasks.");
        }
    }
}
