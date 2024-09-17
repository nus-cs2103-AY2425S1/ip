package gopher.ui;

import gopher.exception.UnknownCommandException;
import gopher.task.Task;
import gopher.task.TaskList;

/**
 * Represents the UI that handles the user interactions and messages.
 */
public class UI {
    /**
     * Get the greeting message to show to the user
     */
    public static String getGreetMessage() {
        return "Hi, welcome to Gopher!\n"
                + "Here are the available commands:\n"
                + """
                1. todo/deadline/event - Create event of these types
                2. mark/unmark X - Mark X-th task as done/not done
                3. find [keyword] - Find matching tasks based on keywords
                4. delete X - Delete X-th task
                5. list - List out the current tasks
                6. bye - End the interaction
                """;
    }

    /**
     * Get task visualization for the given TaskList.
     *
     * @param tasks TaskList object to be printed
     */
    public static String getTaskListMessage(TaskList tasks) {
        return String.format("You currently have %d %s in the list\n%s",
                tasks.getSize(),
                tasks.getSize() == 1 ? "task" : "tasks",
                tasks);
    }

    /**
     * Get the matched tasks message based on the given matched TaskList.
     *
     * @param tasks TaskList object containing all the matched tasks
     */
    public static String getMatchedTasksMessage(TaskList tasks) {
        return String.format("Found %d matching %s in your list:\n%s",
                tasks.getSize(),
                tasks.getSize() == 1 ? "task" : "tasks",
                tasks
        );
    }

    /**
     * Get the message when TaskList successfully add in a new task.
     *
     * @param task     task to be added
     */
    public static String getAddTaskMessage(Task task) {
        return "Got it! I have added this task:\n" + task;
    }

    /**
     * Get the message when a given task is successfully mark as done.
     *
     * @param task task to be marked as done
     */
    public static String getMarkAsDoneMessage(Task task) {
        return String.format("Nice! I've marked this task as done:\n%s\n", task);
    }

    /**
     * Get the message when a given task is successfully mark as not done.
     *
     * @param task task to be marked as not done
     */
    public static String getMarkAsUndoneMessage(Task task) {
        return String.format("Ok, I've marked this task as not done yet:\n%s\n", task);
    }

    /**
     * Get the message when TaskList successfully delete a task.
     *
     * @param task     task to be deleted
     */
    public static String getDeleteTaskMessage(Task task) {
        return String.format("Noted. I've removed this task:\n%s\n", task);
    }

    /**
     * Get the exit message.
     */
    public static String getExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Get the helping message when user encounters an UnknownCommandException.
     *
     * @param e UnknownCommandException thrown by the system
     */
    public static String getUnknownCommandWarning(UnknownCommandException e) {
        return e.getMessage()
                +
                """
                    Current Supported Commands:
                        1. todo - Create a ToDo Task
                        2. deadline - Create a Deadline Task
                        3. event - Create an Event Task
                        4. mark - Mark a task as done
                        5. unmark - Mark a task as not done
                        6. find - Find tasks based on keywords
                        7. bye - Exit the chatbot

                    Note: The command is case-insensitive,
                    as long as the input characters match,
                    the chatbot would be able to respond to the given command""";
    }

    /**
     * Get the helping message when user inputs invalid date.
     */
    public static String getInvalidDateWarning() {
        return ("Oops...Seems like you have passed in invalid date...\n"
                + "Please try again with valid date and correct format...\n"
                + "Current accepted date format: YYYY-MM-DD or YYYY-MM-DD hh:mm");
    }
}
