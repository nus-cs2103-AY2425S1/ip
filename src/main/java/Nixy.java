import java.util.Scanner;

public class Nixy {
    public static void main(String[] args) {
        String chatbotName = "Nixy";
        printHorizontalLine();
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");
        printHorizontalLine();
        echo();
    }

    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static void echo() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        if (input.equals("bye")) {
            exit();
            return;
        }
        printHorizontalLine();
        System.out.println(input);
        printHorizontalLine();
        echo();
    }

    private static void exit() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
