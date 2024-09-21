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
     * Gets the ArrayList userTasks
     *
     * @return an ArrayList Task objects
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
     * @param desc description of the task to be added
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
     * @param desc description of the task to be added
     * @param endDate end date of the deadline
     * @return Returns the Deadline that was added
     * @throws ChatterboxExceptions.ChatterBoxNoInput
     */
    public Deadline addDeadline(String desc, String endDate) throws ChatterboxExceptions.ChatterBoxNoInput {
        Deadline nextDead = new Deadline(desc, endDate);
        userTasks.add(nextDead);
        return nextDead;
    }

    /**
     * Adds a Deadline to the Tasklist with LocalDateTime Object
     *
     * @param desc description of the deadline to be added
     * @param endDate LocalDateTime representation end date of the deadline
     * @return the Deadline Object Added to the Tasklist
     * @throws ChatterboxExceptions.ChatterBoxNoInput
     */
    public Deadline addDeadline(String desc, LocalDateTime endDate) throws ChatterboxExceptions.ChatterBoxNoInput {
        Deadline nextDead = new Deadline(desc, endDate);
        userTasks.add(nextDead);
        return nextDead;
    }

    /**
     *  Adds an Event to the Tasklist
     * @param desc description of the event
     * @param startDate start date of the event in string format
     * @param endDate end date of the event in string format
     * @return the Event object that was added
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
     * @param desc description of event
     * @param startDate LocalDateTime start date of event
     * @param endDate LocalDateTime end date of event
     * @return the Event object that was added
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
     * @return deleted Task object
     */
    public Task deleteTask(int index) {
        return userTasks.remove(index);
    }

    /**
     * Returns the description of the task at index in the list
     *
     * @param index
     * @return description of task at index
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
     * Returns an ArrayList of Task Objects matching the keywords
     *
     * @param keywords is a string of keywords that should appear in task description
     * @return ArrayList with only Task that have the keywords in description
     */
    public ArrayList<Task> findTasks(String keywords) {
        assert userTasks != null;

        return userTasks.stream()
                .filter(task -> task.getDescription().contains(keywords))
                .collect(Collectors.toCollection(ArrayList::new));

    }

    /**
     * Tags a task with a tag at index
     *
     * @param tag is Tag object to add the tag to the task
     *
     */
    public void tagTask(int index, Tag tag) {
        this.userTasks.get(index).addTag(tag);
        tag.tagTask(this.userTasks.get(index));
    }


}
