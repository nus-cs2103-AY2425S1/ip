package processes;


import java.util.ArrayList;

import tasks.Task;

/**
 * The task that deals with user inputs.
 * Formats the input for use in other classes, such as the TaskList class.
 */
public class Ui {

    /**
     * Called when the programme just starts.
     * Prints the welcome message onto the terminal to greet the user.
     *
     * @param name Name of the chatbot
     * @return The welcome message to print in the GUI
     */
    public String showWelcomeMessage(String name) {
        return "Hello! I'm " + name + "\n";
    }

    /**
     * Called when the programme is terminated.
     * Returns the goodbye message onto the terminal for the user.
     *
     * @return The goodbye message
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }


    /**
     * Returns the list of tasks on the terminal for the user.
     *
     * @param taskList The current list of tasks
     * @return The string containing the list of tasks
     */
    public String showTaskList(ArrayList<Task> taskList) {
        if (!taskList.isEmpty()) {
            StringBuilder res = new StringBuilder("Your current tasks are: \n");
            for (int i = 0; i < taskList.size(); i++) {
                res.append((i + 1)).append(".").append(taskList.get(i).toString()).append("\n");
            }
            return res.toString();
        } else {
            return "You currently have no tasks!";
        }

    }

    /**
     * Takes in a message and prints it onto the terminal, useful for showing exception messages.
     *
     * @param message The message that should be printed onto the terminal
     * @return The message to print in the GUI
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Takes in the task that was marked and returns the feedback.
     * Informs the user that the task is successfully marked
     *
     * @param markedTask The task that was just marked.
     * @return The success message
     */
    public String showMarked(Task markedTask) {
        assert markedTask != null : "Marked task given is null!";
        return "task\n  " + markedTask + "\nis marked!";
    }

    /**
     * Takes in the task that was unmarked and returns the feedback.
     * Informs the user that the task is successfully unmarked
     *
     * @param unmarkedTask The task that was just unmarked.
     * @return The success message
     */
    public String showUnmarked(Task unmarkedTask) {
        assert unmarkedTask != null : "Unmarked task given is null!";
        return "task\n  " + unmarkedTask + "\nis unMarked!";
    }

    /**
     * Takes in the task that was just added and the current size of taskList.
     * Informs the user that the task is successfully added.
     *
     * @param added The task that was just added.
     * @param taskListSize The current size of the taskList
     * @return The success message
     */
    public String addedTask(Task added, int taskListSize) {
        assert added != null : "Added task given is null!";
        return "I have added the task:\n" + added + "\nYou now have " + taskListSize + " task(s)";
    }

    /**
     * Takes in the task that was just deleted and the current size of taskList.
     * Informs the user that the task was successfully deleted.
     *
     * @param deleted The task that was just deleted.
     * @param taskListSize The current size of the taskList
     * @return The success message
     */
    public String deletedTask(Task deleted, int taskListSize) {
        assert deleted != null : "Deleted task given is null!";
        return "The task\n " + deleted + "\nhas been removed!\nYou now have "
                + taskListSize + " tasks left.";
    }

    /**
     * Takes in the list of tasks that matched the user's search.
     * Returns the list of tasks to the user.
     *
     * @param matchList The list of tasks that matched the user's search
     * @return The success message
     */

        public String showMatchedTasks(ArrayList<Task> matchList, String prompt) {
            assert matchList != null : "List of matched tasks given is null!";
            if (matchList.isEmpty()) {
                return "There are no tasks in your list that match " + prompt;
            } else {
                StringBuilder res = new StringBuilder("Here are the matching task(s) in your list: \n");
                for (int i = 0; i < matchList.size(); i++) {
                    res.append((i + 1)).append(". ").append(matchList.get(i).toString());
                }
                return res.toString();
            }
        }
    }


