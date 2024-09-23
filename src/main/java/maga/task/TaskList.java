package maga.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import maga.commands.Command;


/**
 * The {@code TaskList} class manages a list of tasks.
 * It allows tasks to be added, deleted, marked as done or undone, and searched.
 * This class is used to manipulate and retrieve tasks for the user.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns a string representation of all tasks in the list, each task is numbered sequentially.
     *
     * @return A formatted string containing all tasks.
     */
    public String listTasks() {
        StringBuilder message = new StringBuilder("Take a look, all the tasks you have here, so many, yuuuuuuge\n");
        for (int i = 0; i < taskList.size(); i++) {
            int temp = i + 1;
            message.append(temp).append(". ").append(taskList.get(i).printTask()).append("\n");
        }

        return message.toString();
    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param id The index of the task to retrieve (0-based index).
     * @return The {@code Task} at the specified index.
     */
    public Task getTask(int id) {
        return taskList.get(id);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getTaskCount() {
        return taskList.size();
    }

    /**
     * Deletes a task by its number and returns a message indicating the task was deleted.
     *
     * @param taskNumber The number of the task to delete (0-based index).
     * @return A message indicating the task has been deleted or an error message if invalid.
     */

    public String deleteTask(int taskNumber) {
        try {
            assert(taskNumber >= 0);
            Task tempTask = taskList.get(taskNumber);
            taskList.remove(taskNumber);
            return "I've deleted this task:\n" + tempTask.getTaskType() + tempTask.getStatusIcon()
                    + tempTask.getDescription() + "\nYou have " + taskList.size() + " task(s) now!\n";
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return "Invalid task specified!";
        }
    }

    /**
     * Marks a task as done by its number and returns a message indicating the task was marked as done.
     *
     * @param taskNumber The number of the task to mark as done (0-based index).
     * @return A message indicating the task has been marked as done or an error message if invalid.
     */
    public String markTask(int taskNumber) {
        try {
            assert(taskNumber >= 0);
            Task temp = taskList.get(taskNumber);
            temp.markAsDone();
            return "Ya boi Donald took the LIBERTY to mark this done:\n"
                    + temp.getTaskType() + temp.getStatusIcon() + temp.getDescription() + "\n";
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return "You're trying to mark a task that DOESN'T EXIST, like bad people on JAN 6. "
                    + "Some of the kindest and most lovely souls I've met";
        }
    }

    /**
     * Unmarks a task by its number and returns a message indicating the task was unmarked.
     *
     * @param taskNumber The number of the task to unmark (0-based index).
     * @return A message indicating the task has been unmarked or an error message if invalid.
     */
    public String unmarkTask(int taskNumber) {
        try {
            assert(taskNumber >= 0);
            Task temp = taskList.get(taskNumber);;
            temp.markAsUndone();
            return "Here's the task promised but not completed, just like the DEMS\n"
                    + temp.getStatusIcon() + temp.getDescription() + "\n";
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return "Stop trying to unmark tasks like ILLEGAL ALIENS after"
                    + " I'm president: NOT HERE!";
        }
    }

    /**
     * Finds tasks that match the given search content and returns a list of matching tasks.
     *
     * @param content The string to search for within task descriptions.
     * @return A formatted string of tasks that contain the search term.
     */
    public String findTask(String content) {
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list: \n");
        int count = 1;
        for (Task task : taskList) {
            if (task.getDescription().contains(content)) {
                message.append(count + ". ").append(task.toString()).append("\n");
            }
            count++;
        }

        return message.toString();
    }

    /**
     * Adds a new task based on the {@code Command} and returns a message indicating the task was added.
     *
     * @param command The {@code Command} containing the task details.
     * @return A message indicating the task has been added or an error message if the task list is full.
     */
    public String addTask(Command<LocalDate[]> command) {
        Task task = null;
        if (Objects.equals(command.getCommandType(), "todo")) {
            task = new TodoTask(false, command.getDescription());
        } else if (Objects.equals(command.getCommandType(), "event")) {
            task = new EventTask(false, command.getCommandType(), command.getContent()[0]);
        } else if (Objects.equals(command.getCommandType(), "deadline")) {
            task = new DeadlineTask(false,
                    command.getCommandType(), command.getContent()[0], command.getContent()[1]);
        }

        try {
            assert(task != null);
            taskList.add(task);
            return "Another task for the American people added:\n" + task.getTaskType()
                    + task.getStatusIcon() + task.getDescription() + "\nYou have " + taskList.size() + " task(s) now!";
        } catch (IndexOutOfBoundsException e) {
            return "Tasklist is full!";
        }
    }


    /**
     * Adds an existing task to the list and returns a message indicating the task was added.
     *
     * @param task The {@code Task} to add to the list.
     * @return A message indicating the task has been added or an error message if the task list is full.
     */
    public String addTask(Task task) {
        try {
            taskList.add(task);
            return "Another maga.task for the American people added:\n" + task.getTaskType()
                    + task.getStatusIcon() + task.getDescription() + "\nYou have "
                    + taskList.size() + " task(s) now!\n";
        } catch (IndexOutOfBoundsException e) {
            return "Tasklist is full!";
        }
    }
}
