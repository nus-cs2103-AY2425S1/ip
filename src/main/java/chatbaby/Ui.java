package chatbaby;

import chatbaby.Command;
import chatbaby.Storage;
import chatbaby.Task;
import chatbaby.TaskType;
import chatbaby.TaskList;
import chatbaby.Ui;
import chatbaby.Parser;
import chatbaby.ChatBabyException;
import chatbaby.Deadline;
import chatbaby.Event;
import chatbaby.ToDo;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("Hello! I'm ChatBaby\nWhat can I do for you?");
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
