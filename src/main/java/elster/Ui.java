package elster;

import java.util.List;

import elster.tasks.Task;


/**
 * Ui class that handles sending messages to interact with the user.
 * Also handles sending error messages.
 */
public class Ui {
    /**
     * Prints tasks from a list.
     *
     * @param list Task list from which to print tasks.
     */
    public String printList(TaskList list) {
        return list.printString();
    }

    /**
     * Prints a line that looks pretty.
     */
    protected static void printLine() {
        System.out.println("    ____________________________________________________________________________");
    }

    /**
     * Prints a message to bid the user goodbye.
     */
    public String goodbyeMessage() {
        return "See you next time.";
    }

    /**
     * Prints an error message when a command that is not allowed is given.
     */
    public String nonsenseErrorMessage() {
        return "Could you, like, write a sensible command please? \n";
    }

    /**
     * Prints a welcome message for users. Has a pretty ASCII logo.
     */
    public String welcomeMessage() {
        String resultStr = "___________.__            __\n"
                + "\\_   _____/|  |   _______/  |_  ___________\n"
                + " |    __)_ |  |  /  ___/\\   __\\/ __ \\_  __ \\\n"
                + " |        \\|  |__\\___ \\  |  | \\  ___/|  | \\/\n"
                + "/_______  /|____/____  > |__|  \\___  >__|\n"
                + "        \\/           \\/            \\/\n";
        resultStr += "Hello, \"greetings\" from your friendly neighbourhood chatbot Elster..\n";
        resultStr += "How can I help you today :|";
        return resultStr;
    }

    /**
     * Prints a message when the user does a task.
     *
     * @param task Task done, details needed for the function to display details.
     */
    public String taskDoneMessage(Task task) {
        String resultStr = "";

        resultStr += "Yes boss, marked the task as done.\n";
        resultStr += "  " + task.toString();

        return resultStr;
    }

    /**
     * Prints a message when the user undoes a task.
     *
     * @param task Task undone, details needed for the function to display details.
     */
    public String taskUndoneMessage(Task task) {
        String resultStr = "";
        resultStr += "Interesting choice, I've marked the task as not done.\n";
        resultStr += "  " + task.toString();
        return resultStr;
    }

    /**
     * Prints a message for when a task is found by its description.
     *
     * @param taskList List of tasks which match the search query.
     */
    public String findByDescriptionMessage(List<Task> taskList) {
        StringBuilder resultStr = new StringBuilder();

        resultStr.append("Elster has trudged through the archives for your results:\n");
        for (int i = 0; i < taskList.size(); i++) {
            resultStr.append("  ").append((i + 1)).append(".").append(taskList.get(i)).append("\n");
        }
        return resultStr.toString();
    }

    /**
     * Prints a message when the user successfully deletes a task.
     */
    public String deleteTaskMessage(TaskList taskList, Task task) {
        String resultStr = "";
        resultStr += "Your bidding has been done, removed:\n";
        resultStr += "  " + task.toString() + "\n";

        if (taskList.getSize() == 1) {
            resultStr += "thou now hath " + taskList.getSize() + " task to complete\n";
        } else if (taskList.isEmpty()) {
            resultStr += "thou hath no tasks to be completed\n";
        } else {
            assert taskList.getSize() > 1 : "Bug when deleting tasks";
            resultStr += "thou now hath " + taskList.getSize() + " tasks to complete\n";
        }
        return resultStr;
    }

    /**
     * Prints a message when the user successfully adds a task.
     */
    public String addTaskMessage(TaskList taskList, Task task) {
        String resultStr = "";
        resultStr += "The task hath been added\n";
        resultStr += "  " + task + "\n";

        if (taskList.getSize() == 1) {
            resultStr += "thou now hath " + taskList.getSize() + " task to complete\n";
        } else {
            assert taskList.getSize() > 1 : "Bug when adding tasks";
            resultStr += "thou now hath " + taskList.getSize() + " tasks to complete\n";
        }

        return resultStr;
    }

    /**
     * Prints the error message of the Elseption
     * Method inspired by @prave1n and how his iP handled errors
     *
     * @param message Error message to be printed.
     */
    public String printErrorMessage(String message) {
        return message;
    }
}
