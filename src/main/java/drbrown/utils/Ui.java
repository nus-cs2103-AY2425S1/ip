package drbrown.utils;

import java.util.Scanner;

import drbrown.task.Task;

/**
 * Handles the user interface for the DrBrown application.
 * Provides methods to interact with the user by displaying messages and reading input.
 */
public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the greeting message to the user when the application starts.
     *
     * @return The greeting message as a String.
     */
    public String showGreeting() {
        return "Roads? Where We're Going, We Don't Need Roads?! So, what can I help you with today?";
    }

    /**
     * Displays the end message to the user when the application is closing.
     *
     * @return The end message as a String.
     */
    public String showEnd() {
        return "Your future is whatever you make it, so make it a good one! Until next time, keep the DeLorean ready!";
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     * @return The error message as a String.
     */
    public String showError(String message) {
        assert message != null : "Message should not be null";
        return message;
    }

    /**
     * Displays a message confirming the creation of a new task.
     *
     * @param tasks The TaskList containing the current tasks.
     * @param task  The task that was created.
     * @return A string message confirming the task creation.
     */
    public String showTaskCreation(TaskList tasks, Task task) {
        assert task != null : "Task should not be null";
        return task.toUiString() + "\n" + task + "\n" + this.showCount(tasks);
    }

    /**
     * Displays a message confirming the marking of a task as completed.
     *
     * @param markTask The task that was marked as completed.
     * @return A string message confirming the task has been marked as completed.
     */
    public String showMarkTask(Task markTask) {
        assert markTask != null : "Task should not be null";
        return "Task complete! If my calculations are correct, when this baby hits 88 miles per hour..."
                + " you're gonna see some serious progress!\n\n" + markTask;
    }

    /**
     * Displays a message confirming the unmarking of a task as incomplete.
     *
     * @param unmarkTask The task that was unmarked as incomplete.
     * @return A string message confirming the task has been unmarked as incomplete.
     */
    public String showUnmarkTask(Task unmarkTask) {
        assert unmarkTask != null : "Task should not be null";
        return "Looks like we're going back to the future-task unmarked! Time to revisit this one.\n\n" + unmarkTask;
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @return A string message introducing the task list.
     */
    public String showList() {
        return "Here's your list! Like Doc Brown's flux capacitor, this will help keep everything in perfect sync!\n\n";
    }

    /**
     * Displays a message confirming the deletion of a task.
     *
     * @param tasks      The TaskList containing the current tasks.
     * @param deleteTask The task that was deleted.
     * @return A string message confirming the deletion of the task.
     */
    public String showDeleteTask(TaskList tasks, Task deleteTask) {
        assert deleteTask != null : "Task should not be null";
        return "That task's history has been erased! Just like Marty's fading photo - it's gone, "
                + "like it never existed!\n\n" + deleteTask + "\n" + this.showCount(tasks);
    }

    /**
     * Displays the total count of tasks in the list.
     *
     * @param tasks The TaskList containing the current tasks.
     * @return A string message displaying the total count of tasks.
     */
    public String showCount(TaskList tasks) {
        assert tasks != null : "Tasks should not be null";
        return "\nYour total count is now " + tasks.getCount() + "! Like the time circuits, "
                + "everything's in sync - keep those tasks ticking along!";
    }

    /**
     * Displays a default message when the input is unrecognized.
     *
     * @return A string message indicating unrecognized input.
     */
    public String showDefault() {
        return "I'm from the future, and even I don't know what that means.";
    }

    /**
     * Displays the matching tasks found in the list.
     *
     * @return A string message introducing the matching tasks.
     */
    public String showFind() {
        return "Here are the matching tasks in your list — it's your density... "
                + "I mean, your destiny to get these done!\n";
    }

    /**
     * Returns the response message for an input that is too long.
     *
     * @return The bye exception message.
     */
    public static String getByeException() {
        return "Whoa, hold on! You've written more letters than necessary! "
                + "It's like trying to fit a flux capacitor into a toaster – it just doesn't belong!";
    }

    /**
     * Returns the response message for missing a description in a to-do command.
     *
     * @return The to-do exception message for no description.
     */
    public static String getTodoExceptionNoDescription() {
        return "Great Scott! You can't add a to-do without a "
                + "description!\n\nUse the format: todo {description} /priority {priority}";
    }

    /**
     * Returns the response message for missing a priority in a to-do command.
     *
     * @return The to-do exception message for no priority.
     */
    public static String getTodoExceptionNoPriority() {
        return "Whoa, this priority is heavy! Set it to 1, 2, or 3 to "
                + "keep the timeline intact! Use the format: todo {description} /priority {priority}";
    }

    /**
     * Returns the response message for other to-do related errors.
     *
     * @return The to-do exception message for other errors.
     */
    public static String getTodoExceptionOthers() {
        return "Great Scott! You can't add a to-do without a description!"
                + "\n\nUse the format: todo {description} /priority {priority}";
    }

    /**
     * Returns the response message for adding a to-do task.
     *
     * @return The message to be displayed when a to-do task is added.
     */
    public static String getTodoUiString() {
        return "Doc, you don't just walk into a store and buy plutonium! "
                + "But you sure can add this task to your list!\n";
    }

    /**
     * Returns the response message for missing a description or date in a deadline command.
     *
     * @return The deadline exception message for no description.
     */
    public static String getDeadlineExceptionNoDescription() {
        return "Great Scott! You can't add a deadline without a "
                + "description and date!\nUse the format: deadline {description} /by "
                + "{MMM dd yyyy HH:mm} /priority {priority}";
    }

    /**
     * Returns the response message for missing a date in a deadline command.
     *
     * @return The deadline exception message for no date.
     */
    public static String getDeadlineExceptionNoDate() {
        return "Hello? Hello? Anybody home? Looks like something's missing "
                + "here!\nUse the format: deadline {description} /by {MMM dd yyyy HH:mm} /priority {priority}";
    }

    /**
     * Returns the response message for other deadline related errors.
     *
     * @return The deadline exception message for other errors.
     */
    public static String getDeadlineExceptionOthers() {
        return "Looks like your Uncle Joey didn't make parole again... "
                + "and you missed the date! Let's fix that deadline!\nUse the format: deadline "
                + "{description} /by {MMM dd yyyy HH:mm} /priority {priority}";
    }

    /**
     * Returns the response message for adding a deadline task.
     *
     * @return The message to be displayed when a deadline task is added.
     */
    public static String getDeadlineUiString() {
        return "Last night, Darth Vader came down from Planet Vulcan and told me that "
                + "if you don't meet this deadline... he'd melt your brain! So, better get moving!\n";
    }

    /**
     * Returns the response message for missing an index in a delete command.
     *
     * @return The delete exception message for no index.
     */
    public static String getDeleteExceptionNoIndex() {
        return "You can't erase something from history without a count!\n"
                + "Use the format: delete {count}";
    }

    /**
     * Returns the response message for missing a description or date in an event command.
     *
     * @return The event exception message for no description.
     */
    public static String getEventExceptionNoDescription() {
        return "Great Scott! You can't add an event without a description "
                + "and from and to date!\nUse the format: "
                + "event {description} /from {MMM dd yyyy HH:mm} /to {MMM dd yyyy HH:mm} /priority {priority}";
    }

    /**
     * Returns the response message for missing a date in an event command.
     *
     * @return The event exception message for no date.
     */
    public static String getEventExceptionNoDate() {
        return "Looks like your Uncle Joey didn't make parole again... "
                + "and you missed the date! Let's fix that event!\nUse the format: "
                + "event {description} /from {MMM dd yyyy HH:mm} /to {MMM dd yyyy HH:mm} /priority {priority}";
    }

    /**
     * Returns the response message for adding an event task.
     *
     * @return The message to be displayed when an event task is added.
     */
    public static String getEventUiString() {
        return "The appropriate question is: 'When the hell are they?' Your event is now set in time!\n";
    }

    /**
     * Returns the response message for other event related errors.
     *
     * @return The event exception message for other errors.
     */
    public static String getEventExceptionOthers() {
        return "Looks like your Uncle Joey didn't make parole again... "
                + "and you missed the date! Let's fix that event!\nUse the format: "
                + "event {description} /from {MMM dd yyyy HH:mm} /to {MMM dd yyyy HH:mm} /priority {priority}";
    }

    /**
     * Returns the response message for an invalid non-numeric input.
     *
     * @return The exception message for not a number.
     */
    public static String getExceptionNotNumber() {
        return "That's not a number! Without the right input, we're never going to get "
                + "this DeLorean off the ground!";
    }

    /**
     * Returns the response message for an input that is too short.
     *
     * @return The find exception message.
     */
    public static String getFindException() {
        return "Whoa, hold on! You've written too few letters than necessary! "
                + "It's like trying to fit a flux capacitor into a toaster – it just doesn't belong!";
    }

    /**
     * Returns the response message for an input that is too long.
     *
     * @return The list exception message.
     */
    public static String getListException() {
        return "Whoa, hold on! You've written more letters than necessary! "
                + "It's like trying to fit a flux capacitor into a toaster - it just doesn't belong!";
    }

    /**
     * Returns the response message for missing an index in a mark command.
     *
     * @return The mark exception message.
     */
    public static String getMarkException() {
        return "Great Scott! You can't complete a task without a count!\n"
                + "Use the format: mark {count}";
    }

    /**
     * Returns the response message for missing an index in an unmark command.
     *
     * @return The unmark exception message.
     */
    public static String getUnmarkException() {
        return "Great Scott! You can't go back in time without a count!\n"
                + "Use the format: unmark {count}";
    }

    /**
     * Returns the default exception message for unknown commands.
     *
     * @return The default exception message.
     */
    public static String getDefaultException() {
        return "I'm from the future, and even I don't know what that means.";
    }

    /**
     * Returns the exception message for a corrupted file format.
     *
     * @return The file corrupted exception message.
     */
    public static String getExceptionFileCorrupted() {
        return "The file provided might be corrupted since it does not follow the specified format.";
    }

    /**
     * Returns the exception message for first-time file creation.
     *
     * @return The first-time file exception message.
     */
    public static String getExceptionFileFirstTime() {
        return "Oops! It seems like this is your first time. "
                + "No worries, I've created a brand new file to get you started.";
    }

    /**
     * Returns the exception message for a wrong file path.
     *
     * @return The wrong path exception message.
     */
    public static String getExceptionFileWrongPath() {
        return "Seems like you messed up the file path somehow!";
    }

    /**
     * Returns the exception message for an incorrect index.
     *
     * @return The wrong index exception message.
     */
    public static String getExceptionWrongIndex() {
        return "You got the count wrong! That's not how you calculate time "
                + "travel - you're off by a few gigawatts!";
    }

    /**
     * Returns the exception message for an empty task list.
     *
     * @return The empty list exception message.
     */
    public static String getExceptionEmptyList() {
        return "Wait a minute, Doc! There's nothing here! We can't go "
                + "anywhere until you add something to the list!";
    }
}
