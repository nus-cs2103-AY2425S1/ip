package hoshi.ui;

import hoshi.task.Task;
import hoshi.task.TaskList;

/**
 * Ui class that handles User Interactions for HoSHI
 */
public class Ui {

    /**
     * Displays the welcome message and logo of Hoshi to the user.
     */
    public void displayWelcome() {

        String logo = """
                 __    __    ______        _______. __    __   __ \s
                |  |  |  |  /  __  \\      /       ||  |  |  | |  |\s
                |  |__|  | |  |  |  |    |   (----`|  |__|  | |  |\s
                |   __   | |  |  |  |     \\   \\    |   __   | |  |\s
                |  |  |  | |  `--'  | .----)   |   |  |  |  | |  |\s
                |__|  |__|  \\______/  |_______/    |__|  |__| |__|\s
                                                                  \s
                """;
        System.out.println(logo);
        System.out.println("""
                ____________________________________________________________
                Hello! I'm Hoshi!
                What can I do for you?
                ____________________________________________________________
                """);

    }

    /**
     * Displays all tasks that the user has previously added to Hoshi
     */
    public String displayTasks(TaskList tasks) {

        if (!tasks.isEmpty()) {
            return tasks.toString();
        } else {
            return "Hoshi doesn't have anything stored! Please add a task first";
        }
    }

    /**
     * Displays all tasks that the user has previously added to Hoshi that match the search query
     */
    public String displayFoundTasks(TaskList tasks) {

        if (!tasks.isEmpty()) {
            return displayMatchingList() + "\n" + tasks;
        } else {
            return "Hoshi couldn't find tasks with that keyword, please try again!";
        }
    }

    /**
     * Displays default prompt with options for user to select from
     */
    public void displayPrompt() {
        System.out.println("____________________________________________________________ \n"
                + "- Add ToDo/Deadline/Event \n"
                + "- List \n"
                + "- Mark/Unmark \n"
                + "- Delete \n"
                + "- Find \n"
                + "- Bye \n"
                + "____________________________________________________________");
    }

    /**
     * Displays text requesting user to specify which task to mark
     */
    public String displayTaskToMark() {
        return "Please specify the task number to mark!";
    }

    /**
     * Displays text indicating text has been marked as done
     */
    public String displayTaskMarked(Task task) {
        return "Nice! I've marked this task as done: \n" + task.toString() + "\n";
    }

    /**
     * Displays text indicating text has been unmarked
     */
    public String displayTaskUnmarked(Task task) {
        return "Nice! I've marked this task as not done: \n" + task.toString() + "\n";

    }

    /**
     * Displays text requesting user to specify which task to delete
     */
    public void displayTaskToDelete() {
        System.out.println("Please specify the task number to delete! \n");
    }

    /**
     * Displays text indicating which task was deleted
     */
    public String displayTaskDeleted(Task task) {
        return "OK, Hoshi has removed ( " + task.getDesc() + " )! \n";
    }

    /**
     * Displays text requesting user to specify which task to add
     */
    public String displayTaskAdd() {
        return "Please specify the task to add! E.g. Add {task to be added} \n";
    }

    /**
     * Displays text requesting user to specify to do to add
     */
    public void displayTodoTask() {
        System.out.println("Understood! What is your ToDo? ");
    }

    /**
     * Displays text requesting user to specify deadline to add
     */
    public void displayDeadlineTask() {
        System.out.println("Understood! What is your Deadline? ");
    }

    /**
     * Displays text requesting user to specify when deadline is due
     */
    public void displayDeadlineDue() {
        System.out.println("When would you like your Deadline to be due by? ");
    }

    /**
     * Displays text requesting user to specify event to add
     */
    public void displayEventTask() {
        System.out.println("Understood! What is your Event? ");
    }

    /**
     * Displays text requesting user to specify when event starts
     */
    public void displayEventStart() {
        System.out.println("When would you like your Event to start? ");
    }

    /**
     * Displays text requesting user to specify when event ends
     */
    public void displayEventEnd() {
        System.out.println("When would you like your Event to end? ");
    }

    /**
     * Displays text indicating task has been added
     */
    public String displayTaskAdded(String input) {
        return "added: " + input;
    }

    /**
     * Displays text indicating search has returned several tasks
     */
    public String displayMatchingList() {
        return "Hoshi has found the following tasks matching your search!";
    }

    /**
     * Displays text indicating an error has occurred
     */
    public String displayError(String message) {
        return message;
    }

    /**
     * Displays text indicating a loading error has occurred
     */
    public void displayLoadingError(String message) {
        System.out.println("Hoshi can't load data! " + message);
    }

    /**
     * Displays text indicating a saving error has occurred
     */
    public void displaySavingError(String message) {
        System.out.println("Hoshi can't save data! " + message);
    }

    /**
     * Displays text indicating the program has terminated
     */
    public String displayBye() {
        return "Bye, Hope to see you again soon! \n";
    }

    /**
     * Displays text indicating the program has started
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
