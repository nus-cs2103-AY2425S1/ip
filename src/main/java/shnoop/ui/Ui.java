package shnoop.ui;

import shnoop.tasks.*;

import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }
    private String[] quotes = new String[] {
        "You're unforgettable.",
                "Coded, tanned, fit and ready.",
                "You're undeniable."
    };

    /**
     * Prints out introductory speech at start of application.
     */
    public void showWelcome() {
        System.out.println("\n ... Greetings loved one ʚ♡ɞ Let's take a journey ... \n\n\n ✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-"
                + "✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿ \n"
                + " ✿ It's Shnoop, my dawg. I'm all up on ya. Whatchu need? ✿ \n"
                + " ✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿ \n");
    }

    public void showLoadingError() {
        System.out.println(" ✿ Shnoop is currently experiencing some difficulties, let's have a fresh start. ✿ \n");
    }

    public String readCommand() {
        String input = scanner.nextLine();
        return input;
    }

    public void goodbye() {
        System.out.println("\n✿ Shnoop ✿: I'll check ya later, cause you represent. Don't worry we got it on lock. ♡");
    }

    public void showError(String string) {
        System.out.println("✿ Shnoop ✿: My homie, listen when I say: " + string);
    }

    public void showLine() {
        System.out.println("\n✿-✿-✿-✿-✿-✿-✿-✿-✿ Say what you gotta say ✿-✿-✿-✿-✿-✿-✿-✿-✿");
    }

    public void mark(boolean b, Task task) {
        if (b) {
            System.out.println("✿ Shnoop ✿: Warm, wet and wild! I've marked this task as done: ");
        } else {
            System.out.println("✿ Shnoop ✿: Daisy dukes! This task was already done my love: ");
        }
        System.out.println(task);
    }

    public void unmark(boolean b, Task task) {
        if (b) {
            System.out.println("✿ Shnoop ✿: Melted this popsicle! I've unmarked this task as done: ");
        } else {
            System.out.println("✿ Shnoop ✿: Daisy dukes! This task was never done my love: ");
        }
        System.out.println(task);
    }

    /**
     * Returns a quote from the quote bank.
     *
     * @param idx Should be a changing number.
     * @return Quote from quote bank.
     */
    public String getRandomQuote(int idx) {
        return quotes[idx];
    }

    public void addTask(Task task, int size) {

        System.out.println("✿ Shnoop ✿: "
                + getRandomQuote(size % 3) + " I'll add that in for ya. \nTask Added: " + task);
        System.out.println("✿ Shnoop ✿: You've got " + size + " doggy-dogs on the stereo.");
    }

    public void list(String string) {
        System.out.println("✿ Shnoop ✿: Find, fresh, fierce and ready.");
        System.out.println(string);
    }

    public void delete(Task task) {
        System.out.println("✿ Shnoop ✿: I know a place, where the grass is really greener. "
                + "I'll send this task there\n" + "Goodbye " + task + "!");
    }
}
