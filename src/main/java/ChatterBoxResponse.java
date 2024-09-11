import java.util.ArrayList;

import task.Merchandise;
import task.Task;

/**
 * The ChatterBoxResponse class is responsible for generating responses from the chatbot based on the user's input.
 */
public class ChatterBoxResponse {

    public static void main(String[] args) {
        System.out.println("Hello");
    }

    /**
     * Returns a greeting message to welcome the user.
     *
     * @return A greeting message
     */
    public String greetUser() {
        return "\t\tHey there! I'm ChatterBox\n\t\tWhat's on your plate today?";
    }

    /**
     * Generates the string representation for current task list.
     *
     * @param taskList The list of tasks to be displayed.
     * @return A formatted string containing the list.
     */
    public String showTaskList(ArrayList<Task> taskList) {
        assert taskList != null : "TaskList cannot be null";
        StringBuilder sb = new StringBuilder();
        sb.append("\n\t\tHere's what you've got on your to-do list so far:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append("\t\t\t").append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a message indicating that a task has been added to the list.
     *
     * @param task The task added
     * @param taskCount The total number of tasks
     * @return A message which indicates task addition
     */
    public static String showTaskAdded(Task task, int taskCount) {
        assert task != null : "Task cannot be null";
        return "\n\t\t" + task.getDescription() + " is added to your list\n\t\t" + task
                + "\n\t\tNow you have " + taskCount + " tasks in your list.\n";
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked done.
     * @return A message indicating the task completion.
     */
    public static String showTaskMarkedAsDone(Task task) {
        assert task != null : "Task cannot be null";
        return "\n\t\tSuper! Task marked as done:\n\t\t" + task + "\n";
    }

    /**
     * Returns a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     * @return A message indicating the task status change.
     */
    public static String showTaskMarkedAsNotDone(Task task) {
        assert task != null : "Task cannot be null";
        return "\n\t\tSure, task has been marked as not done:\n\t\t" + task + "\n";
    }

    /**
     * Returns a message indicating that a task has been removed from the list.
     *
     * @param task The removed task.
     * @param taskCount The total number of tasks remaining.
     * @return A message indicating the task removal.
     */
    public static String showTaskRemoved(Task task, int taskCount) {
        assert task != null : "Task cannot be null";
        return "\n\t\tNoted. I've removed this task:\n\t\t" + task
                + "\n\t\tNow you have " + taskCount + " tasks in the list\n";
    }

    /**
     * Returns an error message indicating that an unknown command was entered.
     *
     * @return An error message for an unknown command.
     */
    public static String showErrorUnknownCommand() {
        return "\n\t\tOOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }

    /**
     * Returns an error message indicating that an invalid task number.
     *
     * @return An error message for an invalid task.
     */
    public static String showErrorInvalidTaskNumber() {
        return "\n\t\tInvalid task number.\n";
    }

    public static String showErrorInvalidMerchandiseNumber() {
        return "\n\t\tInvalid merchandise number.\n";
    }

    /**
     * Returns an error message indicating that the description of a TODO task is empty.
     *
     * @return An error message for an empty TODO description.
     */
    public static String showErrorEmptyTodoDescription() {
        return "\n\t\tOOPS!!! The description of a todo cannot be empty\n";
    }

    /**
     * Returns an error message indicating that the description of a deadline task is empty.
     *
     * @return An error message for an empty deadline description.
     */
    public static String showErrorEmptyDeadlineDescription() {
        return "\n\t\tOOPS!!! The description of a deadline cannot be empty\n";
    }

    /**
     * Returns an error message indicating that the description of an event task is empty.
     *
     * @return An error message for an empty event description.
     */
    public static String showErrorEmptyEventDescription() {
        return "\n\t\tOOPS!!! The description of an event cannot be empty\n";
    }

    /**
     * Returns a goodbye message to the user.
     *
     * @return A goodbye message.
     */
    public static String showGoodbye() {
        return "\n\t\tTake care! Looking forward to helping you again soon!\n";
    }

    /**
     * Returns a custom error message based on the provided message.
     *
     * @param message The custom error message.
     * @return The custom error message.
     */
    public static String showError(String message) {
        assert message != null : "Messaage cannot be null";
        return "\n\t\t" + message + "\n";
    }

    /**
     * Generates a formatted string representing a list of tasks that match a search criteria.
     *
     * @param taskList The list of matching tasks.
     * @return A formatted string containing the search results.
     */
    public static String showFindTaskList(ArrayList<Task> taskList) {
        assert taskList != null : "TaskList cannot be null";
        StringBuilder sb = new StringBuilder();
        sb.append("\n\t\tHere are the matching tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append("\t\t\t").append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }
        return sb.toString();
    }

    public String showIncorrectFormatForMerchandise() {
        return "\n\t\tOOPS!!! The correct format for add merchandise is: \n"
                + "add merchandise, ID, NAME, DESCRIPTION \n";
    }

    public String showIncorrectFormatForUpdatingDescription() {
        return "\n\t\tOOPS!!! The correct format for updating merchandise description is: \n"
                + "modify merchandise description, ID, DESCRIPTION \n";
    }

    public String showMerchandiseAdded(Merchandise merchandise, int merchandiseCount) {
            return "\n\t\t" + merchandise.getName() + " is added to your list\n\t\t" + merchandise
                    + "\n\t\tNow you have " + merchandiseCount + " merchandise in your list.\n";
    }

    public String showMerchandiseDescriptionUpdated(Merchandise merchandise) {
        if(merchandise == null) {
            return "\n\t\t ID DOES'NT MATCH! PLEASE TRY ANOTHER!\n";
        } else {
            return "\n\t Description of merchandise changed! \n"
                    + merchandise;
        }
    }

    public String showMerchandiseNameUpdated(Merchandise merchandise) {
        if(merchandise == null) {
            return "\n\t\t ID DOES'NT MATCH! PLEASE TRY ANOTHER!\n";
        } else {
            return "\n\tName of merchandise changed! \n"
                    + merchandise;
        }
    }

    public String showMerchandiseList(ArrayList<Merchandise> merchandises) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\t\tHere's what you've got on your merchandise list so far:\n");
        for (int i = 0; i < merchandises.size(); i++) {
            sb.append("\t\t\t").append(i + 1).append(". ").append(merchandises.get(i)).append("\n");
        }
        return sb.toString();
    }

    public static String showFindMerchandiseList(ArrayList<Merchandise> merchandises) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\t\tHere are the matching merchandises in your list:\n");
        for (int i = 0; i < merchandises.size(); i++) {
            sb.append("\t\t\t").append(i + 1).append(". ").append(merchandises.get(i)).append("\n");
        }
        return sb.toString();
    }

    public static String showMerchandiseRemoved(Merchandise merchandise, int merchandiseCount) {
        return "\n\t\tNoted. I've removed this merchandise:\n\t\t" + merchandise
                + "\n\t\tNow you have " + merchandiseCount + " merchandises\n";
    }
}