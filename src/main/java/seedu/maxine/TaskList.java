package seedu.maxine;

import seedu.maxine.exception.MaxineException;
import seedu.maxine.task.Deadline;
import seedu.maxine.task.Event;
import seedu.maxine.task.Task;
import seedu.maxine.task.Todo;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void addTodo(String input) {
        try {
            String[] answer = input.split("todo ");
            String regex = "todo";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (answer.length != 2 || !matcher.find()) {
                throw new MaxineException("Please follow this "
                        + "format: todo [enter maxine.task]");
            }
            String description = answer[1];
            Todo task = new Todo(description);
            list.add(task);
        } catch (Exception e) {
            System.out.println("Please follow this format: todo [enter maxine.task]");
        }
    }

    
    public void addDeadline(String input) throws MaxineException {
        try {
            String[] answer = input.split("deadline | /by ");
            String regex = "deadline.*?/by";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (answer.length != 3 || !matcher.find()) {
                throw new MaxineException("Please follow this format: deadline "
                        + "[enter maxine.task] /by [enter deadline]");
            }
            String description = answer[1];
            String deadline = answer[2];
            Deadline task = new Deadline(description, deadline);
            list.add(task);
        } catch (Exception e) {
            System.out.println("Please follow this format: deadline "
                    + "[enter maxine.task] /by [enter deadline]");
        }
    }


    /**
     * 
     * @param input
     * @throws MaxineException
     */
    public void addEvent(String input) throws MaxineException {
        try {
            String[] answer = input.split("event | /from | /to ");
            String regex = "event.*?/from.*?/to";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (answer.length != 4 || !matcher.find()) {
                throw new MaxineException("Please follow this format: event [enter event] "
                        + "/from [start date] /to [end date]");
            }
            String description = answer[1];
            String startTime = answer[2];
            String endTime = answer[3];
            Event task = new Event(description, startTime, endTime);
            list.add(task);
        } catch (MaxineException e) {
            System.out.println(e.getMessage());
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
