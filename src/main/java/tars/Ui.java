package tars;

/**
 * Represents a User interface which handles the entry and exit messages for the User
 *
 * @author csk
 */
public class Ui {

    public Ui() {
    }

    /**
     * Provides welcome message to be displayed to user when first starting Tars chatbot or re-starting the bot
     */
    public String welcome() {
        return "Hello! I'm Tars! Your one-stop Task Manager.\n" + "\n"
                + "Enter 'help' to get more instructions on features\n"
                + "Enter 'bye' to exit application!";
    }

    /**
     * States features of the Tars chatbot that users can use while running the application
     */
    public String help() {
        return "List of commands that you can do while using Tars!\n"
                + "1. add Todo, eg: t read book\n"
                + "2. add deadline, eg: d read book /by 2019-10-15 18:30\n"
                + "3. add event, eg: e read book /from 2019-10-15 18:30 /to 2019-10-16 19:30\n"
                + "4. Mark, unmark or delete tasks by using m/um/delete and the index of the task\n"
                + "5. Find tasks in the list using task description words\n eg: find read book or find book\n"
                + "6. State list to see your list of tasks";
    }

    /**
     * Returns output message for successful addition of Task after handled by taskList
     *
     * @param task
     * @param size
     */
    public String printTask(Task task, int size) {
        return " Got it. I've added this task:\n" + task + "\n" + " Now you have "
                + size + " tasks in the list";
    }

    /**
     * Provides exit message to be displayed to user when exiting Tars
     * This command is executed after the "bye" input, which is handled in Tars application
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }
}
