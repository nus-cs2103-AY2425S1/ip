import java.util.Scanner;

public class Duck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = "____________________________________________________________";
        String welcomeMessage = "Hello! I'm Duck\nWhat can I do for you?";
        System.out.println(line + '\n' + welcomeMessage + '\n' + line);

        while (true) {
            String userCommand = scanner.nextLine();
            if (userCommand.equals("bye")) {
                break;
            }
            System.out.println(line + '\n' + userCommand + '\n' + line);
        }

        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(line + '\n' + goodbyeMessage + '\n' + line);
    }
}
