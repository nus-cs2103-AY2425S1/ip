package elsa.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import elsa.ElsaException;

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
        // Assert that the tasks reference is not null
        assert tasks != null : "Task list cannot be null";
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
     * Adds a Todo task to the list.
     *
     * @param description the description of the elsa.task.Todo task
     * @return A response string that confirms the successful addition of a todo task.
     */
    public String addTodo(String description) throws ElsaException {
        // If the task to add matches a Todo task in the task list, then throw an error
        if (isDuplicateTask(Todo.class, description)) {
            throw new ElsaException("Oh! This Todo task is already present in our list. If you would like to amend it, "
                    + "please delete it and create a new Todo task.");
        }

        // Assert that the description is not empty
        assert !description.isEmpty() : "Task description cannot be empty";
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
    public String addDeadline(String description, String dueBy) throws ElsaException {
        try {
            LocalDate.parse(dueBy);

            // If the task to add matches a Deadline task in the task list, then throw an error
            if (isDuplicateTask(Deadline.class, description)) {
                throw new ElsaException("Oh! This Deadline task is already present in our list. If you would like to "
                        + "amend it, please delete it and create a new Deadline task.");
            }

            // Assert that the description and dueBy are not empty
            assert !description.isEmpty() : "Task description cannot be empty";
            assert !dueBy.isEmpty() : "Due date cannot be empty";
            Deadline newDeadline = new Deadline(description, false, dueBy);
            tasks.add(newDeadline);
            return "Alright, I've added this task:\n  " + tasks.get(tasks.size() - 1) + "\nWe have "
                    + tasks.size() + " tasks in our list now.";
        } catch (DateTimeParseException e) {
            // Throw an exception if parsing fails
            throw new ElsaException("Oops! It appears that this date/time does not exist in the calendar or the clock. "
                    + "Please use a valid format (e.g., YYYY-MM-DD HH:MM).");
        }
    }

    /**
     * Adds an elsa.task.Event task to the list.
     *
     * @param description the description of the elsa.task.Event task
     * @param start the start date and time of the elsa.task.Event task
     * @param end the end date and time of the elsa.task.Event task
     * @return A response string that confirms the successful addition of an event task.
     */
    public String addEvent(String description, String start, String end) throws ElsaException {
        // If the task to add matches an Event task in the task list, then throw an error
        if (isDuplicateTask(Event.class, description)) {
            throw new ElsaException("Oh! This Event task is already present in our list. If you would like to "
                    + "amend it, please delete it and create a new Event task.");
        }

        // Assert that the description, start and end are not empty
        assert !description.isEmpty() : "Task description cannot be empty";
        assert !start.isEmpty() : "Start date/time cannot be empty";
        assert !end.isEmpty() : "End date/time cannot be empty";
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
    public String deleteTask(int index) throws ElsaException {
        if (index < 0 || index >= tasks.size()) {
            if (tasks.isEmpty()) {
                throw new ElsaException("Oops, it appears that the task number entered isn't in our list. The list is "
                        + "currently empty.");
            } else if (tasks.size() == 1) {
                throw new ElsaException("Oops, it appears that the task number entered isn't in our list. There's only "
                        + "one task currently in our list.");
            } else {
                throw new ElsaException("Oops, it appears that the task number entered isn't in our list. Please enter "
                        + "a number between 1 and " + tasks.size() + ".");
            }
        }

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
    public String markTask(int index) throws ElsaException {
        if (index < 0 || index >= tasks.size()) {
            if (tasks.isEmpty()) {
                throw new ElsaException("Oops, it appears that the task number entered isn't in our list. The list is "
                        + "currently empty.");
            } else if (tasks.size() == 1) {
                throw new ElsaException("Oops, it appears that the task number entered isn't in our list. There's only "
                        + "one task currently in our list.");
            } else {
                throw new ElsaException("Oops, it appears that the task number entered isn't in our list. Please enter "
                        + "a number between 1 and " + tasks.size() + ".");
            }
        }
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
    public String unmarkTask(int index) throws ElsaException {
        if (index < 0 || index >= tasks.size()) {
            if (tasks.isEmpty()) {
                throw new ElsaException("Oops, it appears that the task number entered isn't in our list. The list is "
                        + "currently empty.");
            } else if (tasks.size() == 1) {
                throw new ElsaException("Oops, it appears that the task number entered isn't in our list. There's only "
                        + "one task currently in our list.");
            } else {
                throw new ElsaException("Oops, it appears that the task number entered isn't in our list. Please enter "
                        + "a number between 1 and " + tasks.size() + ".");
            }
        }
        tasks.get(index).notDone();
        // Informs the user that the task has been marked as not done
        return "Alright, I've unchecked this task:\n  " + tasks.get(index).toString();
    }

    /**
     * Checks whether the new task that the user is trying to create already exists in the task list.
     *
     * @param taskClass the class of the new task to be created.
     * @param description the description of the new task to be created.
     * @return A boolean value for whether the task type and description currently exist in the task list.
     */
    private boolean isDuplicateTask(Class<? extends Task> taskClass, String description) {
        for (Task task : tasks) {
            if (taskClass.isInstance(task) && task.getDescription().equals(description)) {
                return true;
            }
        }
        return false;
    }
}
