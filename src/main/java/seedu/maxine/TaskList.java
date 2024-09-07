package seedu.maxine;

import seedu.maxine.exception.MaxineException;
import seedu.maxine.task.Deadline;
import seedu.maxine.task.Event;
import seedu.maxine.task.Task;
import seedu.maxine.task.Todo;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.ArrayList;

public class TaskList implements MaxineList {
    private ArrayList<Task> list;
    private Storage storage;

    /**
     * Constructs new instance of TaskList class
     */
    public TaskList() {
        Storage storage = new Storage("data/maxine.txt");
        this.list = storage.load();
    }


    /**
     * Adds todo task to the tasklist
     * @param arr Array of Strings containing user's input
     */
    public void addTodo(String[] arr) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(arr[1]);
            if (arr.length >= 3) {
                for (int i = 2; i < arr.length; i++) {
                    String word = " " + arr[i];
                    sb.append(word);
                }
            }
            Todo task = new Todo(sb.toString());
            list.add(task);
        } catch (Exception e) {
            System.out.println("Please follow this format: todo [enter maxine.task]");
        }
        
    }


    /**
     * Adds deadline task to the tasklist.
     * Exception is thrown if the user does not follow 
     * the format for deadline task
     * @param arr Array of Strings containing user's input
     * @throws MaxineException Exception if the user does not follow format
     */
    public void addDeadline(String[] arr) throws MaxineException {
        try {
            StringBuilder desc = new StringBuilder();
            StringBuilder ddl = new StringBuilder();
            desc.append(arr[1]);
            boolean isChecked = false;
            for (int i = 1; i < (arr.length - 1); i++) {
                if (arr[i].equals("/by")) {
                    isChecked = true;
                    break;
                }
            }
            if (!isChecked || arr[1].equals("/by")) {
                throw new MaxineException("Please follow this format: deadline [enter maxine.task] /by [enter deadline]");
            }
            boolean hasBy = false;
            for (int i = 2; i < arr.length; i++) {
                if (arr[i].equals("/by")) {
                    hasBy = true;
                }
                else if (hasBy) {
                    String word = " " + arr[i];
                    ddl.append(word);
                } else {
                    String word = " " + arr[i];
                    desc.append(word);
                }
            }
            Deadline task = new Deadline(desc.toString(), ddl.toString());
            list.add(task);
        } catch (Exception e) {
            System.out.println("Please follow this format: deadline "
                    + "[enter maxine.task] /by [enter deadline]");
        }
    }


    /**
     * Adds event task to the tasklist.
     * Exception is thrown if the user does not follow 
     * the format for event task
     * @param arr Array of Strings containing user's input
     * @throws MaxineException Exception if the user does not follow format
     */
    public void addEvent(String[] arr) throws MaxineException {
        try {
            StringBuilder desc = new StringBuilder();
            StringBuilder start = new StringBuilder();
            StringBuilder end = new StringBuilder();
            desc.append(arr[1]);
            boolean hasFrom = false;
            boolean hasTo = false;
            for (int i = 2; i < (arr.length - 1); i++) {
                if (arr[i].equals("/from")) {
                    hasFrom = true;
                }
                if (arr[i].equals("/to")) {
                    hasTo = true;
                }
            }

            if (!hasFrom || !hasTo || arr[1].equals("/from")) {
                throw new MaxineException("Please follow this format: "
                        + "event [enter event] /from [start date] /to [end date]");
            }
            boolean isAfterFrom = false;
            boolean isAfterTo = false;
            for (int i = 2; i < arr.length; i++) {
                if (arr[i].equals("/from")) {
                    isAfterFrom = true;
                }
                else if (arr[i].equals("/to")) {
                    isAfterFrom = false;
                    isAfterTo = true;
                } else if (isAfterFrom) {
                    String word = " " + arr[i];
                    start.append(word);
                } else if (isAfterTo) {
                    String word = " " + arr[i];
                    end.append(word);
                } else {
                    String word = " " + arr[i];
                    desc.append(word);
                }
            }
            Event task = new Event(desc.toString(), start.toString(), end.toString());
            list.add(task);
        } catch (Exception e) {
            System.out.println("Please follow this format: event [enter event] " +
                    "/from [start date] /to [end date]");
        }
    }

    /**
     * Deletes the task in the collection that corresponds to the key
     * @param key The number that is tagged to the task that os to be deleted
     */
    public void delete(int key) {
        try {
            Task task = list.get(key);
            list.remove(task);
        } catch (Exception e) {
            if (e instanceof InvocationTargetException) {
                Throwable cause = e.getCause();
                System.out.println("Exception caused by: " + cause);
            } else {
                System.out.println(e);
            }
        }
    }

    /**
     * Returns the size of the current task list
     * @return Size of current task list
     */
    public int size() {
        return list.size();
    }

    /**
     * Retrieves the Task in the task list that corresponds to the number
     * @param num
     * @return task
     */
    public Task get(int num) {
        return list.get(num);
    }


    /**
     * Returns an iterator over the elements in the list.
     * <p>
     * This method provides an iterator that can be used to traverse the elements
     * of the list in a sequential manner. The iterator supports both read and remove operations.
     * </p>
     *
     * @return an {@link Iterator} over the elements in the list
     */
    @Override
    public Iterator<Task> iterator() {
        return list.iterator();
    }
}
