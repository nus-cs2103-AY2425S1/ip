import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void makeLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        makeLine();
        System.out.println("Hello! I am Agave.\n" +
                "What can I do for you?\n");
        makeLine();
    }

    public void showBye() {
        makeLine();
        System.out.println("Bye! Hope to see you again soon!\n");
        makeLine();
    }

    public String getUserInput(String input) {
        return scanner.nextLine();
    }

    public void showEcho(String echo) {
        makeLine();
        System.out.println(echo);
        makeLine();
    }

    public void showTasks(Task[] tasks, int taskCount) {
        makeLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i].getDescription());
        }
        makeLine();
    }
}