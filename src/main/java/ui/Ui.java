package ui;

public class Ui {

    private final String line = "____________________________________________________________\n";

    public Ui() {}

    public void greet() {
        System.out.print(line() + "Hello! I'm Meeks! Your friendly chatbot!\n" + "What can I do for you?\n");
    }

    public void goodbye() {
        System.out.print("Bye. Hope to see you again soon!\n" + line());
    }

    public void invalidCommand() {
        System.out.print("Oh no! You have input an unknown command!\n" + line());
    }

    public String line() {
        return this.line;
    }
    public String affirm() {
        return "Got it. I've added this task: \n";
    }
}
