package cypherchatbot.util;

import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public void output(String s) {
        Ui.lineBreak();
        System.out.println(s);
        Ui.lineBreak();
    }

    public static void lineBreak() {
        System.out.println("--------------------------------------------------------------------------------------------------------------");
    }

    public Ui () {
        this.scanner =  new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
    public void greet() {
        lineBreak();
        System.out.println("Hello! I am Cypher\nWhat can I do for you!");
        lineBreak();
    }

    public void goodBye() {
        lineBreak();
        System.out.println("Bye. Hope to see you again soon!");
        lineBreak();
        this.scanner.close();
    }

    public void showLoadingError(String filePath) {
        lineBreak();
        System.out.println(String.format("Given filepath [%s] does not work. Please try again", filePath));
        lineBreak();
    }

    public void showError(String e) {
        lineBreak();
        System.out.println(e);
        lineBreak();
    }






}
