package ui;

import java.util.Scanner;

public class Ui {

    // TODO: Provide reading of input, showline, and print capability (perhaps create printWithDivider r sth like that
    private Scanner scanner = new Scanner(System.in);


    // Reads the user-input command and returns it to the program
    public String readCommand() {
        return scanner.nextLine();
    }

    // Prints a horizontal line to the console
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    // Prints the input string to the console
    public void print(String str) {
        System.out.println(str);
    }

    public void showLoadingError() {
        System.out.println("Tch, looks like there's a problem loading the tasks. I'll handle it, just stay calm.");
    }

    public void showWelcome() {
        this.showLine();
        System.out.println("Hey Traveller! I'm Kuki Shinobu, deputy leader of the Arataki Gang.");
        System.out.println("Just let me know if you ever find yourself in a pinch. I can help you out.");
        this.showLine();
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        this.showLine();
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showFancyGreet() {
        String logo = " ____  __.      __   .__       _________.__    .__             ___.          \n"
                + "|    |/ _|__ __|  | _|__|     /   _____/|  |__ |__| ____   ____\\_ |__  __ __ \n"
                + "|      < |  |  \\  |/ /  |     \\_____  \\ |  |  \\|  |/    \\ /  _ \\| __ \\|  |  \\\n"
                + "|    |  \\|  |  /    <|  |     /        \\|   Y  \\  |   |  (  <_> ) \\_\\ \\  |  /\n"
                + "|____|__ \\____/|__|_ \\__|    /_______  /|___|  /__|___|  /\\____/|___  /____/ \n"
                + "        \\/          \\/               \\/      \\/        \\/           \\/      \n";
        System.out.println("Hello from\n" + logo);
    }
}