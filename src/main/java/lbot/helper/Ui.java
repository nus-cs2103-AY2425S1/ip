package lbot.helper;

import lbot.task.Task;

/**
 * This class handles all the printed message for user.
 */
public class Ui {
    //=================================================================================================================
    // JavaFX related methods
    //=================================================================================================================

    /**
     * Generic say method that takes in any String.
     *
     * @param output to be printed.
     * @return String to be printed.
     */
    public String say(String output) {
        return output;
    }

    /**
     * Returns greeting message.
     *
     * @return formatted greeting String.
     */
    public String sayHello() {
        return "Henlo, this is LBot :)\nHow can I help?";
    }

    /**
     * Returns farewell message.
     *
     * @return formatted farewell String.
     */
    public String sayBye() {
        return "Bye-bi!!";
    }

    /**
     * Formats and returns newly added {@link Task}.
     *
     * @param task that was created.
     * @return formatted String containing task that was added.
     */
    public String sayAdded(Task task) {
        return "Added: " + task;
    }

    /**
     * Formats and returns {@link Task} that was just marked.
     *
     * @param task that was marked.
     * @return formatted String containing task that was marked.
     */
    public String sayMarked(Task task) {
        if (task.getStatus()) {
            return "Marked: " + task;
        } else {
            return "Unmarked: " + task;
        }
    }

    /**
     * Formats and returns {@link Task} that was just deleted.
     *
     * @param task that was deleted.
     * @return formatted String containing task that was deleted.
     */
    public String sayDeleted(Task task) {
        return "Deleted: " + task;
    }

    /**
     * Formats and returns {@link TaskList}, containing all the tasks currently in the system.
     *
     * @param taskList that contains all {@link Task}s.
     * @return formatted String containing all tasks.
     */
    public String sayList(TaskList taskList) {
        return "Here are your tasks:\n" + taskList;
    }

    /**
     * Formatted Exception message.
     *
     * @param output The exception message to be printed.
     * @return formatted String containing exception message.
     */
    public String sayException(String output) {
        return "Oopsie:\n" + output;
    }
}
