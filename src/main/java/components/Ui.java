package components;

import exceptions.LightException;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void welcome() {
        System.out.println("Hello! I'm Light\nWhat can I do for you?");
        lineBreak();
    }
    private static void lineBreak(){
        String lineBreak = "____________________________________________________________\n";
        System.out.println(lineBreak);
    }

    public void showError(LightException e) {
        System.out.println(e.toString());
    }
    public void showMessage(String message) {
        lineBreak();
        System.out.println(message);
        lineBreak();
    }
    public String readCommand() {
        return scanner.nextLine();
    }

    public void closeUI() {
        scanner.close();
    }
}
