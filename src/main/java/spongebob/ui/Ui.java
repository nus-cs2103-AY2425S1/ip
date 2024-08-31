package spongebob.ui;

import java.util.Scanner;

import spongebob.task.Task;

/**
 * UI Component for Spongebob
 */
public class Ui {
    private static final String LINE = "____________________________________________________________\n";
    private static final String GREETINGS = "Hey there! I'm SpongeBob SquarePants! \n"
            + "What can I do for ya today?\n";
    private static final String GOODBYE = "Aye aye, pal! Bye-bye for now! "
            + "Hope to catch you in Bikini Bottom again soon! \n";

    private Scanner scanner = new Scanner(System.in);

    public String showLine() {
        return LINE;
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public String showWelcome() {
        return GREETINGS;
    }

    public String showGoodbye() {
        return GOODBYE;
    }

    public void showLoadingError() {
        System.out.println("Oh Barnacles! Your save file is corrupted!");
    }

    public String unknownCommand() {
        return "Oh, barnacles! I'm not sure what that means either. \n"
                + "Can you give me a bit more info? We'll figure it out together!";
    }

    public String showList(String string) {
        return "Alrighty, buddy! Here are the tasks in your list!\n\n" + string;
    }

    public String showMarked(Task task) {
        return "Woohoo! I've marked this task as done - great job! \n\n" + task;
    }

    public String showMarkedError() {
        return "Oh, barnacles! You can't mark nothing! \n"
                + "Make sure to fill it in before you add it.";
    }

    public String showUnmarked(Task task) {
        return "Alrighty, I've put that task back to "
                + "\"not done yet.\" Keep at it-you've got this! \n\n"
                + task;
    }

    public String showUnmarkedError() {
        return "Oh, barnacles! You can't unmark nothing! \n"
                + "Make sure to fill it in before you add it.";

    }

    public String showTaskAdded(Task task, int size) {
        return "Got it! I've added this task to your list - "
                + "keep up the great work! \n\n"
                + task + "\n\n"
                + "Now you have " + size + " in the list!";
    }

    public String showTaskDeleted(Task task, int size) {
        return "Noted. I've removed this task: \n\n"
               + task + "\n\n"
               + "Now you have " + size + " tasks in the list.";

    }

    public String showException(Exception e) {
        if (e instanceof NumberFormatException) {
            return "Oopsie-daisy! Looks like there's a hiccup - "
                    + "index needs to be a whole number!";
        } else if (e instanceof IndexOutOfBoundsException) {
            return "Oh no, it's out of bounds! That index is too far out - "
                    + "try a different number!";
        } else {
            return e.getMessage();
        }
    }

    public String showFindTask(String tasks) {
        return "Okay-dokey! here are the matching tasks! \n\n"
                + tasks;
    }

}
