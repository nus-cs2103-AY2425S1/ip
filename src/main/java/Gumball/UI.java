package Gumball;

import java.util.Scanner;

public class UI {
    private Parser parser;
    private Scanner inputScanner;
    private String input;

    public UI() {
        parser = new Parser();
        inputScanner = new Scanner(System.in);
    }

    public String readCommand() {
        return inputScanner.nextLine();
    }

    public void intro() {
        String str = "Hello! I'm Gumball.Gumball \n"
                + "What can I do for you?";
        print(str);
    }

    public void outro() {
        String str = "Bye. Hope to see you again soon!";
        print(str);
    }

    public static void print(String out) {
        String str = "____________________________________________________________";
        System.out.println(str);
        System.out.println(out);
        System.out.println(str);

    }
}
