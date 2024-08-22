import java.util.Scanner;

public class Dudu {
    public static void main(String[] args) {
        String lineRule = "--------------------------------------------\n";
        String welcomeMessage = lineRule
                              + "Hello! I'm dudu\n"
                              + "What can I do for you?\n"
                              + lineRule;
        String goodbyeMessage = "Bye. Hope to see you again soon!\n"
                              + lineRule;

        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int count = 0;
        while (sc.hasNextLine()) {
            String message = sc.nextLine();
            if (message.equals("list")) {
                System.out.println(lineRule);
                if (count == 0) {
                    System.out.println("No tasks");
                } else {
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
                System.out.println(lineRule);
            } else if (message.equals("bye")) {
                break;
            } else {
                tasks[count] = message;
                count++;
                System.out.println(lineRule
                                 + "added: " + message + "\n"
                                 + lineRule);
            }
        }
        System.out.println(goodbyeMessage);
    }
}
