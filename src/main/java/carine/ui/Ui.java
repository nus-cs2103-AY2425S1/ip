package carine.ui;

/**
 * This class represents a reply from the chatbot.
 * It provides methods to print a reply, such as greeting.
 */
public class Ui {
    /**
     * Prints greeting message.
     */
    public static String printGreeting() {
        StringBuilder stringBuilder = new StringBuilder("Hello! I'm Carine, your personal task planning assistant! \n")
                .append("What can I do for you?");
        return stringBuilder.toString();
    }

    /**
     * Prints all available commands for user's reference.
     */
    public static String printCommand() {
        StringBuilder stringBuilder = new StringBuilder("List of available commands: \n")
                .append("   1. Create a new task without deadline: todo [name] \n")
                .append("   2. Create a new task with deadline: deadline [name] /by [time] \n")
                .append("   3. Create a new task with duration: event [name] /from [time] /to [time] \n")
                .append("       NOTE: time in dd/MM/yyyy HH:mm or dd/MM/yyyy \n")
                .append("   4. Mark a task as complete: mark [index of task] \n")
                .append("   5. Mark a task as incomplete: unmark [index of task] \n")
                .append("   6. Delete a task from the list: delete [index of task] \n")
                .append("   7. Search for a task: find [name of task] \n")
                .append("   8. Display all tasks: list");
        return stringBuilder.toString();
    }

    /**
     * Prints goodbye message.
     */
    public static String printGoodbye() {
        return " Bye. Hope to see you again soon!";

    }

}
