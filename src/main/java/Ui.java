import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = "  ____                          ____                   \n" +
                      " / ___|___  _ ____   _____ _ __/ ___|  __ _  __ _  ___ \n" +
                      "| |   / _ \\| '_ \\ \\ / / _ \\ '__\\___ \\ / _ |/ _ |/ _ \\\n" +
                      "| |__| (_) | | | \\ V /  __/ |   ___) | (_| | (_| |  __/\n" +
                      " \\____\\___/|_| |_|\\_/ \\___|_|  |____/ \\__,_|\\__, |\\___|\n" +
                      "                                            |___/      ";
        
        System.out.println(logo);
        System.out.println("Greetings, I'm your ConverSage.");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        System.out.println("Goodbye. We shall meet again soon.");
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________ \n");
    }

    public void showError(String message) {
        System.out.println("ERR: " + message);
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
