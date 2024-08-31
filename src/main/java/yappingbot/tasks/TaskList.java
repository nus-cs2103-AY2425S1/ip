package yappingbot.tasks;

import static yappingbot.tasks.TaskParser.parseSingleTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotOOBException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.ui.MultilineStringBuilder;
import yappingbot.ui.Ui;

/**
 * TaskList container to hold valid Tasks.
 */
public class TaskList implements Iterable<Task> {
    private static ArrayList<Task> tasks;
    private int size;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a task list populated with values from the given ArrayList of Strings.
     * @param tasksRaw ArrayList of Strings, each a line that denotes a serialized task.
     * @return TaskList, populated with appropriate values.
     */
    public static TaskList generateFromRaw(ArrayList<String> tasksRaw) {
        TaskList userList = new TaskList();
        ArrayList<Exception> errorLists = new ArrayList<>();
        for (String taskIndividualRaw : tasksRaw) {
            String[] s = taskIndividualRaw.split(":");
            try {
                userList.add(parseSingleTask(s));
            } catch (YappingBotException e) {
                errorLists.add(e);
            }
        }
        if (!errorLists.isEmpty()) {
            MultilineStringBuilder msb = new MultilineStringBuilder();
            for (Exception e : errorLists) {
                msb.addLine(e.getMessage());
            }
            Ui.printError(String.format(ReplyTextMessages.LOAD_FILE_ERROR_1s, msb));
        }
        return userList;
    }

    /**
     * Creates an ArrayList of Strings values, each containing the serialized version of the tasks.
     * @return ArrayList of Strings of each serialized task.
     */
    public ArrayList<String> toRawFormat() {
        ArrayList<String> taskListRaw = new ArrayList<>();
        for (Task t : tasks) {
            taskListRaw.add(t.serialize());
        }
        return taskListRaw;
    }

    /**
     * Adds task to list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
        size += 1;
    }

    /**
     * Removes a task in list at the specified index.
     *
     * @param index integer index of the task to be deleted.
     * @return Task that was deleted.
     * @throws YappingBotOobException Exception if index provided is out of bounds.
     */
    public Task delete(int index) throws YappingBotOobException {
        Task t = get(index);
        tasks.remove(index);
        size -= 1;
        return t;
    }

    /**
     * Returns whether list is empty or not.
     *
     * @return boolean of whether list is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns size of list.
     * @return integer of the count of tasks in list.
     */

    public int size() {
        return size;
    }

    /**
     * Returns the task saved at a given index.
     *
     * @param index integer index of the task to be accessed.
     * @return Task that was requested.
     * @throws YappingBotOobException Exception if index provided is out of bounds.
     */
    public Task get(int index) throws YappingBotOobException {
        if (index < 0 || index >= size) {
            throw new YappingBotOobException(index);
        }
        return tasks.get(index);
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    @Override
    public void forEach(Consumer<? super Task> action) {
        tasks.forEach(action);
    }

    @Override
    public Spliterator<Task> spliterator() {
        return tasks.spliterator();
    }
}
