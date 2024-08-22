import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Weeny {
    public static void main(String[] args) {
        boolean farewell = false;
        List<String> tasks = new ArrayList<>();
        Scanner user_input = new Scanner(System.in);

        line();
        System.out.println("Hello! I'm Weeny\nWhat can I do for you?");
        line();

        while (!farewell) {
            String input = user_input.nextLine();

            if (input.equals("list")) {
                line();
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                line();
            } else if (input.equals("bye")) {
                farewell = true;
                line();
                System.out.println("Bye. Hope to see you again soon!");
                line();
            } else {
                tasks.add(input);
                line();
                System.out.println("added: " + input);
                line();
            }
        }

        user_input.close();
    }

    public static void line() {
        System.out.println("____________________________________________________________");
    }
}