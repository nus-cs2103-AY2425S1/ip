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
     * Prints message to be displayed to user when first starting Tars chatbot or re-starting the Tars chatbot
     */
    public String welcome() {
        return "Hello! I'm Tars! Your one-stop Task Manager.\n" + "\n"
                + "Simply use t(for ToDos), d(for Deadline) and e(for event)" + "\n"
                + "eg: t read book --> todo read book\n" + "\n"
                + "You can mark, unmark or delete tasks too, by using m/um/delete as command\n" + "\n"
                + "State list to see your list of tasks";
    }

    public String help() {
        return "This is the list of commands that you can do while using Tars!\n"
                + "Simply use t(for ToDos), d(for Deadline) and e(for event)" + "\n"
                + "eg: t read book --> todo read book\n" + "\n"
                + "You can mark, unmark or delete tasks too, by using m/um/delete as command\n" + "\n"
                + "State list to see your list of tasks";
    }

    /**
     * Prints message to be displayed to user when exiting Tars chatbot
     * This command is executed after the "bye" input, which is handled in Tars application
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }
}
