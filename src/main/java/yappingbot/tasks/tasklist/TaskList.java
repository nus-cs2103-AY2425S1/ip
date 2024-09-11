package yappingbot.tasks.tasklist;

import static yappingbot.tasks.tasklist.TaskParser.parseSingleTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotExceptionList;
import yappingbot.exceptions.YappingBotOobException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.Task;

/**
 * TaskList container to hold valid Tasks.
 */
public class TaskList implements Iterable<Task> {
    protected ArrayList<Task> tasks;
    protected int size;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a task list populated with values from the given ArrayList of Strings. Exceptions
     * caught here are returned instead of thrown so that the loop can continue without having
     * throws that interrupt the process.
     *
     * @param tasksRaw ArrayList of Strings, each a line that denotes a serialized task.
     * @return YappingBotException for any caught errors.
     */
    public YappingBotExceptionList generateFromRaw(ArrayList<String> tasksRaw) {
        // Lord forgive me for returning execptions without throwing them, very illegal

        YappingBotExceptionList exceptions = new YappingBotExceptionList();
        for (String taskIndividualRaw : tasksRaw) {
            String[] s = taskIndividualRaw.split(":");
            try {
                this.addTask(parseSingleTask(s));
            } catch (YappingBotException e) {
                exceptions.add(e);
            }
        }
        return exceptions;
    }

    /**
     * Creates an ArrayList of Strings values, each containing the serialized version of the tasks.
     *
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
    public void addTask(Task task) {
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
    public Task deleteTask(int index) throws YappingBotOobException {
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
     *
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
            throw new YappingBotOobException(ReplyTextMessages.SELECT_TASK_MISSING_TEXT_1d, index);
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
