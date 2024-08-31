package TrackBot.ui;

import TrackBot.task.Task;

import java.util.List;

public class Ui {
    public void showWelcome() {
        System.out.println("************************************************************");
        System.out.println("Hello from TrackBot.TrackBot!\n" + "How may I assist you?");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("************************************************************");
    }

    /**
     * Prints a line.
     */
    public void printLine() {
        System.out.println("````````````````````````````````````````````````````````````");
    }

}
