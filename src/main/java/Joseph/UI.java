package Joseph;

import Joseph.Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;
public class UI {
    private Scanner scanner;
    private final String LINE = "----------------------------------";

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public void printLine() {
        System.out.println(LINE);
    }
    public void printWelcomeMessage(String name) {
        printLine();
        System.out.println("Hello, I'm " + name + "!");
        System.out.println("How can I help you today? (type help for all commands)");
        printLine();
    }

    public void printExitMessage() {
        printLine();
        System.out.println("Bye! Have a nice day :)");
        printLine();
    }

    public void printListMessage(ArrayList<Task> list) {
        printLine();
        for (int i = 0; i < list.size(); i++) {
            String done = "[" + list.get(i).getDone() + "] ";
            System.out.println(i+1 + ". " + done + list.get(i).getDetails());
        }
        printLine();
    }

    public void printHelpMessage() {
        printLine();
        System.out.println("help: prints all available commands");
        System.out.println("list: prints the current list");
        System.out.println("mark X, where X is any number: " +
                "marks task X on the list as completed");
        System.out.println("unmark X, where X is any number: " +
                "unmarks task X on the list as uncompleted.");
        System.out.println("The below todo, deadline and event commands " +
                "accepts desc as any string");
        System.out.println("The below deadline and event commands " +
                "accepts a due or start and end " +
                "in the following format: DD/MM/YYYY HHMM");
        System.out.println("todo desc, " +
                "adds a todo to the list");
        System.out.println("deadline desc /due, " +
                "adds a deadline to the list with its due date");
        System.out.println("event desc /start /end, " +
                "adds an event to the list with its start and end");
        System.out.println("bye: closes the chatbot");
        printLine();
    }

    public void printMarkMessage(String taskDetails) {
        System.out.println("Great, I've marked " + taskDetails + " as done!");
    }

    public void printUnmarkMessage(String taskDetails) {
        System.out.println("Okay, I've unmarked " + taskDetails + " as not done!");
    }

    public void printTodoMessage(String taskDetails) {
        System.out.println("I've added the todo: " + taskDetails);
    }

    public void printDeadlineMessage(String taskDetails) {
        System.out.println("I've added the deadline: " + taskDetails);
    }

    public void printJEventMessage(String taskDetails) {
        System.out.println("I've added the event: " + taskDetails);
    }

    public void printDeleteMessage(String taskDetails) {
        System.out.println("Alright, I've deleted " + taskDetails);
    }

    public void printUnrecognisedMessage() {
        System.out.println("I don't know what that means :(");
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public String readUserInput() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}