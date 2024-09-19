package winner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList object and initialises an ArrayList to store tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the ArrayList list of tasks.
     *
     * @return ArrayList list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Number of tasks.
     */
    public int getNoOfTasks() {
        return tasks.size();
    }

    /**
     * Adds a Task into the task list and returns a String confirming that the task has been added.
     *
     * @return A String indicating that the task has been added.
     */
    public String addTaskToString() {
        return """
                I have added this task into the list for you and
                that brings your total number of tasks to""" + " " + getNoOfTasks();
    }

    /**
     * Adds a Todo task into the task list and returns a String confirming that the task has been added.
     *
     * @param description Description of the ToDo task.
     * @return A String indicating that the ToDo task has been added.
     */
    public String addToDo(String description) {
        ToDo newToDo = new ToDo(description);
        tasks.add(newToDo);
        return addTaskToString() + "\n"
                + " ".repeat(5) + "[T] [ ] " + description + "\n"
                + "\n"
                + "You can use the command \"list\" to view your list of tasks :D";
    }

    /**
     * Adds a Deadline task into the task list and returns a String confirming that the task has been added.
     *
     * @param description Description of the Deadline task.
     * @param deadline    Deadline date or both date and time.
     * @return A String indicating that the Deadline task has been added.
     * @throws WinnerException If the date/time format is incorrect.
     */
    public String addDeadline(String description, String deadline) throws WinnerException {
        Deadline newDeadline;
        try {
            if (deadline.matches(".*\\bat\\b.*")) { //Parser
                String dateTime = deadline.trim(); //TaskList
                DateTimeFormatter formattedDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HHmm");
                LocalDateTime byDateTime = LocalDateTime.parse(dateTime, formattedDateTime);
                newDeadline = new Deadline(description, byDateTime);
            } else {
                String date = deadline.trim(); //TaskList
                DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate byDate = LocalDate.parse(date, formattedDate);
                newDeadline = new Deadline(description, byDate);
            }
            tasks.add(newDeadline);
            return addTaskToString() + "\n"
                    + " ".repeat(5) + "[D] [ ] " + description + " (by: " + newDeadline.formattedDeadline() + ")"
                    + "\n" + "\n"
                    + "You can use the command \"list\" to view your list of tasks :D";
        } catch (DateTimeParseException e) {
            throw new WinnerException("""
                    Expected format for adding deadline task:
                    deadline (task) by (date) at (time)
                         > date - dd/mm/yyyy
                         > time - 24 hour format""");
        }
    }

    /**
     * Adds an Event task into the task list and returns a String confirming that the task has been added.
     * @param description Description of the Event task.
     * @param start Start of Event.
     * @param end End of Event.
     * @return A String indicating that the Event task has been added.
     */
    public String addEvent(String description, String start, String end) {
        Event newEvent = new Event(description, start, end);
        tasks.add(newEvent);
        return addTaskToString() + "\n"
                + " ".repeat(5) + "[E] [ ] " + description + " (" + start + " - " + end + ")" + "\n"
                + "\n"
                + "You can use the command \"list\" to view your list of tasks :D";
    }

    /**
     * Returns a String listing all tasks in the task list.
     *
     * @return A String representation of all tasks in the list.
     */
    public String listTasks() {
        int counter = 1;
        StringBuilder list = new StringBuilder("\nHere are the tasks you have in your list:\n");
        for (Task i : tasks) {
            list.append(counter).append(". ").append(i.taskToString()).append("\n");
            counter++;
        }
        return list.toString();
    }

    /**
     * Marks a task in the task list as done and returns a String confirming the task has been marked as done.
     *
     * @param taskNumber Task number to be marked as done.
     * @return A String indicating that the task has been marked as done.
     * @throws WinnerException If the task has been marked as done.
     */
    public String markTaskAsDone(int taskNumber) throws WinnerException {
        Task task = tasks.get(taskNumber - 1);
        if (task.isDone) {
            throw new WinnerException("You have already marked this task as DONE :D");
        }
        return task.markDone();
    }

    /**
     * Marks a task in the task list as undone and returns a String confirming the task has been marked as undone.
     *
     * @param taskNumber Task number to be marked as done.
     * @return A String indicating that the task has been marked as undone.
     * @throws WinnerException If the task is already not marked as done.
     */
    public String unmarkDoneTask(int taskNumber) throws WinnerException {
        Task task = tasks.get(taskNumber - 1);
        if (!task.isDone) {
            throw new WinnerException("You cannot unmark this task as it has not been marked as done :(");
        }
        return task.unmarkDone();
    }

    /**
     * Deletes a task in the task list and returns a String confirming the task has been deleted.
     *
     * @param taskNumber Task number to be deleted from the task list.
     * @return A String indicating that the task has been deleted.
     */
    public String deleteTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        return task.deleteTask();
    }

    /**
     * Finds tasks containing the keyword provided in the task list and returns a String listing the tasks found.
     *
     * @param keyword Keyword to search for.
     * @return A String representation of all tasks containing the keyword in the list.
     */
    public String findTasksWithKeyword(String keyword) {
        int counter = 1;
        StringBuilder list = new StringBuilder("\nHere are the tasks you are searching for: \n");
        for (Task i : tasks) {
            if (i.description.contains(keyword)) {
                list.append(counter).append(". ").append(i.taskToString()).append("\n");
                counter++;
            }
        }
        return list.toString();
    }

}
