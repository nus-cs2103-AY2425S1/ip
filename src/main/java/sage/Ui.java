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

    public void showResponse(String text) {
        String HORIZONTAL_LINE = "_________________________________________________";
        String indentedText = text.indent(4);
        System.out.println(HORIZONTAL_LINE + "\n" + indentedText + HORIZONTAL_LINE);
    }

    public void showWelcome() {
        showResponse(String.format("Hello! I'm %s\nWhat can i do for you?", NAME));
    }

    public void showGoodbye() {
        showResponse("Bye. Hope to see you again soon!");
    }

    public void showEmptyList() {
        showResponse("There are no task!");
    }

    public void showAddedTask(Task addedTask, int size) {
        showResponse(String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", addedTask, size));
    }

    public void showDeletedTask(Task deletedTask, int size) {
        showResponse(String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.", deletedTask, size));
    }

    public void showError(String errorMessage) {
        showResponse("ERROR: " + errorMessage);
    }
}
