package sage;

import sage.Task.Task;

import java.util.Scanner;

public class Ui {
    public static final String NAME = "sage";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readInput() {
        return scanner.nextLine();
    }

//    public void showResponse(String text) {
//        String horizontalLine = "_________________________________________________";
//        String indentedText = text.indent(4);
//        System.out.println(horizontalLine + "\n" + indentedText + horizontalLine);
//    }

    public String showWelcome() {
        return String.format("Hello! I'm %s\nWhat can i do for you?", NAME);
    }

    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    public String showAddedTask(Task addedTask, int size) {
        return String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", addedTask, size);
    }

    public String showDeletedTask(Task deletedTask, int size)  {
        return String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.", deletedTask, size);
    }

    public String showError(String errorMessage) {
        return String.format("ERROR: " + errorMessage);
    }
}
