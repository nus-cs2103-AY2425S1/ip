import java.util.Scanner;
public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommands() {
        return scanner.nextLine();
    }

    public void displayLogo() {
        String logo =
                """
                          _____ _     _                          \s
                         |  ___(_)___| |__  _ __ ___   __ _ _ __ \s
                         | |_  | / __| '_ \\| '_ ` _ \\ / _` | '_ \\\s
                         |  _| | \\__ \\ | | | | | | | | (_| | | | |
                         |_|   |_|___/_| |_|_| |_| |_|\\__,_|_| |_|
                        """;
        System.out.println("Hello from\n" + logo);
    }

    public void displayWelcome() {
        System.out.println("Hello! I'm Fishman");
        System.out.println("What can I do for you?");
    }

    public void displayGoodbye() {
        System.out.println("Bloop bloop. Hope to see you again soon!");
    }

    public void displayAddedTask(Task task) {
        System.out.println("Bloop bloop, I have added the following task: ");
        System.out.println("Added: " + task);
    }

    public void displayTaskList(TaskList tasks) {
        System.out.println("Bloop bloop, here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
    }

}
