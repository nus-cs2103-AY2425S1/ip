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
    public void printList(TaskList list) {
        printLine();
        System.out.println(list.printString());
        printLine();
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
    public void goodbyeMessage() {
        printLine();
        System.out.println("    See you next time.");
        printLine();
    }

    /**
     * Prints an error message when a command that is not allowed is given.
     */
    public void nonsenseErrorMessage() {
        printLine();
        System.out.println("    Could you, like, write a sensible command please? \n");
        printLine();
    }

    /**
     * Prints a welcome message for users. Has a pretty ASCII logo.
     */
    public void welcomeMessage() {
        String logo = "___________.__            __\n"
                + "\\_   _____/|  |   _______/  |_  ___________\n"
                + " |    __)_ |  |  /  ___/\\   __\\/ __ \\_  __ \\\n"
                + " |        \\|  |__\\___ \\  |  | \\  ___/|  | \\/\n"
                + "/_______  /|____/____  > |__|  \\___  >__|\n"
                + "        \\/           \\/            \\/";
        System.out.println(logo);
        printLine();
        System.out.println("    Hello, \"greetings\" from your friendly neighbourhood chatbot Elster..");
        System.out.println("    How can I help you today :|");
        printLine();
    }

    /**
     * Prints a message when the user does a task.
     *
     * @param task Task done, details needed for the function to display details.
     */
    public void taskDoneMessage(Task task) {
        printLine();
        System.out.println("    Yes boss, marked the task as done.");
        System.out.println("      " + task.toString());
        printLine();
    }

    /**
     * Prints a message when the user undoes a task.
     *
     * @param task Task undone, details needed for the function to display details.
     */
    public void taskUndoneMessage(Task task) {
        printLine();
        System.out.println("    Interesting choice, I've marked the task as not done.");
        System.out.println("      " + task.toString());
        printLine();
    }

    /**
     * Prints a message for when a task is found by its description.
     *
     * @param taskList List of tasks which match the search query.
     */
    public void findByDescriptionMessage(List<Task> taskList) {
        printLine();
        System.out.println("    Elster has trudged through the archives for your results:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("    " + (i + 1) + "." + taskList.get(i));
        }
        printLine();
    }

    /**
     * Prints a message when the user successfully deletes a task.
     */
    public void deleteTaskMessage(TaskList taskList, Task task) {
        printLine();
        System.out.println("    Your bidding has been done, removed:");
        System.out.println("      " + task.toString());

        if (taskList.getSize() == 1) {
            System.out.println("    thou now hath " + taskList.getSize() + " task to complete");
        } else if (taskList.isEmpty()) {
            System.out.println("    thou hath no tasks to be completed");
        } else {
            System.out.println("    thou now hath " + taskList.getSize() + " tasks to complete");
        }
        printLine();
    }

    /**
     * Prints a message when the user successfully adds a task.
     */
    public void addTaskMessage(TaskList taskList, Task task) {
        printLine();
        System.out.println("    The task hath been added");
        System.out.println("      " + task);

        if (taskList.getSize() == 1) {
            System.out.println("    thou now hath " + taskList.getSize() + " task to complete");
        } else {
            System.out.println("    thou now hath " + taskList.getSize() + " tasks to complete");
        }
        printLine();
        System.out.println();
    }

    /**
     * Prints the error message of the Elseption
     * Method inspired by @prave1n and how his iP handled errors
     *
     * @param message Error message to be printed.
     */
    public void printErrorMessage(String message) {
        printLine();
        System.out.println("    " + message);
        printLine();
    }
}
