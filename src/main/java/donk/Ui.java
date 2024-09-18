package donk;

import donk.task.Task;
import donk.task.TaskList;
import donk.task.TaskType;

/**
 * Handles all user interactions
 */
public class Ui {

    public Ui() {

    }

    /**
     * Displays an error message indicating that no save file was found
     * and a new task list will be started.
     */
    public void showLoadingError() {
        System.out.println("No save file found, starting new task list");
    }

    /**
     * Greets the user with a welcome message when the application starts.
     */
    public void greet() {
        String greetMsg = " ____________________________________________________________\n"
                + " Hello! I'm donk.Donk, the super intelligent chatbot\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println(greetMsg);
    }

    /**
     * Bids farewell to the user when the application is closed.
     */
    public String bye() {
        String byeMsg = "Bye bro, catch 'ya later\n"
                + "____________________________________________________________\n";
        return byeMsg;
    }

    /**
     * Prints error message and indicates correct input format
     * @param taskType
     */
    public String invalidFormat(TaskType taskType) {
        if (taskType == TaskType.DEADLINE) {
            System.out.println("invalid format, require /by <date-time>");
            return "invalid format, require /by <date-time>";
        } else if (taskType == TaskType.EVENT) {
            return "Invalid Format man. You need a /start and a /end";
        } else {
            return "Bruh todo task cannot be empty.\nExample:\n    todo homework";
        }
    }

    /**
     * Displays a message indicating that the specified task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void markedDone(Task task) {
        System.out.println("    Yo I've marked this thingy as done");
        System.out.println("    " + task.toString());

    }

    /**
     * Displays a message indicating that the specified task has been unmarked as not done.
     *
     * @param task The task that has been unmarked.
     */
    public void unmarkedDone(Task task) {
        System.out.println("    Aights now it's unmarked again");
        System.out.println("    " + task.toString());

    }

    /**
     * Displays a message indicating that the specified task has been deleted
     * and shows the updated number of remaining tasks.
     *
     * @param task The task that has been deleted.
     * @param size The number of remaining tasks after deletion.
     */
    public void delete(Task task, int size) {
        System.out.println("    Alright bro I deleted that for you");
        System.out.println("    deleted: " + task.toString());
        System.out.println("    You now have " + size + " tasks");
    }

    /**
     * Show list of tasks, if empty, show message
     * @param tasks TaskList to display
     */
    public String listTasks(TaskList tasks) {
        assert tasks != null;
        if (tasks.size() == 0) {
            return "You've got not tasks yet bro. Add todo, deadline, or event tasks.";
        }

        String tasksString = "";

        for (int i = 1; i <= tasks.size(); i++) {
            tasksString += "\n" + i + ": " + tasks.getTask(i - 1).toString();
        }

        return tasksString;
    }

    /**
     * Returns message to notify user that task has been added
     * @param t
     * @param tasks
     * @return message to show to user
     */
    public String notifyAddedTask(Task t, TaskList tasks) {
        return "Got it. I've added this task:"
                + "\n" + t.toString()
                + "You now have " + tasks.size() + " tasks";
    }


}
