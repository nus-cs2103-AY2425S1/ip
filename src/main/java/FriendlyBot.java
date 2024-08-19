import java.util.Scanner;

public class FriendlyBot {
    private static void printHorizontalBar() {
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        FriendlyBot.printHorizontalBar();
        System.out.println("    Hello! I'm Friendly Bot");
        System.out.println("    What can I do for you?");
        FriendlyBot.printHorizontalBar();
        String response = "";
        while (!response.equals("bye")) {
            response = reader.next();
            FriendlyBot.printHorizontalBar();
            if (response.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
            } else {
                System.out.println("    " + response);
            }
            FriendlyBot.printHorizontalBar();
        }
    }
}
