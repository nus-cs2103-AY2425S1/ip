package main.java;

import java.util.Scanner;

public class ChatBot {
    private Scanner scanner = new Scanner(System.in);
    private String exitKeyword = "bye";

    private String name;
    public ChatBot(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.println("Hello! I'm " + this.name + "\n" + "What can I do for you?");
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void run() {
        greet();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals(exitKeyword)) {
                goodbye();
                return;
            } else {
                System.out.println(input);
            }
        }
    }
}
