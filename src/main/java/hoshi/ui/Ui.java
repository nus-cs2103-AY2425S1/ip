package hoshi.ui;

import hoshi.task.Task;
import hoshi.task.TaskList;

/**
 * Ui class that handles User Interactions for HoSHI
 */
public class Ui {

    /**
     * Displays all tasks that the user has previously added to Hoshi
     *
     * @param tasks the list of tasks to be displayed.
     * @return a string of list of tasks or an error message if no tasks are stored
     */
    public String displayTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Hoshi doesn't have anything stored! Please add a task first";
        }
        return tasks.toString();
    }

    /**
     * Displays all tasks that the user has previously added to Hoshi that match the search query
     *
     * @param tasks the list of tasks to be displayed.
     * @return a string of list of tasks or an error message if no tasks are with that keyword are found
     */
    public String displayFoundTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Hoshi couldn't find tasks with that keyword, please try again!";
        }
        return displayMatchingList() + "\n" + tasks;
    }

    /**
     * Displays text requesting user to specify which task to mark
     *
     * @return the string prompting the user to specify the task number to mark
     */
    public String displayTaskToMark() {
        return "Please specify the task number to mark!";
    }

    /**
     * Displays text indicating text has been marked as done.
     *
     * @param task the task which has been marked.
     * @return the string indicating the task has been marked along with its description.
     */
    public String displayTaskMarked(Task task) {
        return "Nice! Hoshi has marked this task as done: \n" + task.toString();
    }

    /**
     * Displays text indicating text has been unmarked.
     *
     * @param task the task which has been unmarked.
     * @return the string indicating the task has been unmarked along with its description.
     */
    public String displayTaskUnmarked(Task task) {
        return "Nice! Hoshi has marked this task as not done: \n" + task.toString();
    }

    /**
     * Displays text requesting user to specify which task to delete.
     *
     * @return the string prompting the user to specify the task number to delete.
     */
    public String displayTaskToDelete() {
        return "Please specify the task number to delete!";
    }

    /**
     * Displays text indicating which task was deleted.
     *
     * @param task the task which has been deleted.
     * @return the string indicating the task has been deleted along with its description.
     */
    public String displayTaskDeleted(Task task) {
        return "OK, Hoshi has removed (" + task.getDesc() + ")!";
    }

    /**
     * Displays text prompting the user to specify a keyword to search for.
     *
     * @return the string prompting the user to specify the keyword to search for.
     */
    public String displayKeywordToFind() {
        return "Please specify the keyword to find this task!";
    }

    /**
     * Displays text indicating task has been added.
     *
     * @param desc     the description of the task that has been added.
     * @param taskType the type of task that has been added.
     * @return the string indicating the task has been added along with its type and description.
     */
    public String displayTaskAdded(String desc, String taskType) {
        return "Hoshi has added " + taskType + ": " + desc;
    }

    /**
     * Displays text indicating search has returned several tasks.
     *
     * @return the string indicating that 1 or more tasks that match the keyword has been found.
     */
    public String displayMatchingList() {
        return "Hoshi has found the following tasks matching your search!";
    }

    /**
     * Displays text indicating that the task has already been marked.
     *
     * @return the string indicating the task has already been marked.
     */
    public String displayAlreadyMarked() {
        return "Hoshi has already marked this task!";
    }

    /**
     * Displays text indicating that the task has already been unmarked.
     *
     * @return the string indicating the task has already been unmarked.
     */
    public String displayAlreadyUnmarked() {
        return "Hoshi has already unmarked this task!";
    }

    /**
     * Displays text indicating an error has occurred.
     *
     * @param message the error message to be displayed.
     * @return the error message specified.
     */
    public String displayError(String message) {
        return message;
    }

    /**
     * Displays text indicating a loading error has occurred.
     *
     * @param message the string to output when Hoshi is unable to load data.
     */
    public void displayLoadingError(String message) {
        System.out.println("Hoshi can't load data! " + message);
    }

    /**
     * Displays text indicating a saving error has occurred.
     *
     * @param message the string to output when Hoshi is unable to save data.
     */
    public void displaySavingError(String message) {
        System.out.println("Hoshi can't save data! " + message);
    }

    /**
     * Displays text indicating the program has terminated.
     *
     * @return the string indicating to the user that the programme has terminated.
     */
    public String displayBye() {
        return "Bye, Hope to see you again soon! \n";
    }

    /**
     * Displays text indicating the program has started.
     *
     * @return a string consisting of a summary of the available commands of Hoshi.
     */
    public String initialize() {
        return "Welcome to Hoshi! Try the following commands out!\n"
                + "1.) Add todo/deadline/event\n"
                + "2.) Mark/Unmark {1}\n"
                + "3.) Delete {1}\n"
                + "4.) Find\n"
                + "5.) List\n"
                + "6.) Bye";
    }
}
