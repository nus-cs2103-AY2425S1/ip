import java.util.Scanner;

public class Waterfall {
    public static void main(String[] args) {
        String chatBotName = "Waterfall";
        int indentSpace = 4;
        String welcomeMessage =
            ("____________________________________________________________\n"
            + "Hello I'm " + chatBotName + "\n"
            + "What can I do for you?\n"
            + "____________________________________________________________\n").indent(indentSpace);
        String byeMessage =
            ("____________________________________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "____________________________________________________________\n").indent(indentSpace);
        System.out.println(welcomeMessage);
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String nextInput = userInput.nextLine();
            if (nextInput.equals("bye")) {
                break;
            } else {
                String echoString = ("____________________________________________________________\n"
                + nextInput + "\n"
                + "____________________________________________________________\n").indent(indentSpace);
                System.out.println(echoString);
            }
        }
        System.out.println(byeMessage);
    }
}
