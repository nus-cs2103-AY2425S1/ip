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
        return "Greetings. I am " + GuiResponses.BOTNAME + ".\n"
                + "I am here to assist you with your thoughts and tasks. "
                + "How may I guide you today?";
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
        StringBuilder toReturn = new StringBuilder(
                String.format("You have %d Tasks in List: \n", userList.size()));
        for (int i = 0; i < userList.size(); i++) {
            toReturn.append(String.format(i + 1 + ". " + "[%s][%s] %s",
                    userList.get(i).getTaskSymbol(),
                    userList.get(i).getStatus() ? "X" : " ",
                    userList.get(i).getDescription()) + "\n");
        }
        if (userList.size() == 0) {
            toReturn.append("Your task list is empty, a rare moment of calmness.");
        }
        if (userList.size() > 0 && userList.size() < 5) {
            toReturn.append("You have fewer than 5 tasks. It seems the load is manageable for now."
                    + "\n Keep going!");
        }
        if (userList.size() > 10) {
            toReturn.append("The list has grown beyond 10 tasks... sometimes it feels like the journey is endless.");
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
        return "You've completed the task %s" + "\n" + task.getDescription()
                + "\n" + "Each completed task is a step closer to clarity, "
                + "though the journey may still feel long...";
    }

    /**
     * gets msg for unmarking a task
     * @param task that got unmarked
     * @return msg for unmarking a task
     */
    public String unmarkMsg(Task task) {
        return "The task has been marked as undone."
                + " Sometimes, it feels like our efforts to move forward are met with setbacks.\n"
                + "You now have one more task to address:\n"
                + task.getDescription();
    }

    /**
     * Return standard template for displaying added task
     * @param type type of task
     * @param size size of user task list
     * @return standard template for displaying added task
     */
    public String addTaskMsg(String type, int size) {
        return String.format("Added %s to Tasks", type) + "\n"
                + String.format("Currently %d tasks in List. ", size)
                + String.format("Sometimes, I wonder if you have too much time on your hands... (¬_¬)", size);

    }

    /**
     * returns message for deleting a task
     * @param task Task that is deleted
     * @param size size of the tasks.TaskList object task is removed from
     * @return Deleted task message
     */
    public String delTaskMsg(Task task, int size) {
        return "The task has been removed: " + task.getDescription() + "\n"
                + String.format("You now have %d tasks remaining. "
                + "Each deletion is a step forward, though the journey continues.", size);
    }

    /**
     * Gets String representation of matching search
     * @param matches Tasklist of searches that match criteria
     * @return String representation of matching search
     */
    public String getSearchList(ArrayList<Task> matches) {
        StringBuilder toReturn = new StringBuilder("Here are the tasks that align with your search:\n");
        if (matches.size() == 0) {
            toReturn.append("...It seems there are no tasks that match your criteria at this moment. "
                    + "Sometimes, the answers we seek remain hidden.");
        }
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
        return String.format("The task has been tagged with '%s'.\n"
                + "This addition might change how you perceive and approach this task, "
                + "adding another layer to its significance:\n"
                + "%s", tagName, task.descNoTags());
    }
    /**
     * Gets String representation of all tags
     * @param tagKeys set of strings of tags
     * @return String representation of all tags
     */
    public String displayAllTags(Set<String> tagKeys) {
        if (tagKeys.isEmpty()) {
            return "It appears there are no tags at the moment. Sometimes, our organization tools can feel sparse.";

        }
        StringBuilder tagsString = new StringBuilder(
                String.format("You currently have %d Tags: \n", tagKeys.size()));
        for (String tag : tagKeys) {
            tagsString.append(tag).append("\n");
        }
        return tagsString.toString();
    }

    /**
     * Gets String representation of invalid command response
     * @return String representation of invalid command response
     */
    public String getInvalidCommandMessage() {
        return "It seems I didn’t quite grasp that command. Sometimes, our communication can be elusive."
                + " Please try again.";
    }

}
