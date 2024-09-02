package yoda;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;


    public Ui() {
//        this.tasks = tasks;
        this.scanner = new Scanner(System.in);
    }

    public String getNextLine() {
        return scanner.nextLine().trim();
    }

    public void printLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    public void showLoadingError() {
        String message = "Error loading UI";
        System.out.println("Error loading UI");
    }
}
