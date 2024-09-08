package Joseph;

import java.util.ArrayList;

import Joseph.Tasks.Task;

/**
 * Handles all interactions with the user.
 */
public class UI {

    /**
     * Prints the welcome message when the chatbot is started.
     */
    public String getWelcomeMessage() {
        return "----------------------------------\n" +
                "Hello, I'm Joseph!\n" +
                "How can I help you today? (type help for all commands)\n" +
                "----------------------------------\n";
    }

    /**
     * Prints the exit message when the chatbot is closed i.e. the "bye" command.
     */
    public String getExitMessage() {
        return "----------------------------------\n" +
                "Bye! Have a nice day :)\n" +
                "----------------------------------\n";
    }

    /**
     * Prints the current list of tasks.
     *
     * @param list The list of tasks within ./data/joseph.txt.
     */
    public String getListMessage(ArrayList<Task> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------\n");
        for (int i = 0; i < list.size(); i++) {
            String done = "[" + list.get(i).getDone() + "] ";
            sb.append(i + 1).append(". ").append(done).append(
                    list.get(i).getDetails()).append("\n");
        }
        sb.append("----------------------------------\n");
        return sb.toString();
    }

    /**
     * Prints a help message showing all available commands. Command is "help".
     */
    public String getHelpMessage() {
        return "----------------------------------\n" +
                "help: prints all available commands\n" +
                "list: prints the current list\n" +
                "mark X, where X is any number: \n" +
                "marks task X on the list as completed\n" +
                "mark X, where X is any number: \n" +
                "marks task X on the list as completed\n" +
                "unmark X, where X is any number: \n" +
                "unmarks task X on the list as uncompleted.\n" +
                "The below todo, deadline and event commands \n" +
                "accepts desc as any string\n" +
                "The below deadline and event commands \n" +
                "accepts a due or start and end \n" +
                "in the following format: DD/MM/YYYY HHmm\n" +
                "todo desc, " + "adds a todo to the list\n" +
                "deadline desc /due, \n" +
                "adds a deadline to the list with its due date\n" +
                "event desc /start /end, \n" +
                "adds an event to the list with its start and end\n" +
                "bye: closes the chatbot\n" +
                "----------------------------------\n";
    }

    /**
     * Prints a message to indicate that the task as been marked as done. Command is "mark X".
     *
     * @param taskDetails The details of the task being marked.
     */
    public String getMarkMessage(String taskDetails) {
        return "Great, I've marked " + taskDetails + " as done!";
    }

    /**
     * Prints a message to indicate that the task has been unmarked. Command is "unmark X".
     *
     * @param taskDetails The details of the task being unmarked.
     */
    public String getUnmarkMessage(String taskDetails) {
        return "Okay, I've unmarked " + taskDetails + " as not done!";
    }

    /**
     * Prints a message to indicate that the ToDo task has been added. Command is "todo XXX".
     *
     * @param taskDetails The details of the ToDo task.
     */
    public String getTodoMessage(String taskDetails) {
        return "I've added the todo: " + taskDetails;
    }

    /**
     * Prints a message to indicate that the Deadline task has been added. Command is "deadline XXX".
     *
     * @param taskDetails The details of the Deadline task.
     */
    public String getDeadlineMessage(String taskDetails) {
        return "I've added the deadline: " + taskDetails;
    }

    /**
     * Prints a message to indicate that the Event task has been added. Command is "event XXX".
     *
     * @param taskDetails The details of the Event task.
     */
    public String getJEventMessage(String taskDetails) {
        return "I've added the event: " + taskDetails;
    }

    /**
     * Prints a message to indicate that the task has been deleted. Command is "delete X".
     *
     * @param taskDetails The details of the task being deleted.
     */
    public String getDeleteMessage(String taskDetails) {
        return "Alright, I've deleted " + taskDetails;
    }

    /**
     * Prints the tasks that match the keyword.
     *
     * @param tasks   The list of matching tasks.
     * @param keyword The search keyword.
     */
    public String getFindMessage(ArrayList<Task> tasks, String keyword) {
        if (tasks.isEmpty()) {
            return "I couldn't find any tasks with the keyword: " + keyword;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("I've found the below tasks with the keyword: " + keyword);
            sb.append("----------------------------------\n");
            for (int i = 0; i < tasks.size(); i++) {
                String done = "[" + tasks.get(i).getDone() + "] ";
                sb.append(i + 1).append(". ").append(done).append(
                        tasks.get(i).getDetails()).append("\n");
            }
            sb.append("----------------------------------\n");
            return sb.toString();
        }
    }

    /**
     * Prints a message to indicate that the command was not recognised.
     */
    public String getUnrecognisedMessage() {
        return "I don't know what that means :(";
    }

    /**
     * Prints a message to indicate the error details.
     *
     * @param message The error message.
     */
    public String getErrorMessage(String message) {
        return message;
    }
}