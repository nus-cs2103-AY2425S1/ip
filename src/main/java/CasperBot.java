package main.java;

public class CasperBot {
    public static void main(String[] args) {
        line();
        System.out.println("Hello! I'm CasperBot.\n" +
                "What can I do for you?");
        line();
        System.out.println("Bye. Hope to see yo again soon!");
    }

    private static void line() {
        System.out.println("------------------------------------------");
    }
}
