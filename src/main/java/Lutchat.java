import java.util.Scanner;

public class Lutchat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greet();

        String userInput = sc.nextLine();
        while (conversation(userInput)) {
            userInput = sc.nextLine();
        }

        exit();
    }

    static void greet() {
        System.out.print("______________________________________________\n");
        System.out.print("Hello! I'm Lutchat!\n");
        System.out.print("What can I do for you?\n");
        System.out.print("______________________________________________\n");
    }

    static void exit() {
        System.out.print("Bye! Hope to see you again soon!\n");
        System.out.print("______________________________________________\n");
    }

    static boolean conversation(String userInput) {
        switch (userInput) {
            case "bye":
                return false;
            default:
                System.out.print(userInput + "\n");
                System.out.print("______________________________________________\n");
                return true;
        }
    }
}
