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
        return "Hi, nice to meet you! I am Gopher!\n"
                + "I will be your task managing assistant for today!\n"
                + "If you want me to handle any task-related issues, here are the available commands:\n\n"
                + """
                1. todo [name] - Create a todo task
                2. deadline [name] /by [due date] - Create a deadline that due by the given date
                3. event [name] /from [start date] /to [end date] - Create an Event with specified start and end dates
                4. mark/unmark X Y Z... - Mark tasks at position X Y Z as done/not done
                5. find [keyword] - Find matching tasks based on keywords
                6. delete X Y Z - Delete tasks at position X Y Z
                7. list - List out the current tasks
                8. update X [name] [fields] [detail] - update task at position X with the specified name and fields
                9. bye - End the interaction
                """;
    }

    /**
     * Get task visualization for the given TaskList.
     *
     * @param tasks TaskList object to be printed
     */
    public static String getTaskListMessage(TaskList tasks) {
        return String.format("You currently have %d %s in the task list\n%s%s",
                tasks.getSize(),
                tasks.getSize() <= 1 ? "task" : "tasks",
                tasks,
                tasks.getSize() == 0 ? "Good job! There's no pending tasks to be done!" : "");
    }

    /**
     * Get the matched tasks message based on the given matched TaskList.
     *
     * @param tasks TaskList object containing all the matched tasks
     */
    public static String getMatchedTasksMessage(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "Sorry, I can't find any tasks that matches your keyword...";
        }
        return String.format("I have found %d matching %s in your task list:\n%s",
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
        return "Got it! I have added this task for you:\n" + task;
    }

    /**
     * Get the message when a given task is successfully mark as done.
     *
     * @param task task to be marked as done
     */
    public static String getMarkAsDoneMessage(Task task) {
        return String.format("I've marked this task as done:\n%s\nWell done! Keep up the good work!", task);
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
                    Currently I can understand the following commands:
                        1. todo - Create a ToDo Task
                        2. deadline - Create a Deadline Task
                        3. event - Create an Event Task
                        4. mark - Mark tasks as done
                        5. unmark - Mark tasks as not done
                        6. find - Find tasks based on keywords
                        7. update - Update a task with provided information
                        8. delete - Delete tasks from the task list
                        9. bye - Exit the chatbot

                    Note that the command is case-insensitive,
                    as long as the input characters match,
                    I would be able to respond to the given command""";
    }

    /**
     * Get the helping message when user inputs invalid date.
     */
    public static String getInvalidDateWarning() {
        return ("Oops...Seems like you have provided me an invalid date...\n"
                + "Please try again with a valid date in correct format...\n"
                + "Currently I can read date inputs in the following formats:\n"
                + "    1. YYYY-MM-DD\n"
                + "    2. YYYY-MM-DD hh:mm");
    }

    /**
     * Get the message when a given task is successfully updated with the given information.
     */
    public static String getUpdateTaskMessage(Task task) {
        return String.format("Alright! I have already updated this task with the given information:\n%s",
                task);
    }

    /**
     * Get the warning message when user inputs an update command without any information.
     */
    public static String getEmptyUpdateCommandWarning() {
        return "Sorry, I don't know how you want the task to be updated...\nPlease try again...";
    }
}
