package chatbot;

import java.util.Scanner;

public class Ui {
    private final Scanner input;
    private boolean isRunning;

    public Ui() {
        this.input = new Scanner(System.in);
        this.isRunning = true;
    }
    public void sayHi() {
        System.out.println("Hello, I'm Bobby.Bobby");
        System.out.println("What can I do for you?");
    }

    public boolean runStatus() {
        return this.isRunning;
    }

    public void endRun() {
        this.isRunning = false;
    }

    public String getInput() {
        return this.input.nextLine();
    }

}
