package carly.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import carly.exception.CarlyException;
import carly.exception.CarlyIncorrectIndexFormat;
import carly.exception.CarlyIndexOutOfBoundsException;
import carly.exception.CarlyMissingDateTimeException;
import carly.tasks.Deadline;
import carly.tasks.Event;
import carly.tasks.Task;
import carly.tasks.Todo;

/** Represents a list of tasks. Handle a collection of {@link Task} objects. */
public class TaskList {

    /** List of Task objects.*/
    private final ArrayList<Task> taskList;

    /** TWO_INDENT used in displaying output messages. */
    private final String TWO_INDENT = "        ";

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task get(Integer index) {
        return this.taskList.get(index);
    }

    public Integer getSize() {
        return this.taskList.size();
    }

    public Boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    /** Gets the message fpr the current size of the task list. */
    private String taskListSize() {
        return String.format("You've got %d tasks on your plate!", this.getSize());
    }

    /**
     * Parses a task number string and converts it into an integer.
     *
     * @param taskNumString The task number string to parse.
     * @return The parsed task number as an integer.
     * @throws CarlyIncorrectIndexFormat If the task number format is invalid.
     */
    private int parseTaskNumber(String taskNumString) throws CarlyIncorrectIndexFormat {
        try {
            return Integer.parseInt(taskNumString);
        } catch (NumberFormatException e) {
            throw new CarlyIncorrectIndexFormat();
        }
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskNumString The task number to be marked as done.
     * @throws CarlyException If the task number format is incorrect or out of bounds.
     */
    public String mark(String taskNumString) throws CarlyException {
        return updateTaskStatus(taskNumString, true);
    }

    /**
     * Unmarks the specified task as not done yet.
     *
     * @param taskNumString The task number to be unmarked.
     * @throws CarlyException If the task number is out of bounds.
     */
    public String unmark(String taskNumString) throws CarlyException {
        return updateTaskStatus(taskNumString, false);
    }

    /**
     * Updates the status of a task by marking it as done or not done.
     *
     * @param taskNumString The task number to be updated.
     * @param isMarkingDone A boolean flag indicating whether the task is being marked as done (true) or not done (false).
     * @throws CarlyException If the task number format is incorrect or out of bounds.
     */
    private String updateTaskStatus(String taskNumString, boolean isMarkingDone) throws CarlyException {
        int taskNum = parseTaskNumber(taskNumString);

        try {
            Task task = this.get(taskNum - 1);
            Task updatedTask = isMarkingDone ? task.markAsDone() : task.unmarkAsDone();
            this.taskList.set(taskNum - 1, updatedTask);

            String statusMessage = isMarkingDone ? "done" : "not done yet";
            return String.format("Okiee! I've marked this task as %s:\n%s%s", statusMessage, TWO_INDENT, updatedTask);
        } catch (IndexOutOfBoundsException e) {
            throw new CarlyIndexOutOfBoundsException(taskNum, this.getSize());
        }
    }

    /**
     * Deletes the specified task from the list.
     *
     * @param taskNumString The task number to be deleted.
     * @throws CarlyException If the task number format is incorrect or out of bounds.
     */
    public String delete(String taskNumString) throws CarlyException {
        Integer taskNum = null;
        try {
            taskNum = Integer.parseInt(taskNumString);
            Task t = this.get(taskNum - 1);
            this.taskList.remove(taskNum - 1);
            return "All set! I've removed this task:\n" + TWO_INDENT + t;
        } catch (NumberFormatException e) {
            throw new CarlyIncorrectIndexFormat();
        } catch (IndexOutOfBoundsException e) {
            throw new CarlyIndexOutOfBoundsException(taskNum, this.getSize());
        }
    }

    /**
     * Finds tasks in the task list that contain the specified word in their description.
     * If matching tasks are found, they are added to a filtered task list and printed.
     *
     * @param word The word to search for in the task descriptions.
     */
    public String find(String word) {
        TaskList filteredList = new TaskList();
        for (Task t : this.taskList) {
            if (t.getDescription().contains(word)) {
                filteredList.taskList.add(t);
            }
        }
        TaskPrinter taskPrinter = new TaskPrinter(filteredList);
        return taskPrinter.printFindResults(word);
    }

    /**
     * Adds a new ToDo task to the list.
     *
     * @param taskDescription The description of the task.
     */
    public String addToDo(String taskDescription) {
        Todo t = new Todo(taskDescription);
        return this.addTaskToList(t);
    }

    /**
     * Adds a new Deadline task to the list.
     *
     * @param taskDescription The description and due date of the task, formatted as "description /by dueDate".
     * @throws CarlyMissingDateTimeException If the task description or due date is missing.
     */
    public String addDeadline(String taskDescription) throws CarlyException {
        try {
            String[] taskDueDate = taskDescription.split(" /by ");
            String task = taskDueDate[0];
            String dueDate = taskDueDate[1];

            Deadline t = new Deadline(task, dueDate);
            return this.addTaskToList(t);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CarlyMissingDateTimeException("task description or \"/by\" command");
        }
    }

    /**
     * Adds a new Event task to the list.
     *
     * @param taskDescription The description, start time, and end time of the task.
     * @throws CarlyMissingDateTimeException If the task description, start time, or end time is missing.
     */
    public String addEvent(String taskDescription) throws CarlyMissingDateTimeException {
        try {
            String[] taskTimeParts = taskDescription.split(" /from ");
            String task = taskTimeParts[0];
            String timeParts = taskTimeParts[1];
            String[] startEndTimeParts = timeParts.split(" /to ");
            String startTime = startEndTimeParts[0];
            String endTime = startEndTimeParts[1];

            Event t = new Event(task, startTime, endTime);
            return this.addTaskToList(t);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CarlyMissingDateTimeException("\"/from\" or \"/to\" command");
        }
    }

    /**
     * Adds a task to the task list and returns a success message.
     *
     * @param task The task to add.
     * @return The formatted success message.
     */
    private String addTaskToList(Task task) {
        this.taskList.add(task);
        assert this.getSize() >= 1 : "Size of tasklist should be at least 1.";
        String successMessage = "Awesome! I've added this task:";
        return successMessage + "\n" + TWO_INDENT + task + "\n" + taskListSize();
    }

    /** Generates a string representation of all tasks in the list to be saved in txt file. */
    public String getFormattedTaskList() {
        if (this.taskList.isEmpty()) {
            return "Nothing in your list";
        } else {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < this.getSize(); i++) {
                Task task = this.get(i);
                sb.append(formatTask(task)).append("\n");
            }

            return sb.toString().trim();
        }
    }

    /** Formats a single task into the required string format. */
    private String formatTask(Task task) {
        String taskType = getTaskType(task);
        String isDone = formatIsDone(task);
        String taskDescription = task.getDescription();
        String taskDetails = getTaskDetails(task);

        return String.format("%s | %s | %s%s", taskType, isDone, taskDescription, taskDetails);
    }

    /** Determines the task type (T for Todo, D for Deadline, E for Event). */
    private String getTaskType(Task task) {
        if (task instanceof Todo) {
            return "T";
        } else if (task instanceof Deadline) {
            return "D";
        } else {
            return "E";
        }
    }

    /** Formats whether the task is done for Storage of user commands in txt file (1 for done, 0 for not done). */
    private String formatIsDone(Task task) {
        return task.getIsDone() ? "1" : "0";
    }

    /** Retrieves the additional details for Deadline and Event tasks. */
    private String getTaskDetails(Task task) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.format(" | %s", deadline.getDueDateAsString());
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return String.format(" | %s to %s", event.getStartTime(), event.getEndTime());
        } else {
            return "";
        }
    }

    public String sort() {
        List<Deadline> deadlines = new ArrayList<>();
        List<Task> remainingTasks = new ArrayList<>();

        for (Task t : this.taskList) {
            if (t instanceof Deadline) {
                deadlines.add((Deadline) t);
            } else {
                remainingTasks.add(t);
            }
        }
        Collections.sort(deadlines);

        TaskList combinedTasks = new TaskList();
        combinedTasks.taskList.addAll(deadlines);
        combinedTasks.taskList.addAll(remainingTasks);

        TaskPrinter taskPrinter = new TaskPrinter(combinedTasks);
        return taskPrinter.printSortedTasks();
    }

}
