import java.util.ArrayList;
import java.util.Scanner;

public class Duck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        String line = "____________________________________________________________";
        String welcomeMessage = "Hello! I'm Duck\nWhat can I do for you?";
        System.out.println(line + "\n" + welcomeMessage + "\n" + line);

        while (true) {
            String userCommand = scanner.nextLine();
            if (userCommand.equals("bye")) {
                break;
            }
            if (userCommand.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < list.size(); i++) {
                    int num = i + 1;
                    System.out.println(num + ". " + list.get(i));
                }
                System.out.println(line);
            } else {
                list.add(userCommand);
                System.out.println(line + "\n" + "added: " + userCommand + "\n" + line);
            }
        }

        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(line + "\n" + goodbyeMessage + "\n" + line);
        scanner.close();
    }
}
