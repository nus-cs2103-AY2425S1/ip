import java.util.Scanner;

public class MonoBot {
    public static void main(String[] args) {
        printGreeting();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            }
            hLine();
            System.out.println(command);
            hLine();
        }

        printFarewell();
    }

    private static void hLine() {
        System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
    }

    private static void printGreeting() {
        hLine();
        System.out.println(" Hello! I'm MonoBot");
        System.out.println(" What can I do for you?");
        hLine();    }

    private static void printFarewell() {
        System.out.println(" Bye. Hope to see you again soon!");
        hLine();
    }
}
