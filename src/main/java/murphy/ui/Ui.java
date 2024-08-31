package murphy.ui;

import java.util.Scanner;

public class Ui {
    Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
        showLine();
        System.out.println("Hello! I'm Murphy");
        System.out.println("What can I do for you?");
        showLine();
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void showText(String text) {
        System.out.println(text);
        System.out.println("____________________");
    }

    public void showError(String text) {
        System.out.printf("ERROR: %s\n", text);
        System.out.println("____________________");
    }

    private void showLine() {
        System.out.println("____________________");
    }

    public void closeScanner() {
        scanner.close();
    }
}
