package henry.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import henry.HenryException;
import henry.task.Deadline;
import henry.task.Event;
import henry.task.Task;
import henry.task.Todo;

/**
 * Deals with loading tasks from the file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the path of the file.
     *
     * @return The path of the file.
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Returns an ArrayList of tasks recorded in the hard disk.
     *
     * @return An ArrayList of tasks that are in the form of
     *                 event, deadline and todo.
     * @throws HenryException If there is an issue accessing the file, creating
     *                 the directory, or reading the tasks.
     */
    public ArrayList<Task> load() throws HenryException {
        try {
            ArrayList<Task> recordedTasks = new ArrayList<>();

            // create a Scanner using the File as the source
            File file = new File(this.filePath);
            //if file does not exist, create new file and directory
            if (!file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    throw new HenryException("Failed to create directory for file path");
                }
            }

            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new HenryException("Failed to create new file");
                }
                return recordedTasks; // Return empty list if file just created
            }

            Scanner scanner1 = new Scanner(file);
            while (scanner1.hasNext()) {
                String input = scanner1.nextLine();
                String[] words = input.split(" \\| ");
                if (words[0].equals("T")) {
                    addToDo(recordedTasks, words);
                } else if (words[0].equals("D")) {
                    addDeadline(recordedTasks, words);
                } else if (words[0].equals("E")) {
                    addEvent(words, recordedTasks);
                }
                assert (words[0].equals("T") || words[0].equals("D") || words[0].equals("E"));
            }
            return recordedTasks;
        } catch (IOException e) {
            throw new HenryException("An error occurred while accessing the file");
        }
    }

    /**
     * Adds an Event task to the list.
     *
     * @param recordedTasks The list to store the all the tasks.
     * @param words The input string split into components.
     */
    private static void addEvent(String[] words, ArrayList<Task> recordedTasks) {
        String[] duration = words[3].split("-");
        String startTime = duration[0];
        String endTime = duration[1];
        recordedTasks.add(new Event(words[2], startTime,
                endTime, (Integer.parseInt(words[1]) != 0)));
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param recordedTasks The list to store the all the tasks.
     * @param words The input string split into components.
     */
    private static void addDeadline(ArrayList<Task> recordedTasks, String[] words) {
        recordedTasks.add(new Deadline(words[2], words[3], (Integer.parseInt(words[1]) != 0)));
    }

    /**
     * Adds a Todo task to the list.
     *
     * @param recordedTasks The list to store the all the tasks.
     * @param words The input string split into components.
     */
    private static void addToDo(ArrayList<Task> recordedTasks, String[] words) {
        recordedTasks.add(new Todo(words[2], (Integer.parseInt(words[1]) != 0)));
    }
}
