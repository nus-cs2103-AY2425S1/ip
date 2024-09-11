package rizz.source;
import java.util.Scanner;
import rizz.task.*;

public class Ui {
    public void errorSaveTasks() {
        System.out.println("There was an error saving the tasks.");
    }

    public void errorLoadTasks() {
        System.out.println("There was an error loading the tasks.");
    }

    public void noSavedTasks() {
        System.out.println("No saved tasks found. Starting with an empty list.");
    }

    public void successSavedTasks() {
        System.out.println("Tasks loaded successfully!");
    }

    public void unknownTask(String taskType) {
        System.out.println("Unknown task type: " + taskType);
    }

    /**
     * Shows the tasks that match the search keyword.
     *
     * @param tasks The TaskList containing the matching tasks.
     */
    public void showMatchingTasks(TaskList tasks) {
        if (tasks.getLength() == 0) {
            System.out.println("\tNo matching tasks found.");
        } else {
            System.out.println("\tHere are the matching tasks in your list:");
            for (int i = 0; i < tasks.getLength(); i++) {
                System.out.printf("\t%d.%s\n", i + 1, tasks.getTask(i));
            }
        }
        System.out.println("\t____________________________________________________________");
    }

    public void greet() {
        String str = "\t____________________________________________________________\n" +
                "\tHello! I'm Rizz\n" +
                "\tWhat can I do for you?\n" +
                "\t____________________________________________________________\n";
        System.out.println(str);
    }

    public void exit() {
        String str =  "\t____________________________________________________________\n" +
                "\tBye! Hope to see you again soon!\n" +
                "\t____________________________________________________________\n";
        System.out.println(str);
    }

    public void outputList(TaskList taskList) {
        if (taskList.getLength() == 0) {
            System.out.println("\tNo items in the list.\n");
        } else {
            StringBuilder str = new StringBuilder("\t____________________________________________________________\n");
            str.append("\tHere are the tasks in your list:\n");


            for (int i = 0; i < taskList.getLength(); i++) {
                str.append("\t").append(i + 1).append(". ").append(taskList.getTask(i)).append("\n");
            }

            str.append("\t____________________________________________________________\n");
            System.out.println(str.toString());
        }
    }


    public void markTask(Task task) {
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t" + task.toString() + "\n");
    }

    public void unmarkTask(Task task) {
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t\t" + task.toString() + "\n");
    }

    public void noMoreCommands() {
        System.out.println("\tNo more commands in input.\n");
    }
    

    public void invalidMarkCommand() {
        System.out.println("\t The mark command is missing an index or has an invalid index.");
    }

    public void invalidUnmarkCommand() {
        System.out.println("\t The unmark command is missing an index or has an invalid index.");
    }

    public void invalidToDoCommand() {
        System.out.println("\t The todo details cannot be empty / incorrectly formatted.");
    }

    public void invalidDeadlineCommand() {
        System.out.println("\t The deadline's details cannot be empty / incorrectly formatted.");
    }

    public void invalidTimeFormat() {
        System.out.println("\t The date/time format is incorrect. Please use yyyy-MM-dd HHmm.");
    }

    public void invalidEventFormat() {
        System.out.println("\t The event's details cannot be empty / incorrectly formatted.");
    }

    public void invalidDeleteCommand() {
        System.out.println("\t The deletion command is empty / incorrectly formatted.");
    }

    public void unknownCommand() {
        System.out.println("\t I'm sorry, but I don't know what that means :-(");
    }

    public void addEvent(String Event, String taskListSize) {
        System.out.println("\tadded event: " + Event);
        System.out.printf("\tYou have %d tasks in the list.\n", taskListSize);
    }

    public void addToDo(ToDo newToDo, int taskListSize) {
        System.out.println("\tadded event: " + newToDo.toString());
        System.out.printf("\tYou have %d tasks in the list.\n", taskListSize);
    }

    public void addDeadline(Deadline Deadline) {
        System.out.println("\tadded deadline: " + Deadline.toString());
    }

    public void deleteTask(String Task, String taskListSize) {
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t " + Task);
            System.out.printf("\tNow you have %d tasks in the list.\n\n", taskListSize);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}
