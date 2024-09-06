package secondmind;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

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

    private String formatDateTime(String dateTime) throws DateTimeParseException {
        LocalDateTime ldt = LocalDateTime.parse(dateTime);
        DateTimeFormatter formatter 
            = DateTimeFormatter.ofPattern(
                    "d MMM yyyy HH:mm:ss a");
        return ldt.format(formatter);
    }

    private Task createToDo(String[] taskInfo) throws EmptyToDoException {
        if (taskInfo.length == 1) {
            throw new EmptyToDoException();
        }
        taskInfo[0] = "";
        String taskDescription = String.join(" ", taskInfo);
        taskDescription = taskDescription.substring(1);
        return new ToDoTask(taskDescription);
    }

    private Task createDeadline(String task) {
        String[] taskInfo = task.split(" /by ");
        //Prefix of taskInfo[0] is "deadline "
        String taskDescription = taskInfo[0].substring(9);
        String taskDeadline = formatDateTime(taskInfo[1]);
        return new DeadlineTask(taskDescription, taskDeadline);
    }

    private Task createEvent(String task) {
        String[] taskInfo = task.split(" /", 3);
        //Prefix of taskInfo[0] is "event "
        String taskDescription = taskInfo[0].substring(6);
        //Prefix of taskInfo[1] is "from "
        String taskStart = formatDateTime(taskInfo[1].substring(5));
        //Prefix of taskInfo[2] is "to "
        String taskEnd = formatDateTime(taskInfo[2].substring(3));
        return new EventTask(taskDescription, taskStart, taskEnd);
    }

    private Task createTask(String task) 
            throws EmptyCommandException, EmptyToDoException, UnknownCommandException {
        String[] taskInfo = task.split(" ");
        String taskType = taskInfo[0];
        if (taskInfo[0].equals("")) {
            throw new EmptyCommandException();
        } else if (taskType.equals("todo")) {
            try {
                return createToDo(taskInfo);
            } catch (EmptyToDoException e) {
                throw e;
            }
        } else if (taskType.equals("deadline")) {
            return createDeadline(task);
        } else if (taskType.equals("event")) {
            return createEvent(task);
        } else {
            throw new UnknownCommandException();
        }
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task description to be added.
     * @return The created Task object.
     * @throws EmptyCommandException If the command is empty.
     * @throws EmptyToDoException If the ToDo task description is empty.
     * @throws UnknownCommandException If the command is unknown.
     * @throws DateTimeParseException If the date-time format is invalid.
     */
    public Task addToTaskList(String task)
            throws EmptyCommandException, EmptyToDoException,  UnknownCommandException, DateTimeParseException  {
        Task curr = createTask(task);
        taskList.add(curr);
        return curr;
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

    public void markAsDone(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber <= 0 || taskNumber > getTaskCount()) {
            throw new InvalidTaskNumberException(taskNumber);
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
            throw new InvalidTaskNumberException(taskNumber);
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
        try {
            this.taskList.remove(taskNumber-1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException(taskNumber);
        }
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
}