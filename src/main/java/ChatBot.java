package main.java;

import java.util.Scanner;
import java.util.ArrayList;

public class ChatBot {
    private Scanner scanner = new Scanner(System.in);

    private ArrayList<Task> lst = new ArrayList<>();

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

    public void addToList(String desc){
        try {
            lst.add(new Task(desc));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showList() {
        int num = 1;
        for (int i = 0; i < lst.size(); ++i) {
            System.out.println(num + ". " + lst.get(i).getDesc());
            num +=1;
        }
    }

    public void run() {
        greet();
        while (true) {
            String input = scanner.nextLine();

            String [] parts = input.split(" ");

            if (input.equals(exitKeyword)) {
                goodbye();
                return;
            } else if (input.equals(listKeyword)) {
                showList();
            } else if (parts.length == 2 && parts[0].equals("mark")) {
                Task task = lst.get(Integer.parseInt(parts[1])-1);
                task.markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task.getDesc());
            } else if (parts.length == 2 && parts[0].equals("unmark")) {
                Task task = lst.get(Integer.parseInt(parts[1])-1);
                task.markUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task.getDesc());
            } else {
                addToList(input);
                System.out.println("added: " + input);
            }
        }
    }
}
