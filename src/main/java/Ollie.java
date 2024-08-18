import java.util.Scanner;
public class Ollie {

    public static void messageWrapper(String message) {
        String border = "*-".repeat(30);
        System.out.println(border);
        System.out.println();
        System.out.println(message);
        System.out.println();
        System.out.println(border);
    }

    public static void greeting() {
        messageWrapper("Hello there! I'm OLLIE :) \nWhat can I do for you?");
    }

    public static void exit() {
        messageWrapper("Bye. Have a great day. Hope to see you again soon! :) ");
    }

    public static void commandWrapper(String message) {
        String border = "--".repeat(30);
        System.out.println(border);
        System.out.println(message);
        System.out.println(border);
    }

    public static void echo(String userCommand) {
        if (userCommand.equals("bye")) {
            exit();
        } else {
            commandWrapper(userCommand);
        }
    }

    public static void main(String[] args) {
        greeting();
        System.out.println();

        String command;

        do {
            Scanner input = new Scanner(System.in);
            command = input.nextLine();
            echo(command);
        } while (!command.equals("bye"));
    }
}