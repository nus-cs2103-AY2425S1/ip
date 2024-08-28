package lebron;

import java.util.Scanner;


public class Ui {

    Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("------------------------");
    }

    public void showLoadingError() {
        System.out.println("There was an error in loading the chatbot");
    }

    public void showWelcomeMessage() {
        this.showLine();
        System.out.println("Wassup! I'm lebron");
        System.out.println("What can I do for you?");
        this.showLine();
    }

    public String getUserCommand() {
        return scanner.nextLine();
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye! I'm leaving now.");
        this.showLine();
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Gotchu, added the task: ");
        System.out.println(task.toString());
        System.out.println(String.format("Now you have %d tasks in the list", size));
        this.showLine();
    }

    public void showTaskDeleted(Task task) {
        System.out.println("Alright bro, I've deleted that task.");
        System.out.println(task.toString());
        this.showLine();
    }

    public void showTaskMarked(Task task) {
        System.out.println("Alright bro, I've marked that task");
        System.out.println(task.toString());
        this.showLine();
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("Alright bro, I've unmarked that task");
        System.out.println(task.toString());
        this.showLine();
    }

    public void showTaskList(TaskList taskList) throws LeBronException {
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.getTask(i);
            System.out.println(String.format("%d. %s", i, task.toString()));
        }
        this.showLine();
    }

    public void showMatchingTasks(TaskList taskList, String keyword) throws LeBronException {
        System.out.println("Here's what I've got bro");
        this.showLine();
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(keyword)) {
                System.out.println(String.format("%d. %s", i, task.toString()));
            }
        }
        this.showLine();
    }
}
