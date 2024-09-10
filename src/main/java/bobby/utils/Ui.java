package bobby.utils;

import java.util.ArrayList;

import bobby.tasks.Task;

/**
 * The Ui class deals with any user interaction.
 */
public class Ui {

    /**
     * Prints the greeting message of Bobby.
     */
    public String showGreeting() {
        String output = "";
        output += "Good day Sire/Madam! I'm Bobby\n";
        output += "How may I be of assistance today?\n";
        return output;
    }

    /**
     * Prints the farewell message of Bobby.
     */
    public String showBye() {
        String output = "";
        output += "Farewell, have a pleasant journey ahead!\n";
        return output;
    }

    /**
     * Prints the message when a task is created successfully.
     * @param t Task to be created.
     * @param taskList The list of tasks.
     */
    public String showTaskCreated(Task t, ArrayList<Task> taskList) {
        String output = "";
        output += "Alright! I have added a new task.\n";
        output += "You have a total of " + taskList.size() + " tasks now.\n";
        return output;
    }

    /**
     * Prints the message when a task is marked.
     */
    public String showMarked() {
        String output = "";
        output += "Sure thing! I will check off this task as done.\n";
        return output;
    }

    /**
     * Prints the message when a task is unmarked.
     */
    public String showUnmarked() {
        String output = "";
        output += "Roger that, I will uncheck the task.\n";
        return output;
    }

    /**
     * Prints the message when a task is deleted.
     *
     * @param t Task to be deleted.
     * @param numberOfTasksLeft Number of tasks left in the list after deletion.
     */
    public String showTaskDeleted(Task t, int numberOfTasksLeft) {
        String output = "";
        output += "As you wish, I will remove this task: " + t.toString() + "\n";
        output += "Now you have " + numberOfTasksLeft + " tasks left.\n";
        return output;
    }

    /**
     * Prints the list of tasks.
     *
     * @param taskList The list of tasks.
     */
    public String showTaskList(ArrayList<Task> taskList) {
        String output = "";
        int numberOfTasks = taskList.size();

        if (taskList.isEmpty()) {
            return "Sire/Madam, you have no tasks at hand right now.";
        } else {
            for (int i = 0; i < numberOfTasks; i++) {
                int number = i + 1;
                output += (number + ". " + taskList.get(i).toString() + "\n");
            }
            return output;
        }
    }

    /**
     * Prints the matching task lists for Find functionality.
     *
     * @param matchingTaskList The list of tasks containing matching tasks.
     */
    public String showFindTasks(ArrayList<Task> matchingTaskList) {
        String output;
        if (matchingTaskList.isEmpty()) {
            output = "There are no matching tasks!";
            return output;
        } else {
            output = "Here are the matching tasks:\n";
            output += showTaskList(matchingTaskList);
            return output;
        }
    }

    /**
     * Prints the task that is archived
     *
     * @param taskList The list of tasks.
     */
    public String showArchiveTask(Task t, ArrayList<Task> taskList) {
        String output = "";
        output += "As you wish, I will archive this task: " + t.toString() + "\n";
        return output;
    }

    /**
     * Prints the list of archived tasks.
     *
     * @param taskList The list of archived tasks.
     */
    public String showArchiveList(ArrayList<Task> taskList) {
        String output = "";
        int numberOfTasks = taskList.size();

        if (taskList.isEmpty()) {
            return "Sire/Madam, you have no archived tasks.";
        } else {
            for (int i = 0; i < numberOfTasks; i++) {
                int number = i + 1;
                output += (number + ". " + taskList.get(i).toString() + "\n");
            }
            return output;
        }
    }
}
