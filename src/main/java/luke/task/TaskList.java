package luke.task;

import java.util.ArrayList;
import java.util.List;

import luke.env.Constants;

/**
 * TaskList.java --- stores a ArrayList of Tasks (todo, deadline, event).
 * It also has methods that can be used to modify or inspect the state of the Task ArrayList.
 * @author frymash
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the task list.
     * @param index index of the task in the task list.
     */
    public void removeTask(int index) {
        Task task = taskList.remove(index);
        System.out.println(Constants.DIVIDER
                + "alright i've purged this task for you:\n"
                + Constants.INDENT + task.taskDescription() + "\n"
                + listSizeUpdateMessage()
                + Constants.DIVIDER);
    }

    /**
     * Prints a message to inform the user that the task they had called does not exist.
     * @param taskNumber the index number of the task the user had tried to call.
     */
    public static void taskNotFound(int taskNumber) {
        System.out.println(Constants.DIVIDER
                + "task " + taskNumber + " doesn't exist...try another number!\n"
                + Constants.DIVIDER);
    }

    /**
     * Returns information on the size of the task list.
     * @return a string message with information on the size of the task list.
     */
    public String listSizeUpdateMessage() {
        int listSize = taskList.size();
        if (listSize == 1) {
            return "your list has " + listSize + " item now.\n";
        } else {
            return "your list has " + listSize + " items now.\n";
        }
    }

    /**
     * Adds a task from user input or save data to the task list.
     * @param command task type (todo, deadline, event).
     * @param taskDetails details of the task.
     * @param isMarked true if the task to be added is marked as done.
     * @param isLoadingFromDisk true if the task to be added originated from save data.
     * @throws NoDescriptionException thrown when args is a blank string.
     * @throws UnknownCommandException thrown when any command apart from the task types are passed to the function.
     */
    public void addToList(String command, String taskDetails, boolean isMarked, boolean isLoadingFromDisk)
            throws NoDescriptionException, UnknownCommandException {
        if (!(Constants.TASK_TYPES.contains(command))) {
            throw new UnknownCommandException();
        }
        if (taskDetails.isEmpty()) {
            throw new NoDescriptionException();
        }
        switch (command) {
        case "todo" -> {
            Todo todo = new Todo(taskDetails, isMarked);
            taskList.add(todo);
            if (!(isLoadingFromDisk)) {
                System.out.println(Constants.DIVIDER
                        + "i've thrown this to-do into your task list:\n"
                        + Constants.INDENT + todo.taskDescription() + "\n"
                        + listSizeUpdateMessage()
                        + Constants.DIVIDER);
            }
        }
        case "deadline" -> {
            String[] taskAndDeadline;
            if (isLoadingFromDisk) {
                taskAndDeadline = taskDetails.split(" by ");
            } else {
                taskAndDeadline = taskDetails.split(" /by ");
            }
            String taskName = taskAndDeadline[0];
            String deadline = taskAndDeadline[1];
            Deadline dl = new Deadline(taskName, deadline, isMarked);
            addTask(dl);
            if (!(isLoadingFromDisk)) {
                System.out.println(Constants.DIVIDER
                        + "the new deadline's been added to your task list:\n"
                        + Constants.INDENT + dl.taskDescription() + "\n"
                        + listSizeUpdateMessage()
                        + Constants.DIVIDER);
            }
        }
        case "event" -> {
            String[] taskAndTimings;
            if (isLoadingFromDisk) {
                taskAndTimings = taskDetails.split(" from | to ");
            } else {
                taskAndTimings = taskDetails.split(" /from | /to ");
            }
            String taskName = taskAndTimings[0];
            String from = taskAndTimings[1];
            String to = taskAndTimings[2];
            Event event = new Event(taskName, from, to, isMarked);
            addTask(event);
            if (!(isLoadingFromDisk)) {
                System.out.println(Constants.DIVIDER
                        + "aaaaand this event is now in your task list:\n"
                        + Constants.INDENT + event.taskDescription() + "\n"
                        + listSizeUpdateMessage()
                        + Constants.DIVIDER);
            }
        }
        default -> throw new UnknownCommandException();
        }
    }

    /**
     * Filters the task list to include only tasks that contain the specified search term.
     * The search matches substrings within task descriptions.
     * @param searchTerm the term the user is searching for.
     * @return a list of tasks that contain the specified search term within their descriptions.
     */
    public List<Task> findTasks(String searchTerm) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.name.contains(searchTerm)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Prints the current list of tasks to the standard output.
     * Each task is displayed with an index number followed by its description.
     */
    public void printList() {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println((i + 1) + ". " + task.taskDescription());
        }
    }
}
