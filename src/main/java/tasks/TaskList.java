package tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import chatterboxexceptions.ChatterboxExceptions;
import tags.Tag;


/**
 * Represents a list of tasks
 */
public class TaskList {



    private final ArrayList<Task> userTasks;

    public TaskList(ArrayList<Task> userTasks) {
        this.userTasks = userTasks;
    }

    /**
     * gets the userTasks of tasks.TaskList object
     *
     * @return an ArrayList userTasks
     */
    public ArrayList<Task> getTasks() {
        assert userTasks != null;
        return userTasks;
    }


    /**
     * Marks task at index to be complete
     *
     * @param index of task to be marked complete
     * @return returns the task that was marked
     */
    public Task markTask(int index) {
        assert index >= 0;
        userTasks.get(index).setStatus(true);
        return userTasks.get(index);
    }

    /**
     * Marks task at index to be not complete
     *
     * @param index to be mark incomplete
     * @return the task that was unmarked
     */
    public Task unmarkTask(int index) {
        userTasks.get(index).setStatus(false);
        return userTasks.get(index);
    }

    /**
     * Adds a Todo to the Tasklist
     * @param desc
     * @return Returns the Todo that was added
     * @throws ChatterboxExceptions.ChatterBoxNoInput
     */
    public Todo addTodo(String desc) throws ChatterboxExceptions.ChatterBoxNoInput {
        Todo nextTodo = new Todo(desc);
        userTasks.add(nextTodo);
        return nextTodo;
    }

    /**
     * Adds a Deadline to the Tasklist
     *
     * @param desc
     * @param endDate
     * @return RETURNS the Deadline that was added
     * @throws ChatterboxExceptions.ChatterBoxNoInput
     */
    public Deadline addDeadline(String desc, String endDate) throws ChatterboxExceptions.ChatterBoxNoInput {
        Deadline nextDead = new Deadline(desc, endDate);
        userTasks.add(nextDead);
        return nextDead;
    }

    /**
     * Adds a Deadline to the Tasklist
     *
     * @param desc
     * @param endDate
     * @return the deadline Added
     * @throws ChatterboxExceptions.ChatterBoxNoInput
     */
    public Deadline addDeadline(String desc, LocalDateTime endDate) throws ChatterboxExceptions.ChatterBoxNoInput {
        Deadline nextDead = new Deadline(desc, endDate);
        userTasks.add(nextDead);
        return nextDead;
    }

    /**
     *  Adds an Event to the Tasklist
     * @param desc
     * @param startDate
     * @param endDate
     * @return the event added
     * @throws ChatterboxExceptions.ChatterBoxNoInput
     */
    public Event addEvent(String desc, String startDate, String endDate)
            throws ChatterboxExceptions.ChatterBoxNoInput {
        Event nextEve = new Event(desc, startDate, endDate);
        assert nextEve != null;
        userTasks.add(nextEve);
        return nextEve;
    }

    /**
     * Adds an Event to the Tasklist
     *
     * @param desc      description of event
     * @param startDate start date of event
     * @param endDate   end date of event
     * @return the created event
     * @throws ChatterboxExceptions.ChatterBoxNoInput if no description was found
     */
    public Event addEvent(String desc, LocalDateTime startDate, LocalDateTime endDate)
            throws ChatterboxExceptions.ChatterBoxNoInput {
        Event nextEve = new Event(desc, startDate, endDate);
        userTasks.add(nextEve);
        return nextEve;
    }

    /**
     * Gets the task at index
     *
     * @param index
     * @return Task at index
     */
    public Task getTask(int index) {
        return userTasks.get(index);
    }

    /**
     * Deletes task and index and returns it
     *
     * @param index of task to be deleted
     * @return delted Task
     */
    public Task deleteTask(int index) {
        return userTasks.remove(index);
    }

    /**
     * returns the description of a task
     *
     * @param index
     * @return
     */
    public String getTaskDescription(int index) {
        assert index >= 0;
        return userTasks.get(index).getDescription();
    }

    /**
     * Get size of task list
     *
     * @return size of task list
     */
    public int size() {
        return userTasks.size();
    }

    /**
     * returns an ArrayList
     *
     * @param keywords is a string of keywords that should appear
     * @return ArrayList with only tasks that have the keywords
     */
    public ArrayList<Task> findTasks(String keywords) {
        assert userTasks != null;

        return userTasks.stream()
                .filter(task -> task.getDescription().contains(keywords))
                .collect(Collectors.toCollection(ArrayList::new));

    }

    public void tagTask(int index, Tag tag) {
        userTasks.get(index).addTag(tag);
    }


}
