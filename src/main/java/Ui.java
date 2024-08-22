import java.util.Scanner;
public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommands() {
        return scanner.nextLine();
    }

    public void displayLogo() {
        String logo =
                "  _____ _     _                           \n"
                        + " |  ___(_)___| |__  _ __ ___   __ _ _ __  \n"
                        + " | |_  | / __| '_ \\| '_ ` _ \\ / _` | '_ \\ \n"
                        + " |  _| | \\__ \\ | | | | | | | | (_| | | | |\n"
                        + " |_|   |_|___/_| |_|_| |_| |_|\\__,_|_| |_|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void displayWelcome() {
        System.out.println("Hello! I'm Fishman");
        System.out.println("What can I do for you?");
    }

    public void displayGoodbye() {
        System.out.println("Bloop bloop. Hope to see you again soon!");
    }
}
