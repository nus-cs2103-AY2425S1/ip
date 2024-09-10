package janet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    // replaces the ArrayList<janet.Task> listOfTasks inside Janet
    private final ArrayList<Task> listOfTasks;

    TaskList() {
        listOfTasks = new ArrayList<>();
    }


    TaskList(ArrayList<Task> tasks) {
        // overloaded constructor to load the tasks created from janet.txt
        this.listOfTasks = tasks;
    }


    public int getNumberOfTasks() {
        return this.listOfTasks.size();
    }


    /**
     * Returns true if the list of tasks is empty, false otherwise.
     *
     * @return A boolean value indicating whether the list is empty.
     */
    public boolean isEmpty() {
        return this.listOfTasks.isEmpty();
    }


    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }


    public Task getTask(int index) {
        return this.listOfTasks.get(index);
    }


    /**
     * Adds the task into the current list of tasks.
     *
     * @param task A janet.Task object that is to be added into the listOfTasks.
     */
    public void addTaskToList(Task task) {
        this.listOfTasks.add(task);
    }


    /**
     * Returns a String message to indicate task is marked.
     * If task has already been marked, returns a different String message.
     *
     * @param desiredTaskNum The task that the user specified.
     * @return A String message to indicate successful marking of desired task.
     */
    public String markAsDone(int desiredTaskNum) {
        // (desiredTaskNum - 1) is the index of the task, inside listOfTasks, that needs to be marked as done
        Task desiredTask = listOfTasks.get(desiredTaskNum - 1);
        if (desiredTask.isDone()) {
            // the desired task is already marked as done
            return "already marked";
        }
        desiredTask.setDone(true);
        return "marked";
    }


    /**
     * Returns a String message to indicate task is unmarked.
     * If task has already been unmarked, returns a different String message.
     *
     * @param desiredTaskNum The task that the user specified.
     * @return A String message to indicate successful unmarking of desired task.
     */
    public String unmark(int desiredTaskNum) {
        // (desiredTaskNum - 1) is the index of the task, inside listOfTasks, that needs to be unmarked
        Task desiredTask = listOfTasks.get(desiredTaskNum - 1);
        if (!desiredTask.isDone()) {
            // desired task is already marked as NOT done (unmarked)
            return "already unmarked";
        }
        desiredTask.setDone(false);
        return "unmarked";
    }


    /**
     * Removes the task from the list of tasks.
     *
     * @param desiredTaskNum The task that the user specified.
     */
    public void deleteTask(int desiredTaskNum) {
        Task desiredTask = listOfTasks.get(desiredTaskNum - 1);
        listOfTasks.remove(desiredTask);
    }


    /**
     * Returns a TaskList with tasks' description containing the keyword(s).
     *
     * @param keyword The desired keyword to be matched to.
     * @return A TaskList.
     */
    public TaskList findTasks(String... keyword) {
        // returns a TaskList, where tasks have the keyword in their descriptions.
        TaskList taskList = new TaskList();
        for (Task task : listOfTasks) {
            for (String word : keyword) {
                if (task.getDescription().contains(word)) {
                    taskList.addTaskToList(task);
                }
            }
        }
        return taskList;
    }


    /**
     * Returns true if the task is a deadline or event type,
     * false otherwise.
     *
     * @param task A Task object.
     * @return A boolean value.
     */
    private boolean isDeadlineOrEvent(Task task) {
        // returns true if the task is either a deadline or event.
        return task.getSymbol().equals("D") || task.getSymbol().equals("E");
    }


    /**
     * Returns true if the task's scheduledDate is equal to the schedule.
     * 1. if task is a deadline, its scheduledDate will be the dueDate.
     * 2. if task is an event, its scheduledDate will be the startDate.
     * Returns false otherwise.
     *
     * @param task A Task object.
     * @param schedule A LocalDate, representing the date the user inputs.
     * @return A boolean value.
     */
    private boolean isScheduledTask(Task task, LocalDate schedule) {
        return task.getScheduledDate().equals(schedule);
    }


    /**
     * Returns a TaskList, where each task in the TaskList is
     * either a deadline or an event,
     * and has a dueDate/startDate equals to the schedule.
     *
     * @param dateAndTime A String, representing the date the user inputs (yyyy-MM-dd).
     * @return A TaskList.
     */
    public TaskList viewScheduledTasks(String dateAndTime) throws JanetException {
        LocalDate schedule = null;
        try {
            schedule = LocalDate.parse(dateAndTime);
        } catch (DateTimeParseException e) {
            throw new JanetException("WHOOPS! Please ensure date is in yyyy-MM-dd format!");
        }
        TaskList tasks = new TaskList();
        for (Task task : listOfTasks) {     // go through all the tasks in the list
            if (isDeadlineOrEvent(task) && isScheduledTask(task, schedule)) {
                // if the task is deadline/event and has the same schedule, add to tasks
                tasks.addTaskToList(task);
            }
        }
        return tasks;
    }

}
