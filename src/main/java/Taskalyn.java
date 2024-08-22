import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Taskalyn {
    public static void main(String[] args) {

        List<String> tasks = new ArrayList<String>(100);

        // Initialising scanner
        Scanner scanner = new Scanner(System.in);

        // Greeting user
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hey! I'm Taskalyn, your personal Task Manager :)\n" +
                "    What can I do for you?");
        System.out.println("    ____________________________________________________________\n");

        // Conditions
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________\n");
                break;
            } else if (input.equals("list")) {
                for (int i=0; i < tasks.size(); i++) {
                    System.out.println(i + "." + tasks.get(i));
                }
            } else {
                tasks.add(input);
                System.out.println("    ____________________________________________________________");
                System.out.println("    " + input);
                System.out.println("    ____________________________________________________________\n");
            }
        }

    }
}
