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
    public String goodbye() {
        return "Farewell. May our paths cross again in the future.";


    }

    /**
     * display the list of tasks
     * @param tasks TaskList object
     * @return String representation of tasks in list
     */
    public String listTaskMsg(TaskList tasks) {
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
                    + "\nKeep going!");
        }
        if (userList.size() > 5 && userList.size() <= 10) {
            toReturn.append("You have between 5 and 10 tasks. "
                    + "Though the workload is noticeable, it remains within a manageable range. "
                    + "The path ahead is clearer, but the journey still requires your attention.");
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
        return String.format("You've completed the task") + "\n" + task.getDescription()
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
        if (size > 5) {
            return String.format("Added task of type %s to your list", type) + "\n"
                    + String.format("There are now %d tasks in total. ", size)
                    + String.format("\nI cannot help but ponder whether this accumulation reflects a lack of time"
                    + "or an inability to manage it...", size);
        }
        return String.format("Task of type %s has been added. You now have %d tasks in total. " + "\n"
                + "It seems your list isn't overwhelming yet. "
                + "Sometimes, having fewer tasks can be as mundane as having too many.", type, size);

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
        if (matches.isEmpty()) {
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

    public String getTaggedTasks(Set<Task> tasks) {
        StringBuilder toReturn = new StringBuilder("Here are the tasks that have been tagged:\n");
        if (tasks.isEmpty()) {
            toReturn.append("...It seems there are no tasks that have been tagged at this moment. "
                    + "Sometimes, the answers we seek remain hidden.");
        }
        for (Task task : tasks) {
            toReturn.append(String.format("[%s][%s] %s",
                    task.getTaskSymbol(),
                    task.getStatus() ? "X" : " ",
                    task.getDescription()) + "\n");
        }
        return toReturn.toString();
    }

    /**
     * Gets String representation of tagged task
     * @param task Task that is tagged
     * @param tagName name of tag
     * @return String representation of tagged task
     */
    public String untagTagMsg(Task task, String tagName) {
        return String.format("Ah, it appears the tag '%s' has been removed from the task \n[%s]. "
                + "\nSometimes, the tools at our disposal may seem somewhat inadequate. "
                + "Rest assured, even in these moments of apparent simplicity, "
                + "there is always room for growth and improvement.", tagName, task.getDescription());
    }
    /**
     * Gets String representation of tagged task
     * @param task Task that is tagged
     * @param tagName name of tag
     * @return String representation of tagged task
     */
    public String tagTaskMsg(Task task, String tagName) {
        return String.format("The task has been tagged with '%s'.\n"
                + "This addition might change how you perceive and approach this task, "
                + "adding another layer to its significance:\n"
                + "%s", tagName.toLowerCase(), task.descNoTags());
    }

    /**
     * Gets String representation of already tagged task message
     * @param task Task that is already tagged
     * @param tagName name of tag
     * @return response to an already tagged message
     */
    public String alreadyTaggedMsg(Task task, String tagName) {
        return String.format("The task \n'%s' \nalready bears the tag '%s'. "
                        + "\nOne might wonder if you’re simply repeating the same actions without much thought. "
                        + "\nPerhaps it’s time to reconsider if these tags are serving any real purpose.",
                task.getDescription(), tagName);
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
     * Gets String representation of tag not found
     * @param tagName name of tag
     * @return String representation of tag not found
     */
    public String tagNotFoundMsg(String tagName) {
        return String.format("The tag '%s' was not found. Sometimes, the answers we seek remain hidden.", tagName);
    }

    /**
     * Gets String representation of invalid command response
     * @return String representation of invalid command response
     */
    public String getInvalidCommandMessage() {
        return "It seems I didn't quite grasp that command. Communication can be a delicate thing, can't it?"
                + " Perhaps, you could try again.";
    }

    /**
     * Gets String representation of invalid index
     * @return String representation of invalid index
     */
    public String getInvalidIndexMessage() {
        return "It seems the index you have entered is invalid. "
                + "Sometimes, the path to clarity is not as straightforward as we would like, don't you think?";
    }

    public String getErrorMessage(String errorMessage) {
        return "It seems an error has occurred\n" + errorMessage
                + "\nEven in moments like these, there is something to be learned";


    }

}
