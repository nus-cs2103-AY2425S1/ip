package elsa.task;

import java.util.List;

/**
 * Manages the list of tasks in the application.
 * @author Aaron
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an elsa.task.TaskList with the specified list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a elsa.task.Todo task to the list.
     *
     * @param description the description of the elsa.task.Todo task
     * @return A response string that confirms the successful addition of a todo task.
     */
    public String addTodo(String description) {
        Todo newTodo = new Todo(description, false);
        tasks.add(newTodo);
        return "Alright, I've added this task:\n  " + tasks.get(tasks.size() - 1) + "\nWe have "
                + tasks.size() + " tasks in our list now.";
    }

    /**
     * Adds an elsa.task.Deadline task to the list.
     *
     * @param description the description of the elsa.task.Deadline task
     * @param dueBy the due date and time of the elsa.task.Deadline task
     * @return A response string that confirms the successful addition of a deadline task.
     */
    public String addDeadline(String description, String dueBy) {
        Deadline newDeadline = new Deadline(description, false, dueBy);
        tasks.add(newDeadline);
        return "Alright, I've added this task:\n  " + tasks.get(tasks.size() - 1) + "\nWe have "
                + tasks.size() + " tasks in our list now.";
    }

    /**
     * Adds an elsa.task.Event task to the list.
     *
     * @param description the description of the elsa.task.Event task
     * @param start the start date and time of the elsa.task.Event task
     * @param end the end date and time of the elsa.task.Event task
     * @return A response string that confirms the successful addition of an event task.
     */
    public String addEvent(String description, String start, String end) {
        Event newEvent = new Event(description, false, start, end);
        tasks.add(newEvent);
        return "Alright, I've added this task:\n  " + tasks.get(tasks.size() - 1) + "\nWe have "
                + tasks.size() + " tasks in our list now.";
    }

    /**
     * Deletes a task from the list.
     *
     * @param index the index of the task that is to be deleted from the task list
     * @return A response string that confirms the successful deletion of a task.
     */
    public String deleteTask(int index) {
        String message = "Okay, I've removed this task:\n  " + tasks.get(index).toString() + "\nWe have "
                + (tasks.size() - 1) + " tasks in our list now.";

        // Deletes the task from the list
        tasks.remove(index);

        // Informs the user that the task has been deleted
        return message;
    }

    /**
     * Lists all the tasks in the task list.
     *
     * @return A response string that contains the list of tasks.
     */
    public String listTasks() {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            message.append("\n").append((i + 1)).append(".").append(tasks.get(i));
        }
        return message.toString();
    }

    /**
     * Finds tasks in the taskList according to the userInput.
     *
     * @param taskToFind The edited userInput that indicates which task(s) to search for in the taskList.
     * @return A response string that shows any matching tasks.
     */
    public String findTasks(String taskToFind) {
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:");
        int taskNum = 1;
        String lowerCaseTaskToFind = taskToFind.toLowerCase();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(lowerCaseTaskToFind)) {
                message.append("\n").append(taskNum).append(".").append(task);
                taskNum++;
            }
        }

        if (taskNum == 1) {
            return "Sorry, I couldn't find any matching tasks.";
        }
        return message.toString();
    }

    /**
     * Marks a task as done with an 'X'.
     *
     * @param index the index of the task to be marked as done
     * @return A response string that confirms the marking of a task.
     */
    public String markTask(int index) {
        tasks.get(index).done();
        // Informs the user that the task has been marked as done
        return "Great! I've marked it as done:\n  " + tasks.get(index).toString();
    }

    /**
     * Unmarks a task by removing any 'X'.
     *
     * @param index the index of the task to be unmarked
     * @return A response string that confirms that the task has been unmarked.
     */
    public String unmarkTask(int index) {
        tasks.get(index).notDone();
        // Informs the user that the task has been marked as not done
        return "Alright, I've unchecked this task:\n  " + tasks.get(index).toString();
    }
}
