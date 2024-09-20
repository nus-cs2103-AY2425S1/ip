package sinatra;

import java.util.ArrayList;
import java.util.List;

/**
 * The Ui class handles user interface interactions.
 */
public class Ui {
    private static final String INTRO_MESSAGE = "Hello! I'm Sinatra. \nWhat can I do for you?";
    private static final String LIST_OUTPUT_START = "Here are the tasks in your list:";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String MARKED_DONE_MESSAGE = "Nice! I've marked this task as done: ";
    private static final String MARKED_NOT_DONE_MESSAGE = "OK, I've marked this task as not done yet: ";
    private static final String TODO_EXCEPTION_MESSAGE = "OOPS!!! The description of a todo cannot be empty.";
    private static final String DEADLINE_EXCEPTION_MESSAGE = "OOPS!!! The description of a deadline cannot be empty.";
    private static final String TASK_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Task already exists and was not added";
    private static final String EVENT_EXCEPTION_MESSAGE = "OOPS!!! The description of an event cannot be empty.";
    private static final String ADDED_TASK_MESSAGE = "Got it. I've added this task:";
    private static final String REMOVED_TASK_MESSAGE = "Noted. I've removed this task:";
    private static final String TASK_COUNT_MESSAGE = "Now you have %d tasks in the list.";
    private static final String FIND_OUTPUT_START = "Here are the matching tasks in your list:";
    private static final String UNKNOWN_COMMAND_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String UNEXPECTED_ERROR_MESSAGE = "An unexpected error occurred: ";

    private List<String> output;

    /**
     * Constructs a new Ui instance.
     */
    public Ui() {
        output = new ArrayList<>();
    }

    /**
     * Prints the introduction message.
     */
    public void printIntro() {
        System.out.println(INTRO_MESSAGE);
    }

    /**
     * Caches the list of tasks.
     *
     * @param tasks the list of tasks
     */
    public void cacheList(ArrayList<Task> tasks) {
        output.add(LIST_OUTPUT_START);
        for (int i = 0; i < tasks.size(); i++) {
            int count = i + 1;
            output.add(count + "." + tasks.get(i).toString());
        }
    }

    /**
     * Caches the bye message.
     */
    public void cacheByeMessage() {
        output.add(BYE_MESSAGE);
    }

    /**
     * Caches the marked done message.
     *
     * @param task the task that was marked as done
     */
    public void cacheMarkedDoneMessage(Task task) {
        output.add(MARKED_DONE_MESSAGE);
        output.add(task.toString());
    }

    /**
     * Caches the marked not done message.
     *
     * @param task the task that was marked as not done
     */
    public void cacheUnmarkDoneMessage(Task task) {
        output.add(MARKED_NOT_DONE_MESSAGE);
        output.add(task.toString());
    }

    /**
     * Caches the todo exception message.
     */
    public void cacheToDoExceptionMessage() {
        output.add(TODO_EXCEPTION_MESSAGE);
    }

    /**
     * Caches the todo message.
     *
     * @param task      the task that was added
     * @param taskCount the total number of tasks
     */
    public void cacheToDoMessage(Task task, int taskCount) {
        output.add(ADDED_TASK_MESSAGE);
        output.add(" " + task.toString());
        output.add(String.format(TASK_COUNT_MESSAGE, taskCount));
    }

    /**
     * Caches the deadline exception message.
     */
    public void cacheDeadlineExceptionMessage() {
        output.add(DEADLINE_EXCEPTION_MESSAGE);
    }

    /**
     * Caches the task already exists exception message.
     */
    public void cacheTaskAlreadyExistsExceptionMessage() {
        output.add(TASK_ALREADY_EXISTS_EXCEPTION_MESSAGE);
    }

    /**
     * Caches the deadline message.
     *
     * @param task      the task that was added
     * @param taskCount the total number of tasks
     */
    public void cacheDeadlineMessage(Task task, int taskCount) {
        output.add(ADDED_TASK_MESSAGE);
        output.add(" " + task.toString());
        output.add(String.format(TASK_COUNT_MESSAGE, taskCount));
    }

    /**
     * Caches the event exception message.
     */
    public void cacheEventExceptionMessage() {
        output.add(EVENT_EXCEPTION_MESSAGE);
    }

    /**
     * Caches the event message.
     *
     * @param task      the task that was added
     * @param taskCount the total number of tasks
     */
    public void cacheEventMessage(Task task, int taskCount) {
        output.add(ADDED_TASK_MESSAGE);
        output.add(" " + task.toString());
        output.add(String.format(TASK_COUNT_MESSAGE, taskCount));
    }

    /**
     * Caches the delete message.
     *
     * @param task      the task that was removed
     * @param taskCount the total number of tasks
     */
    public void cacheDeleteMessage(Task task, int taskCount) {
        output.add(REMOVED_TASK_MESSAGE);
        output.add(" " + task.toString());
        output.add(String.format(TASK_COUNT_MESSAGE, taskCount));
    }

    /**
     * Caches the find message.
     *
     * @param foundTasks the list of found tasks
     */
    public void cacheFindMessage(ArrayList<Task> foundTasks) {
        output.add(FIND_OUTPUT_START);
        for (int i = 0; i < foundTasks.size(); i++) {
            output.add(i + 1 + "." + foundTasks.get(i).toString());
        }
    }

    /**
     * Caches the unknown command message.
     */
    public void cacheUnknownCommandMessage() {
        output.add(UNKNOWN_COMMAND_MESSAGE);
    }

    public void cacheUnexpectedErrorMessage(Exception e) {
        output.add(UNEXPECTED_ERROR_MESSAGE + e.getMessage());
    }

    /**
     * Returns the output list.
     *
     * @return the output list
     */
    public List<String> getOutput() {
        return output;
    }

    public List<String> newOutput() {
        return output = new ArrayList<String>();
    }

    /**
     * Displays an error message indicating a loading error.
     */
    public void showLoadingError() {
        System.out.println("Error loading file");
    }
}
