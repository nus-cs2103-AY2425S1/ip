package handsome;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import handsome.exception.InvalidTaskException;
import handsome.task.Deadline;
import handsome.task.Event;
import handsome.task.Task;
import handsome.task.ToDo;

/**
 * The TaskList class manages the list of tasks in the Handsome chatBot
 * application, handling the addition, removal, and modification of tasks.
 * This class loads tasks from a file, updates task statuses, and converts
 * task data into a string format suitable for saving to the storage file.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a TaskList object by loading tasks from a specified file.
     * The constructor reads each line from the file, parses task data, and
     * initializes the task list. Tasks are identified by their type and loaded
     * accordingly as todo, deadline, or event tasks.
     *
     * @param file The File object containing saved task data.
     */
    public TaskList(File file) {
        try {
            Scanner handsomeScanner = new Scanner(file);
            while (handsomeScanner.hasNext()) {
                String[] task = handsomeScanner.nextLine().split(" \\| ");
                assert task.length >= 2 : "Invalid task data format.";
                String type = task[0];
                String isDone = task[1];
                switch (type) {
                case "T":
                    tasks.add(new ToDo(task[2], isDone));
                    break;
                case "D":
                    tasks.add(new Deadline(task[2], isDone, task[3], true));
                    break;
                case "E":
                    tasks.add(new Event(task[2], isDone, task[3], task[4], true));
                    break;
                default:
                    throw new InvalidTaskException("Error Loading");
                }
            }
        } catch (FileNotFoundException fileError) {
            System.out.println("File not found!");
        } catch (ArrayIndexOutOfBoundsException | InvalidTaskException error) {
            System.out.println("Data file corrupted!");
        }
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     */
    public void markDone(int index) {
        tasks.get(index).markDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The index of the task to mark as not done.
     */
    public void markUndone(int index) {
        tasks.get(index).markUndone();
    }

    /**
     * Removes the task at the specified index from the list.
     *
     * @param index The index of the task to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the string representation of the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return A string representation of the task.
     */
    public String getStringOfIndex(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The {@code Task} object to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Searches the task list for tasks that contain the specified keyword in their content.
     *
     * @param keyword The keyword to search for within the tasks.
     * @return An {@code ArrayList<Task>} containing tasks that match the specified keyword.
     */
    public ArrayList<Task> findKeyword(String keyword) {
        ArrayList<Task> ans = new ArrayList<>();
        for (Task t : tasks) {
            if (t.contains(keyword)) {
                ans.add(t);
            }
        }
        return ans;
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        for (Task t : tasks) {
            String curr = t.toDataFormat() + System.lineSeparator();
            data.append(curr);
        }
        return data.toString();
    }
}
