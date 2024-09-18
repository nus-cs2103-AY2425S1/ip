package weeny.ui;

import java.util.List;

import weeny.task.Task;


/**
 * Handles user interactions by displaying messages and reading input.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public String showWelcomeMessage() {
        return String.format("Oink! I'm Weeny\nWhat can I do for you?\n");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to display.
     */
    public String showTaskList(List<Task> tasks) {
        assert tasks != null : "Task list should not be null";
        String taskList = "Here you go! All the tasks you have are here:\n";
        for (int i = 0; i < tasks.size(); i++) {
            taskList = taskList + String.format((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        return String.format(taskList);
    }

    /**
     * Diplays the list of tasks after search
     *
     * @param tasks A list of tasks that contains the keyword
     */
    public String showSearchResult(List<Task> tasks) {
        assert tasks != null : "Search result list should not be null";
        String resultList = "Hmm.. these are the tasks that matches what you want:\n";
        for (int i = 0; i < tasks.size(); i++) {
            resultList = resultList + String.format((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        if (tasks.isEmpty()) {
            return "Sorry I cannot find any tasks with those keywords";
        }
        return String.format(resultList);
    }

    /**
     * Displays the list of tasks on a date
     *
     * @param tasks A list of tasks that is filtered to date and sorted
     * @param date String of the particular date
     */
    public String showScheduleMessage(List<Task> tasks, String date) {
        String scheduleList = "I have gathered all the tasks you have on " + date + ":\n";
        for (int i = 0; i < tasks.size(); i++) {
            scheduleList = scheduleList + String.format((i + 1) + " " + tasks.get(i).toString() + "\n");
        }
        if (tasks.isEmpty()) {
            return "Hooray you have no task on " + date;
        }
        return String.format(scheduleList);
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task The task that was added.
     * @param size The updated number of tasks.
     */
    public String showTaskAddedMessage(Task task, int size) {
        assert task != null : "Task should not be null";
        return String.format("Gotcha, I have added:\n" + task.toString() +
                "\n" +"You have a total of " + size + " tasks in the list.\n");
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The updated number of tasks.
     */
    public String showTaskDeletedMessage(Task task, int size) {
        assert task != null : "Task should not be null";
        return String.format("Spooof! The task magically disappeared:\n" +
                task.toString() + "\n" + "You have a total of " + size + " tasks in the list.\n");
    }

    /**
     * Displays a message indicating a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public String showUnmarkMessage(Task task) {
        assert task != null : "Task should not be null";
        return String.format("You're going back on your words? Tsk I have unmarked:\n" +
                task.toString() + "\n");
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that was marked.
     */
    public String showMarkMessage(Task task) {
        assert task != null : "Task should not be null";
        return String.format("Nice! I've marked this task as done:\n" +
                task.toString() + "\n");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public String showError(String message) {
        return String.format("Error: " + message + "\n");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showGoodbyeMessage() {
        return String.format("I guess it's my meal time! Bye!\n");
    }

}
