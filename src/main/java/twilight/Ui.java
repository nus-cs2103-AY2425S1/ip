package twilight;

import java.util.Scanner;

public class Ui {
    protected Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("Hello! I am Twilight your personal assistant\nWhat can I do for you?");
    }

    public void bidFarewell() {
        System.out.println("See you");
    }

    public void printMessage(String s) {
        System.out.println(s);
    }

    public String readInput() {
        return scanner.nextLine();
    }
}
