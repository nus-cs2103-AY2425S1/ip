package TaskList;

import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

import Exceptions.DelphiException;
import Exceptions.InvalidListItemException;
import Parser.DateParser;
import Parser.Parser;
import Storage.Storage;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

/**
 * Represents a list of tasks. The TaskList.TaskList class manages the creation,
 * manipulation, and display of tasks, including Todos, Deadlines, and Events.
 *
 * @author Jordan Chan
 */
public class TaskList {
    private final List<Task> tasks;
    /**
     * Constructs an empty TaskList.TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list based on the input string.
     *
     * @param task The task object.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Converts all the strings that represent tasks from the hard drive .txt file and adds them to the task list.
     *
     * @param s The storage object that UI.Delphi uses to interact with the hard disk .txt file.
     */
    public void loadStorageToTasks(Storage s) {
        Parser helperParser = new Parser();
        DateParser helperDateParser = new DateParser();
        try {
            List<String> readTasks = s.readFromHardDisk();
            int counter = 0;
            while (counter < readTasks.size()) {
                String line = readTasks.get(counter);

                //ensure that the stored tasks are formatted correcly as strings before converting
                //them to tasks
                assert line.charAt(0) == '[' : "Task incorrectly formatted";
                assert line.charAt(2) == ']' : "Task incorrectly formatted";
                assert line.charAt(3) == '[' : "Task incorrectly formatted";
                assert line.charAt(5) == ']' : "Task incorrectly formatted";
                assert (line.charAt(4) == 'X' || line.charAt(4) == ' ') : "Task incorrectly formatted";
                assert line.charAt(1) == 'T' || line.charAt(1) == 'D' || line.charAt(1) == 'E'
                        : "Task incorrectly formatted";

                if (helperParser.checkStringPrefix(line, 6, "[T][ ]")) {
                    try {
                        tasks.add(new Todo(line.substring(7)));
                    } catch (DelphiException e) {
                        //empty
                    }
                } else if (helperParser.checkStringPrefix(line, 6, "[T][X]")) {
                    try {
                        tasks.add(new Todo(line.substring(7)));
                        markTaskAsDone(tasks.size());
                    } catch (DelphiException e) {
                        //empty
                    }
                } else if (helperParser.checkStringPrefix(line, 6, "[D][ ]")) {
                    try {
                        tasks.add(new Deadline(Parser.formatStringDeadline(line.substring(7)), helperDateParser));
                    } catch (DelphiException e) {
                        //empty
                    }
                } else if (helperParser.checkStringPrefix(line, 6, "[D][X]")) {
                    try {
                        tasks.add(new Deadline(Parser.formatStringDeadline(line.substring(7)), helperDateParser));
                        markTaskAsDone(tasks.size());
                    } catch (DelphiException e) {
                        //empty
                    }
                } else if (helperParser.checkStringPrefix(line, 6, "[E][ ]")) {
                    try {
                        tasks.add(new Event(Parser.formatStringEvent(line.substring(7)), helperDateParser));
                    } catch (DelphiException e) {
                        //empty
                    }
                } else if (helperParser.checkStringPrefix(line, 6, "[E][X]")) {
                    try {
                        tasks.add(new Event(Parser.formatStringEvent(line.substring(7)), helperDateParser));
                        markTaskAsDone(tasks.size());
                    } catch (DelphiException e) {
                        //empty
                    }
                }
                counter++;
            }
            if (readTasks.isEmpty()) {
                System.out.println("    no tasks in hard drive");
            }
        } catch (IOException e) {
            System.out.println("    could not find hard disk!");
        }
    }

    /**
     * Removes a task from the list based on the index.
     *
     * @param i The index of the task to be removed.
     * @return The removed task, or null if the index is invalid.
     */
    public Task removeTask(int i) throws InvalidListItemException {
        if (i <= tasks.size()) {
            Task t = tasks.get(i - 1);
            tasks.remove(i - 1);
            return t;
        } else {
            throw new InvalidListItemException(i);
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks a task as done based on the index.
     *
     * @param i The index of the task to be marked as done.
     */
    public void markTaskAsDone(int i) {
        if (i <= tasks.size()) {
            tasks.get(i - 1).complete();
        }
    }

    /**
     * Marks a task as undone based on the index.
     *
     * @param i The index of the task to be marked as undone.
     */
    public void markTaskAsUndone(int i) {
        if (i <= tasks.size()) {
            tasks.get(i - 1).uncomplete();
        }
    }

    /**
     * Returns the string representation of a task based on the index.
     *
     * @param i The index of the task.
     * @return The string representation of the task, or an empty string if the index is invalid.
     */
    public Task getTask(int i) {
        if (i <= tasks.size()) {
            return tasks.get(i - 1);
        } else {
            return null;
        }
    }

    /**
     * returns task list.
     *
     * @return The task list as Collection.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Prints all tasks in the task list.
     */
    public String printTasks() {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            //System.out.println("    " + (i + 1) + "." + tasks.get(i).toString());
            res += "    " + (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return res;
    }
}
