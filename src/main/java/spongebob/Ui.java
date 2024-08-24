package spongebob;

import spongebob.task.Task;

import java.util.Scanner;

/**
 * UI Component for Spongebob
 */
public class Ui {
    final private static String LINE = "____________________________________________________________\n";
    final private static String GREETINGS = "Hey there! I’m SpongeBob SquarePants! \n" +
            "What can I do for ya today?\n";
    final private static String GOODBYE = "Aye aye, pal! Bye-bye for now! " +
            "Hope to catch you in Bikini Bottom again soon! \n";

    private Scanner scanner = new Scanner(System.in);

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println(LINE + "\n" + GREETINGS + LINE);
    }

    public void showGoodbye() {
        System.out.println(GOODBYE);
    }

    public void showLoadingError() {
        System.out.println("Oh Barnacles! Your save file is corrupted!");
    }

    public void unknownCommand() {
        System.out.println("Oh, barnacles! I’m not sure what that means either. \n" +
                "Can you give me a bit more info? We’ll figure it out together!");
    }

    public void showList(String string) {
        System.out.println("Alrighty, buddy! Here are the tasks in your list!\n");
        System.out.println(string);
    }

    public void showMarked(Task task) {
        System.out.println("Woohoo! I’ve marked this task as done — great job!");
        System.out.println(task);
    }

    public void showMarkedError() {
        System.out.println("Oh, barnacles! You can't mark nothing! \n" +
                "Make sure to fill it in before you add it.");
    }

    public void showUnmarked(Task task) {
        System.out.println("Alrighty, I’ve put that task back to " +
                "\"not done yet.\" Keep at it—you’ve got this!");
        System.out.println(task);
    }

    public void showUnmarkedError() {
        System.out.println("Oh, barnacles! You can't unmark nothing! \n" +
                "Make sure to fill it in before you add it.");

    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it! I've added this task to your list — " +
                "keep up the great work!");
        System.out.println(task);
        System.out.println("Now you have " + size + " in the list!");
    }

    public void showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");

    }

    public void showException(Exception e) {
        if (e instanceof NumberFormatException) {
            System.out.println("Oopsie-daisy! Looks like there’s a hiccup — " +
                    "index needs to be a whole number!");
        } else if (e instanceof IndexOutOfBoundsException ) {
            System.out.println("Oh no, it’s out of bounds! That index is too far out — " +
                    "try a different number!");
        } else {
            System.out.println(e.getMessage());
        }
    }

    public void showFindTask(String tasks) {
        System.out.println("Okay-dokey! here are the matching tasks! \n");
        System.out.println(tasks);
    }

}
