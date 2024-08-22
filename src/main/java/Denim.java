import java.util.Scanner;

public class Denim {
    public static void main(String[] args) {

        String horizontalLine = "____________________________________________________________";
        String chatBotName = "Denim";
        String greetingMessage = String.format("%s%n Hello! I'm %s!%n What can I do for you? %n%s%n",
                horizontalLine, chatBotName, horizontalLine);

        System.out.println(greetingMessage);

        // Scans User Input in the CLI
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        // Echoes the input of the user until the command "bye" is given.
        while (!input.equals("bye")) {
            String echoMessage = String.format("%s%n %s%n%s", horizontalLine, input, horizontalLine);
            System.out.println(echoMessage);
            input = sc.next();
        }

        String byeMessage = String.format("%s%n %s%n%s", horizontalLine, "Bye. Hope to see you again soon!",
                horizontalLine);
        System.out.println(byeMessage);
        sc.close();
    }
}


