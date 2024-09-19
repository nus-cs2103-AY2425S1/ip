package tars;
/**
 * Class represents a User interface which handles the entry and exit messages for the User
 *
 * @author csk
 * @version 1
 */
public class Ui {

    public Ui() {
    }

    /**
     * Provides welcome message to be displayed to user when first starting Tars chatbot or re-starting the bot
     *
     * @return String (Welcome message)
     */
    public String welcome() {
        return "Hello! I'm Tars! Your one-stop Task Manager.\n" + "\n"
                + "Enter 'help' to get more instructions on features\n"
                + "Enter 'bye' to exit application!";
    }

    /**
     * States features of the Tars chatbot that users can use while running the application
     *
     * @return String (help message)
     */
    public String help() {
        return "List of commands that you can do while using Tars!\n"
                + "1. Simply use t(for ToDos), d(for Deadline) and e(for event)\n"
                + "eg: t read book --> todo read book\n"
                + "2. Mark, unmark or delete tasks by using m/um/delete and the index of the task\n"
                + "3. State list to see your list of tasks";
    }

    /**
     * Provides exit message to be displayed to user when exiting Tars
     * This command is executed after the "bye" input, which is handled in Tars application
     *
     * @return String (bye message)
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }
}
