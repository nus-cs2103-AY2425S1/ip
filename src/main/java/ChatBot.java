package main.java;

import java.util.Scanner;
import java.util.ArrayList;

public class ChatBot {
    private Scanner scanner = new Scanner(System.in);

    private ArrayList<String> lst = new ArrayList<>();

    private String name;
    private String exitKeyword = "bye";
    private String listKeyword = "list";

    public ChatBot(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.println("Hello! I'm " + this.name + "\n" + "What can I do for you?");
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void addToList(String s){
        try {
            lst.add(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showList() {
        int num = 1;
        for (int i = 0; i < lst.size(); ++i) {
            System.out.println(num + ". " + lst.get(i));
            num +=1;
        }
    }

    public void run() {
        greet();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals(exitKeyword)) {
                goodbye();
                return;
            } else if (input.equals(listKeyword)){
                showList();
            } else {
                addToList(input);
                System.out.println("added: " + input);
            }
        }
    }
}
