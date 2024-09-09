package chatgpt.ui;

import chatgpt.task.Task;
import chatgpt.task.TaskList;

import java.util.Scanner;

public class Ui {

    private final static String NAME = "ChatGPT";
    private final static String LINE = "________________________________________________";
    private Scanner inputReader;

    public Ui() {
        this.inputReader = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println("\tHello! I'm " + NAME);
        System.out.println("\tWhat can I do for you?");
        showLine();
    }

    public void showExit() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println("\t"+LINE);
    }

    public String readCommand() {
        return inputReader.nextLine();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showAddTask(Task task, int taskNum) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  "+ task.toString());
        System.out.println("\tNow you have " + taskNum + " tasks in your list.");
    }

    public void showDeleteTask(Task task, int taskNum) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  "+ task.toString());
        System.out.println("\tNow you have " + taskNum + " tasks in your list.");
    }

    public void showCompleteTask(Task task) {
        System.out.println("\t Nice! I've marked this task as done: \n\t  "
                + task.toString());
    }

    public void showUncompleteTask(Task task) {
        System.out.println("\t OK, I've marked this task as not done yet: \n\t  "
                + task.toString());
    }

    public void showList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\tNothing has been added");
        } else {
            System.out.println("\tHere are the tasks in your list:");
        }
        for (int i = 0; i < tasks.size(); i++){
            System.out.println("\t" + (i+1) + ". " + tasks.get(i).toString());
        }
    }

    public void showFound(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\tNothing with that keyword was found");
        } else {
            System.out.println("\tHere are the matching tasks in your list:");
        }
        for (int i = 0; i < tasks.size(); i++){
            System.out.println("\t" + (i+1) + ". " + tasks.get(i).toString());
        }
    }

    public void showLoadingError() {
        showLine();
        System.out.println("\tThere was a problem with the save file");
        System.out.println("\tYou can either: ");
        System.out.println("\t(1) Fix the save file manually and restart the program");
        System.out.println("\t(2) Start from scratch");
    }
}
