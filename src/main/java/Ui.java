import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printStartMessage() {
        System.out.println("________________________________");
        System.out.println("Hello! I'm YapperBot");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");
    }

    public void printEndMessage() {
        System.out.println("________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("________________________________");
    }

    public void printLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty task list.");
    }

    public void printInvalidCommandMessage() {
        System.out.println("________________________________");
        System.out.println("IDK what you are yapping about!!");
        System.out.println("________________________________");
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}

