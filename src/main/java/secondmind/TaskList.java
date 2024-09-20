package secondmind;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Manages a list of tasks, providing methods to add, retrieve, and manipulate tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for the TaskList class.
     *
     * @param taskList An ArrayList of tasks to initialize the task list.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The count of tasks in the list.
     */
    public int getTaskCount() {
        return taskList.size();
    }

    private String formatDateTime(String dateTime)
            throws DateTimeParseException, PassedDateTimeException {
        LocalDateTime ldt = LocalDateTime.parse(dateTime);
        if (ldt.isBefore(LocalDateTime.now())) {
            throw new PassedDateTimeException();
        }
        DateTimeFormatter formatter 
            = DateTimeFormatter.ofPattern(
                    "d MMM yyyy HH:mm:ss a");
        return ldt.format(formatter);
    }

    private Task createToDo(String[] taskInfo) throws EmptyTaskDescriptionException {
        if (taskInfo.length == 1) {
            throw new EmptyTaskDescriptionException();
        }
        taskInfo[0] = "";
        String taskDescription = String.join(" ", taskInfo);
        taskDescription = taskDescription.substring(1);
        return new ToDoTask(taskDescription);
    }

    private Task createDeadline(String[] taskInfo)
            throws PassedDateTimeException, EmptyTaskDescriptionException {
        if (taskInfo.length == 1) {
            throw new EmptyTaskDescriptionException();
        }
        //Format of taskInfo:
        //["deadline", description, /by, {deadline}]
        taskInfo[0] = "";
        String[] newTaskInfo = String.join(" ", taskInfo).split(" /by ");
        String taskDescription = newTaskInfo[0].trim();
        String taskDeadline = formatDateTime(newTaskInfo[1]);
        return new DeadlineTask(taskDescription, taskDeadline);
    }

    private Task createEvent(String[] taskInfo)
            throws PassedDateTimeException, EmptyTaskDescriptionException {
        if (taskInfo.length == 1) {
            throw new EmptyTaskDescriptionException();
        }
        //Format of taskInfo:
        //["event", {description}, /from, {taskStart}, /to, {eventEnd}]
        String[] newTaskInfo = String.join(" ", taskInfo).split(" /");
        //Format of newTaskInfo:
        //["event {description}", "from {taskStart}", "to {eventEnd}"]
        //Prefix of taskInfo[0] is "event "
        String taskDescription = newTaskInfo[0].substring(6);
        //Prefix of taskInfo[1] is "from "
        String taskStart = formatDateTime(newTaskInfo[1].substring(5));
        //Prefix of taskInfo[2] is "to "
        String taskEnd = formatDateTime(newTaskInfo[2].substring(3));
        return new EventTask(taskDescription, taskStart, taskEnd);
    }

    /**
     * Create a Task object from a single String[] instruction.
     *
     * @param instruction The instruction to be converted to a Task object.
     * @throws EmptyCommandException If the command is empty.
     * @throws EmptyTaskDescriptionException If the task description is empty.
     * @throws UnknownCommandException If the command is unknown.
     * @throws DateTimeParseException If the date-time format is invalid.
     */
    public Task createTask(String[] instruction)
            throws EmptyCommandException, EmptyTaskDescriptionException, UnknownCommandException,
                    DateTimeParseException, PassedDateTimeException {
        String[] taskInfo = instruction[1].split(" ");
        String taskType = taskInfo[0];
        if (instruction[0].equals("")) {
            throw new EmptyCommandException();
        } else if (taskType.equals("todo")) {
            return createToDo(taskInfo);
        } else if (taskType.equals("deadline")) {
            return createDeadline(taskInfo);
        } else if (taskType.equals("event")) {
            return createEvent(taskInfo);
        } else {
            throw new UnknownCommandException();
        }
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task object to be added.
     * @throws EmptyCommandException If the command is empty.
     * @throws EmptyTaskDescriptionException If the task description is empty.
     * @throws UnknownCommandException If the command is unknown.
     * @throws DateTimeParseException If the date-time format is invalid.
     */
    public void addToTaskList(Task task) {
        taskList.add(task);
    }

    /**
     * Gets the list of tasks.
     *
     * @return An ArrayList of Task objects.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public ArrayList<Task> getMatchingTasks(String match) {
        ArrayList<Task> filteredTaskList = new ArrayList<>();
        for (int i = 1; i <= this.getTaskCount(); i++) {
            Task curr = this.getTask(i);
            if (curr.getDescription().contains(match)) {
                filteredTaskList.add(curr);
            }
        }
        return filteredTaskList;
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber The number of the task to mark as done.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public void markAsDone(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber <= 0 || taskNumber > getTaskCount()) {
            throw new InvalidTaskNumberException();
        } else {
            Task curr = this.taskList.get(taskNumber-1);
            curr.markAsDone();
        }
    }

    /**
     * Marks a task as undone.
     *
     * @param taskNumber The number of the task to mark as undone.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public void markAsUndone(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber <= 0 || taskNumber > getTaskCount()) {
            throw new InvalidTaskNumberException();
        } else {
            Task curr = this.taskList.get(taskNumber-1);
            curr.markAsUndone();
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param taskNumber The number of the task to delete.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public void delete(int taskNumber) throws InvalidTaskNumberException {
        this.taskList.remove(taskNumber-1);
    }

    /**
     * Retrieves a task from the list.
     *
     * @param taskNumber The number of the task to retrieve. (1-Indexed)
     * @return The Task object.
     */
    public Task getTask(int taskNumber) {
        return this.taskList.get(taskNumber-1);
    }

    public boolean checkForDuplicate(Task newTask) {
        for (Task task: this.taskList) {
            if (task.equals(newTask)) {
                return true;
            }
        }
        return false;
    }
}