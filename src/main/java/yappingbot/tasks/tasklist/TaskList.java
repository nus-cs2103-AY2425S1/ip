package yappingbot.tasks.tasklist;

import static yappingbot.tasks.tasklist.TaskParser.parseSingleTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotInvalidSaveFileException;
import yappingbot.exceptions.YappingBotOobException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.Task;
import yappingbot.ui.Ui;

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
     * Creates a task list populated with values from the given ArrayList of Strings.
     *
     * @param tasksRaw ArrayList of Strings, each a line that denotes a serialized task.
     */
    public YappingBotInvalidSaveFileException generateFromRaw(ArrayList<String> tasksRaw) {
        assert tasksRaw != null;

        ArrayList<Exception> errorLists = new ArrayList<>();
        for (String taskIndividualRaw : tasksRaw) {
            String[] s = taskIndividualRaw.split(":");
            try {
                this.addTask(parseSingleTask(s));
            } catch (YappingBotException e) {
                errorLists.add(e);
            }
        }

        if (!errorLists.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Exception e : errorLists) {
                // TODO: replace with exception collector class
                sb.append(e.toString());
                sb.append('\n');
            }
            return new YappingBotInvalidSaveFileException(sb.toString());
        }
        return null;
    }

    /**
     * Creates an ArrayList of Strings values, each containing the serialized version of the tasks.
     *
     * @return ArrayList of Strings of each serialized task.
     */
    public ArrayList<String> toRawFormat() {
        assert tasks != null;
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
        assert tasks != null;
        assert task != null;
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
        assert tasks != null;
        assert index < size;
        assert tasks.size() == size;

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
        assert tasks.size() == size;
        return size == 0;
    }

    /**
     * Returns size of list.
     *
     * @return integer of the count of tasks in list.
     */

    public int size() {
        assert tasks.size() == size;
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
        assert tasks != null;
        assert tasks.size() == size;
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
        assert tasks != null;
        tasks.forEach(action);
    }

    @Override
    public Spliterator<Task> spliterator() {
        assert tasks != null;
        return tasks.spliterator();
    }
}
