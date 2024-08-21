package main.java;

public class ChatBot {
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
}
