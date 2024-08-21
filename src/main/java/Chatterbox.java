import java.util.Scanner;

public class Chatterbox {
    public static void main(String[] args) {
        String welcomeMessage = """
                ____________________________________________________________
                 Hello! I'm Chatterbox
                 What can I do for you?
                ____________________________________________________________
                """;
        printMessage(welcomeMessage);
        boolean done = false;
        while (!done) {
            String command = echo();
            if (command.equals("bye")) {
                String byeMessage = """
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
                """;
                printMessage(byeMessage);
                done = true;
            } else {
                String echoMessage = "____________________________________________________________\n"
                        + command
                        +"\n____________________________________________________________\n";
                printMessage(echoMessage);
            }
        }
    }
    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static String echo() {
        Scanner scan  = new Scanner(System.in);
        return scan.nextLine();
    }
}