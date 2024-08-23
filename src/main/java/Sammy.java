import java.util.ArrayList;
import java.util.Scanner;

public class Sammy {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        System.out.println(line);
        System.out.println(" Hello! I'm Sammy.");
        System.out.println(" What can I do for you?");
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                }
            } else {
                tasks.add(input);
                System.out.println(line);
                System.out.println(" added: " + input);
            }

            System.out.println(line);
        }

        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
