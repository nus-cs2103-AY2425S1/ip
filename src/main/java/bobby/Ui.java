package bobby;

import java.util.ArrayList;

/**
 * The Ui class deals with any user interaction.
 */
public class Ui {
    static int lengthOfLine = 35;

    /**
     * Prints out a horizontal line in the form of underscores.
     *
     * @param x The number of underscores to be printed out.
     */
    public void horizontalLine(int x) {
        for (int i = 0; i < x; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    /**
     * Prints the greeting message of Bobby.
     */
    public void showGreeting() {
        horizontalLine(lengthOfLine);
        System.out.println("Good day Sire/Madam! I'm Bobby");
        System.out.println("How may I be of assistance today?");
        horizontalLine(lengthOfLine);
    }

    /**
     * Prints the farewell message of Bobby.
     */
    public void showBye() {
        horizontalLine(lengthOfLine);
        System.out.println("Farewell, have a pleasant journey ahead!");
        horizontalLine(lengthOfLine);
    }

    /**
     * Prints the message when a task is created successfully.
     * @param t Task to be created.
     * @param taskList The list of tasks.
     */
    public void showTaskCreated(Task t, ArrayList<Task> taskList) {
        horizontalLine(lengthOfLine);
        System.out.println("Alright! I have added a new task.");
        System.out.printf("You have a total of %d tasks now.%n", taskList.size());
        horizontalLine(lengthOfLine);
    }

    /**
     * Prints the message when a task is marked.
     */
    public void showMarked() {
        horizontalLine(lengthOfLine);
        System.out.println("Sure thing! I will check off this task as done.");
        horizontalLine(lengthOfLine);
    }

    /**
     * Prints the message when a task is unmarked.
     */
    public void showUnmarked() {
        horizontalLine(lengthOfLine);
        System.out.println("Roger that, I will uncheck the task.");
        horizontalLine(lengthOfLine);
    }

    /**
     * Prints the message when a task is deleted.
     *
     * @param t Task to be deleted.
     * @param numberOfTasksLeft Number of tasks left in the list after deletion.
     */
    public void showTaskDeleted(Task t, int numberOfTasksLeft) {
        horizontalLine(lengthOfLine);
        System.out.printf("As you wish, I will remove this task: %s%n", t.toString());
        System.out.printf("Now you have %d tasks left.%n", numberOfTasksLeft);
        horizontalLine(lengthOfLine);
    }

    /**
     * Prints the list of tasks.
     *
     * @param taskList The list of tasks.
     */
    public void showTaskList(ArrayList<Task> taskList) {
        int numberOfTasks = taskList.size();
        if (taskList.isEmpty()) {
            horizontalLine(lengthOfLine);
            System.out.println("Sire/Madam, you have no tasks at hand right now.");
            horizontalLine(lengthOfLine);
        } else {
            horizontalLine(lengthOfLine);
            for (int i = 0; i < numberOfTasks; i++) {
                int number = i + 1;
                System.out.println(number + ". " + taskList.get(i));
            }
            horizontalLine(lengthOfLine);
        }
    }

    public void showFindTasks(ArrayList<Task> matchingTaskList) {
        horizontalLine(lengthOfLine);
        if (matchingTaskList.isEmpty()) {
            System.out.println("There are no matching tasks!");
        } else {
            System.out.println("Here are the matching tasks:");
            showTaskList(matchingTaskList);
        }
        horizontalLine(lengthOfLine);
    }
}
