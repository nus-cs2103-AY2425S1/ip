import java.util.Scanner;

public class Waterfall {
    public static void main(String[] args) {
        String chatBotName = "Waterfall";
        String welcomeMessage =
            "____________________________________________________________\n"
            + "Hello I'm " + chatBotName + "\n"
            + "What can I do for you?\n"
            + "____________________________________________________________\n";
        String byeMessage =
            "____________________________________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "____________________________________________________________\n";
        System.out.println(welcomeMessage);
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String nextInput = userInput.nextLine();
            if (nextInput.equals("bye")) {
                break;
            } else {
                System.out.println("____________________________________________________________\n");
                System.out.println(nextInput);
                System.out.println("____________________________________________________________\n");
            }
        }
        System.out.println(byeMessage);
    }
}
