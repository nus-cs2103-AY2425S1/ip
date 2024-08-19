import java.util.Scanner;
import java.util.ArrayList;

public class DemureBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // check if user ended session
        boolean finished = false;
        // list of items to do
        ArrayList<String> list = new ArrayList<>();

        // introduction to chatbot
        System.out.println("____________________________________________________________\n" +
            " Hello! I'm DemureBot\n" +
            " What can I do for you?\n" +
            "____________________________________________________________\n" +
            "\n"
        );

        // while user hasn't ended session
        while (!finished) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                finished = true;
            } else if(command.equals("list")) {
                for (int i = 0; i < list.size(); i ++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            } else {
                list.add(command);
                System.out.println("____________________________________________________________\n" +
                    "added: " + command +
                    "\n" +
                    "____________________________________________________________\n" +
                    "\n"
                );
            }
        }
        // close scanner and end session
        scanner.close();
        System.out.println("____________________________________________________________\n" +
            " Bye. Hope to see you again soon!\n" +
            "____________________________________________________________\n" +
            "\n"
        );
    }
}
