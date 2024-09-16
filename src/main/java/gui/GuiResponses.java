package gui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;

import tasks.Task;
import tasks.TaskList;

/**
 * Handles the string formatting of chatterbox responses for the gui
 */
public class GuiResponses {
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String BOTNAME = "Chatterbox";

    private static final DateTimeFormatter PRINTDATEFORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");


    public GuiResponses() {

    }

    /**
     * generates String for standard greeting
     * @return string format of the greeting
     */
    public String greeting() {
        return "Hello! I'm " + GuiResponses.BOTNAME
                + "\nWhat can I do for you?";

    }

    /**
     * generates String for standard goodbye
     * @return string format of the goodbye message
     */
    public String goodBye() {
        return "Bye! Hope to see you again!";


    }

    /**
     * display the list of tasks
     * @param tasks TaskList object
     * @return String representation of tasks in list
     */
    public String displayList(TaskList tasks) {
        ArrayList<Task> userList = tasks.getTasks();
        StringBuilder toReturn = new StringBuilder("Current Tasks in List: \n");
        for (int i = 0; i < userList.size(); i++) {
            toReturn.append(String.format(i + 1 + ". " + "[%s][%s] %s",
                    userList.get(i).getTaskSymbol(),
                    userList.get(i).getStatus() ? "X" : " ",
                    userList.get(i).getDescription()) + "\n");
        }
        return toReturn.toString();
    }

    /**
     * Gets description of task
     * @param task to get description
     * @return task description
     */
    public String getTaskDescription(Task task) {
        return task.getDescription();
    }

    /**
     * gets msg for marking a task
     * @param task that got marked
     * @return msg for marking a task
     */
    public String markMsg(Task task) {
        return "Marked Task as done!" + "\n" + task.getDescription();
    }

    /**
     * gets msg for unmarking a task
     * @param task that got unmarked
     * @return msg for unmarking a task
     */
    public String unmarkMsg(Task task) {
        return "Marked Task as undone!" + "\n" + task.getDescription();

    }

    /**
     * Return standard template for displaying added task
     * @param type type of task
     * @param size size of user task list
     * @return standard template for displaying added task
     */
    public String addTaskMsg(String type, int size) {
        return String.format("Added %s to Tasks", type) + "\n"
                + String.format("Currently %d tasks in List", size);

    }

    /**
     * returns message for deleting a task
     * @param task Task that is deleted
     * @param size size of the tasks.TaskList object task is removed from
     * @return Deleted task message
     */
    public String delTaskMsg(Task task, int size) {


        return "Deleting Task: " + task.getDescription()
                + "\n" + String.format("%d tasks left", size);
    }

    /**
     * Gets String representation of matching search
     * @param matches Tasklist of searches that match criteria
     * @return String representation of matching search
     */
    public String getSearchList(ArrayList<Task> matches) {
        StringBuilder toReturn = new StringBuilder("Displaying All Matching Tasks: +\n");
        for (int i = 0; i < matches.size(); i++) {
            toReturn.append(String.format(i + 1 + ". " + "[%s][%s] %s",
                    matches.get(i).getTaskSymbol(),
                    matches.get(i).getStatus() ? "X" : " ",
                    matches.get(i).getDescription()) + "\n");
        }
        return toReturn.toString();

    }

    /**
     * Gets String representation of tagged task
     * @param task Task that is tagged
     * @param tagName name of tag
     * @return String representation of tagged task
     */
    public String taggedTasks(Task task, String tagName) {
        return String.format("Tagged Task: %s with tag: %s", task.descNoTags(), tagName);
    }

    /**
     * Gets String representation of all tags
     * @param tagKeys set of strings of tags
     * @return String representation of all tags
     */
    public String displayAllTags(Set<String> tagKeys) {
        if (tagKeys.size() == 0) {
            return "No tags found";
        }
        StringBuilder tagsString = new StringBuilder(
                String.format("Currently have %d Tags: \n", tagKeys.size()));
        for (String tag : tagKeys) {
            tagsString.append(tag).append("\n");
        }
        return tagsString.toString();
    }

    public String getInvalidCommandMessage() {
        return "Sorry, I don't understand that command. Please try again. ˙◠˙";
    }

}
