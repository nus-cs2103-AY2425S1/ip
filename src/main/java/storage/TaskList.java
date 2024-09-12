package storage;

import java.util.ArrayList;

import commands.Command;
import exceptions.InvalidTaskException;
import tasks.Deadline;
import tasks.Event;
import tasks.Priority;
import tasks.Task;
import tasks.Todo;

/**
 * Stores the list of tasks.
 * Responsible for manipulating tasks, such as adding, marking, unmarking and deleting.
 * Responsible for printing out all the tasks in the list.
 */
public class TaskList {
    private final ArrayList<Task> highPriorityTasks = new ArrayList<>();
    private final ArrayList<Task> mediumPriorityTasks = new ArrayList<>();
    private final ArrayList<Task> lowPriorityTasks = new ArrayList<>();

    /**
     * Adds a task to TASKS.
     *
     * @param type type of task to be added.
     * @param params arguments that the task has.
     * @return the task that has been added to TASKS.
     */
    public Task addTask(Command type, String ... params) {
        Task task = new Task(params[0]);
        int numOfParams = params.length;

        assert type == Command.TODO
                || type == Command.DEADLINE
                || type == Command.EVENT
                : "invalid task type to call function addTask";

        switch (type) {
        case TODO:
            task = numOfParams == 1 ? new Todo(params[0]) : new Todo(params[0], params[1]);
            break;
        case DEADLINE:
            task = numOfParams == 2
                    ? new Deadline(params[0], params[1])
                    : new Deadline(params[0], params[1], params[2]);
            break;
        case EVENT:
            task = numOfParams == 3
                    ? new Event(params[0], params[1], params[2])
                    : new Event(params[0], params[1], params[2], params[3]);
            break;
        default:
            break;
        }

        String priority = params[numOfParams - 1];
        switch (priority) {
        case "high":
            highPriorityTasks.add(task);
            break;
        case "medium":
            mediumPriorityTasks.add(task);
            break;
        case "low":
            lowPriorityTasks.add(task);
            break;
        default:
            lowPriorityTasks.add(task);
            break;
        }
        return task;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        Priority priority = task.getPriority();
        switch (priority) {
        case HIGH:
            highPriorityTasks.add(task);
            break;
        case MEDIUM:
            mediumPriorityTasks.add(task);
            break;
        case LOW:
            lowPriorityTasks.add(task);
            break;
        default:
            break;
        }
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber the (index + 1) of the task.
     * @param priority the priority list to index.
     * @return the task that was marked.
     * @throws InvalidTaskException if taskNumber > TASKS.size() or taskNumber == 0.
     */
    public Task markTask(int taskNumber, String priority) throws InvalidTaskException {
        checkValidTaskIndex(taskNumber, priority);
        Task markTask;

        switch (priority) {
        case "high":
            markTask = highPriorityTasks.get(taskNumber - 1);
            break;
        case "medium":
            markTask = mediumPriorityTasks.get(taskNumber - 1);
            break;
        case "low":
            markTask = lowPriorityTasks.get(taskNumber - 1);
            break;
        default:
            markTask = new Task("");
        }

        markTask.markAsDone();
        return markTask;
    }

    /**
     * Unmarks a task.
     *
     * @param taskNumber the (index + 1) of the task.
     * @param priority the priority list to index.
     * @return the task that was unmarked.
     * @throws InvalidTaskException if taskNumber > TASKS.size() or taskNumber == 0.
     */
    public Task unmarkTask(int taskNumber, String priority) throws InvalidTaskException {
        checkValidTaskIndex(taskNumber, priority);
        Task unmarkTask;

        switch (priority) {
        case "high":
            unmarkTask = highPriorityTasks.get(taskNumber - 1);
            break;
        case "medium":
            unmarkTask = mediumPriorityTasks.get(taskNumber - 1);
            break;
        case "low":
            unmarkTask = lowPriorityTasks.get(taskNumber - 1);
            break;
        default:
            unmarkTask = new Task("");
        }

        unmarkTask.markAsUndone();
        return unmarkTask;
    }

    /**
     * Deletes the specified task.
     *
     * @param taskNumber the (index + 1) of the task.
     * @return the task that was deleted.
     * @throws InvalidTaskException if taskNumber > TASKS.size() or taskNumber == 0.
     */
    public Task deleteTask(int taskNumber, String priority) throws InvalidTaskException {
        checkValidTaskIndex(taskNumber, priority);

        switch (priority) {
        case "high":
            return highPriorityTasks.remove(taskNumber - 1);
        case "medium":
            return mediumPriorityTasks.remove(taskNumber - 1);
        case "low":
            return lowPriorityTasks.remove(taskNumber - 1);
        default:
            return new Task("");
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return number of tasks in the list.
     */
    public int getSize() {
        return this.highPriorityTasks.size()
                + this.mediumPriorityTasks.size()
                + this.lowPriorityTasks.size();
    }

    /**
     * Returns the string representation of all the tasks in the list.
     *
     * @return string of tasks.
     */
    public String listTasks(ArrayList<Task> tasks) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String taskInfo = String.format("%d. %s", i + 1, tasks.get(i).toString());
            s.append(taskInfo).append("\n");
        }
        return s.toString();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index index in array.
     * @return task at the given index.
     */
    public Task getTask(int index) {
        return getAllTasks().get(index);
    }

    /**
     * Returns ArrayList of all tasks in the TaskList.
     *
     * @return arraylist containing all the tasks.
     */
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> allTasks = new ArrayList<>(highPriorityTasks);
        allTasks.addAll(mediumPriorityTasks);
        allTasks.addAll(lowPriorityTasks);
        return allTasks;
    }

    /**
     * Finds tasks whose description matches the keyword.
     *
     * @param keyword the keyword to check.
     * @return String of tasks which contains the keyword.
     */
    public String findTasks(String keyword) {
        ArrayList<Task> allTasks = getAllTasks();
        ArrayList<Task> filtered = new ArrayList<>();

        for (Task task : allTasks) {
            String[] keywords = task.getDescription().split(" ");
            for (String word : keywords) {
                if (word.equals(keyword)) {
                    filtered.add(task);
                    break;
                }
            }
        }
        return listTasks(filtered);
    }

    /**
     * Checks if the task index is within the size of the task list.
     *
     * @param taskNumber the index to check.
     * @param priority the priority list.
     * @throws InvalidTaskException when index is outside range of list.
     */
    private void checkValidTaskIndex(int taskNumber, String priority) throws InvalidTaskException {
        ArrayList<Task> list = priority.equals("high")
                ? highPriorityTasks
                : priority.equals("medium")
                ? mediumPriorityTasks
                : lowPriorityTasks;
        if (taskNumber > list.size() || taskNumber <= 0) {
            throw new InvalidTaskException("", taskNumber);
        }
    }

    /**
     * Returns string of tasks of the given priority.
     *
     * @param priority the priority of tasks required.
     * @return string of tasks with that priority.
     */
    public String findTaskOfPriority(Priority priority) {
        ArrayList<Task> filtered = priority == Priority.HIGH
                ? highPriorityTasks
                : priority == Priority.MEDIUM
                ? mediumPriorityTasks
                : lowPriorityTasks;
        return listTasks(filtered);
    }

    /**
     * Returns the task for which the priority is updated.
     *
     * @param taskNumber index of task.
     * @param priority list which the task initially belongs.
     * @param newPriority new priority to be set.
     * @return the task which was updated.
     * @throws InvalidTaskException when taskNumber is not within array size.
     */
    public Task updatePriority(int taskNumber, String priority, String newPriority) throws InvalidTaskException {
        checkValidTaskIndex(taskNumber, priority);
        Task updateTask;

        switch (priority) {
        case "high":
            updateTask = highPriorityTasks.get(taskNumber - 1);
            break;
        case "medium":
            updateTask = mediumPriorityTasks.get(taskNumber - 1);
            break;
        case "low":
            updateTask = lowPriorityTasks.get(taskNumber - 1);
            break;
        default:
            updateTask = new Task("");
        }

        updateTask.setPriority(newPriority);
        return updateTask;
    }
}
