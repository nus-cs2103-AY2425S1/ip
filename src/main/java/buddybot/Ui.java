package buddybot;

import java.util.Scanner;

public class Ui {
    private Scanner myObj = new Scanner(System.in);

    public String readInput() {
        return this.myObj.nextLine();
    }


    public void closeInput() {
        this.myObj.close();
    }

    public void welcomeMsg() {
        System.out.println(" Hello! I'm BuddyBot" + "\n" +
                " What can I do for you?");
    }

    public void goodbyeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void showLoadingError(String msg) {
        System.out.println(msg);
    }

    public void addTask(Task task, int i) {
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.println("Now you have " + i + " tasks in the list.");
    }

    public void showDelete(Task task, int i) {
        System.out.println("Got it. I've removed this task: \n" + task);
        System.out.printf("Now you have " + i + "\" tasks in the list.\"" );
    }

    public void showDone(Task task) {
        System.out.println("I've marked this task as done!");
        System.out.printf(task.toString());
    }


    public void showList(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("You have no task in your list.");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ". " + taskList.get(i));
        }
        System.out.println();
    }

    public void showBuddyBotException(BuddyBotException e) {
        System.out.printf(e.getMessage());
    }

    public void showSavingError() {
        System.out.println("Error when saving data!");
    }
    public void showInvalidDateRange() {
        System.out.println("\tEnd time must be after the start time!\n");
    }

    public void showInvalidDateFormat() {
        System.out.println("\tPlease enter the start/end time in the format of <DD/MM/YY HH:MM>!\n");
    }


    public void showSuccessMsg(int i) {
        System.out.println("Now you have " + i + " tasks in the list.");
    }

    public String getUserInput() {
        return myObj.nextLine();
    }

    public void showLine() {
        myObj.nextLine();
    }

}
