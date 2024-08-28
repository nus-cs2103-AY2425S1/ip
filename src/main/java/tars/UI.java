package tars;

import java.util.Scanner;

public class UI {
    private final Scanner scanner;
    private final String line = "____________________________________";

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void printResponse(String response) {
        System.out.println(line);
        System.out.println(response);
        System.out.println(line);
    }

    public void taskMarkedResponse(Task task) {
        printResponse("Nice! I've marked this task as done:\n" + "  " + task.toString());
    }

    public void taskUnmarkedResponse(Task task) {
        printResponse("OK, I've marked this task as not done yet:\n" + "  " + task.toString());
    }

    public void taskNotFoundResponse() {
        printResponse("The task you specified cannot be found. Please try again");
    }

    public void taskAddedResponse(Task task, int size) {
        printResponse("Got it. I've added this task: \n" + task.toString() + "\n" + "Now you have "
                + size + " tasks in the list\n");
    }

    public void taskRemovedResponse(String taskDescription, int size) {
        printResponse("Noted. I've removed this task:\n" + taskDescription + "\n" + "Now you have "
                + size + " tasks in the list\n");
    }

    public void printWelcome() {
        System.out.println(line);
        System.out.println("Hello! I'm TARS");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printLoadingError() {
        System.out.println("There was an error loading tasks.");
    }

}
